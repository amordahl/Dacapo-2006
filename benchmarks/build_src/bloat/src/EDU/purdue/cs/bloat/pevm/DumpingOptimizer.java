/**
 * All files in the distribution of BLOAT (Bytecode Level Optimization and
 * Analysis tool for Java(tm)) are Copyright 1997-2001 by the Purdue
 * Research Foundation of Purdue University.  All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms are permitted
 * provided that this entire copyright notice is duplicated in all
 * such copies, and that any documentation, announcements, and other
 * materials related to such distribution and use acknowledge that the
 * software was developed at Purdue University, West Lafayette, IN by
 * Antony Hosking, David Whitlock, and Nathaniel Nystrom.  No charge
 * may be made for copies, derivations, or distributions of this
 * material without the express written consent of the copyright
 * holder.  Neither the name of the University nor the name of the
 * author may be used to endorse or promote products derived from this
 * material without specific prior written permission.  THIS SOFTWARE
 * IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR ANY PARTICULAR PURPOSE.
 *
 * <p>
 * Java is a trademark of Sun Microsystems, Inc.
 */
package EDU.purdue.cs.bloat.pevm;

import EDU.purdue.cs.bloat.reflect.*;
import EDU.purdue.cs.bloat.file.*;
import EDU.purdue.cs.bloat.pevm.*;
import EDU.purdue.cs.bloat.editor.*;
import EDU.purdue.cs.bloat.cfg.*;
import EDU.purdue.cs.bloat.tree.*;
import EDU.purdue.cs.bloat.ssa.*;
import EDU.purdue.cs.bloat.tbaa.*;
import EDU.purdue.cs.bloat.trans.*;
import EDU.purdue.cs.bloat.codegen.*;
import EDU.purdue.cs.bloat.util.*;

import java.io.*;
import java.util.*;

/**
 * This optimizer dumps the bytecode of the method being optimized to
 * a file both before and after optimization.
 */
public class DumpingOptimizer extends Optimizer {

  protected File                outputDir;

  /**
   * Constructor.  Dump bytecodes to directories in the home directory.
   */
  public DumpingOptimizer() {
    super();

    String homeName = System.getProperty("user.home");
    outputDir = new File(homeName);
  }

  /**
   * Constructor.  Place byetcode dumps in files located in a
   * given directory.
   */
  public DumpingOptimizer(String outputDirName) {
    super();

    outputDir = new File(outputDirName);

  }

  private static final PrintWriter console = new
     PrintWriter(System.out, true);

  protected void optimize0(String className, String methodName) {

    // Load the class from the PEVM
    ClassInfo classInfo = null;

    try {
      classInfo = loader.loadClass(className);

    } catch(ClassNotFoundException ex) {
      System.err.println(ex);
    }

    MethodEditor method = null;
    MethodInfo[] methods = classInfo.methods();

    // Locate the MethodEditor for the desired method
    for (int j = 0; j < methods.length; j++) {
      MethodEditor m;
      
      try {
	m = editor.editMethod(methods[j]);

      } catch (ClassFormatException ex) {
	System.err.println(ex.getMessage());
	continue;
      }
      
      String t = m.name() + m.type();
	
      if (t.equals(methodName)) {
	method = m;
	break;
      }
	
      // This isn't the method we're looking for.  
      // Decrement its reference count.
      editor.release(methods[j]);
      continue;
    }

    if (method == null || method.isNative() || method.isAbstract()) {
      // We can't edit native or abstract methods
      editor.release(method.methodInfo());
      return;
    }

    // Determine the files in which to place the dumps.  Each method
    // gets its own file.
    PrintWriter before = null;
    PrintWriter after = null;

    try {
      String newMethodName = methodName.replace('/', '\\');

      /*
      methodName.replace(';', '#');
      newMethodName = newMethodName.replace('(', '{');
      newMethodName = newMethodName.replace(')', '}');
      */

      className = className.replace('.', File.separatorChar);
      File beforeDir = new File(outputDir, "before" +
				File.separatorChar + className);
      if(!beforeDir.exists())
	beforeDir.mkdirs();
      File beforeFile = new File(beforeDir, newMethodName + ".dump");

      File afterDir = new File(outputDir, "after" + File.separatorChar
			       + className);
      if(!afterDir.exists())
	afterDir.mkdirs();
      File afterFile = new File(afterDir, newMethodName + ".dump");

      System.out.println("Before: " + beforeFile);
      System.out.println("After: " + afterFile);

      before = new PrintWriter(new FileWriter(beforeFile), true);
      after = new PrintWriter(new FileWriter(afterFile), true);

    } catch(IOException ex) {
      System.err.println("IOException: " + ex);
      System.exit(1);
    }

    dumpMethod(method, before);
    before.flush();
    before.close();

    optimize(editor, method);

    dumpMethod(method, after);
    after.flush();
    after.close();

    if(classInfo instanceof PEVMClassInfo) {
      // Just commit this method.  Remember that no class information
      // is modified.
      PEVMClassInfo pevmInfo = (PEVMClassInfo) classInfo;
      pevmInfo.updateMethodInPEVM(method.methodInfo());

    } else {
      // Commit the whole thing
      classInfo.commit();
    }

    editor.release(method.methodInfo());
    editor.release(classInfo);

  }

  /**
   * Optimizes one method
   */
  protected void optimize(Editor editor, MethodEditor method) {

    // I don't think array initialization compaction is necessary
    // anymore
    //    CompactArrayInitializer.transform(method);

    FlowGraph cfg;

    try {
      cfg = new FlowGraph(method);

    } catch(ClassFormatException ex) {
      method.declaringClass().editor().release(method.methodInfo());
      return;
    }

    cfg.initialize();

    SSA.transform(cfg);

    ExprPropagation copy = new ExprPropagation(cfg);
    copy.transform();

    DeadCodeElimination dce = new DeadCodeElimination(cfg);
    dce.transform();

    TypeInference.transform(cfg, editor.hierarchy());

    (new ValueNumbering()).transform(cfg);

    (new ValueFolding()).transform(cfg);

    SSAPRE pre = new SSAPRE(cfg, editor);
    pre.transform();

    (new ValueFolding()).transform(cfg);

    ExprPropagation copy2 = new ExprPropagation(cfg);
    copy2.transform();

    dce = new DeadCodeElimination(cfg);
    dce.transform();

    (new PersistentCheckElimination()).transform(cfg);

    cfg.visit(new VerifyCFG());

    Liveness liveness = new Liveness(cfg);
    RegisterAllocator alloc = new RegisterAllocator(cfg, liveness);

    CodeGenerator codegen = new CodeGenerator(method);
    codegen.replacePhis(cfg);

    codegen.simplifyControlFlow(cfg);
    codegen.allocReturnAddresses(cfg, alloc);

    method.clearCode();

    cfg.visit(codegen);

    Peephole.transform(method);

    editor.commit(method.methodInfo());

    return;
  }

  private void dumpMethod(MethodEditor method, PrintWriter pw) {
    // Print the class info

    ClassEditor c = method.declaringClass();

    if (c.isPublic()) {
      pw.print("public ");
    }
    else if (c.isPrivate()) {
      pw.print("private ");
    }
    else if (c.isProtected()) {
      pw.print("protected ");
    }

    if (c.isStatic()) {
      pw.print("static ");
    }

    if (c.isFinal()) {
      pw.print("final ");
    }

    if (c.isInterface()) {
      pw.print("interface ");
    }
    else if (c.isAbstract()) {
      pw.print("abstract class ");
    }
    else {
      pw.print("class ");
    }

    pw.print(c.type().className());

    if (c.superclass() != null) {
      pw.print(" extends " + c.superclass().className());
    }

    Type[] interfaces = c.interfaces();

    for (int i = 0; i < interfaces.length; i++) {
      if (i == 0) {
	pw.print(" implements");
      }
      else {
	pw.print(",");
      }

      pw.print(" " + interfaces[i].className());
    }

    pw.println();
    pw.println("{");

    FieldInfo[] fields = c.fields();

    for (int i = 0; i < fields.length; i++) {
      FieldEditor f = null;

      try {
	f = editor.editField(fields[i]);
      }
      catch (ClassFormatException ex) {
	System.err.println(ex.getMessage());
	System.exit(1);
      }

      pw.print("    ");

      if (f.isPublic()) {
	pw.print("public ");
      }
      else if (f.isPrivate()) {
	pw.print("private ");
      }
      else if (f.isProtected()) {
	pw.print("protected ");
      }

      if (f.isTransient()) {
	pw.print("transient ");
      }

      if (f.isVolatile()) {
	pw.print("volatile ");
      }

      if (f.isStatic()) {
	pw.print("static ");
      }

      if (f.isFinal()) {
	pw.print("final ");
      }

      pw.println(f.type() + " " + f.name());

      editor.release(fields[i]);
    }

    if (fields.length != 0) {
      pw.println();
    }


    MethodEditor m = method;

    pw.print("    ");

    if (m.isPublic())
      pw.print("public ");

    else if (m.isPrivate())
      pw.print("private ");

    else if (m.isProtected())
      pw.print("protected ");
    
    if (m.isNative())
      pw.print("native ");

    if (m.isSynchronized()) {
      pw.print("synchronized ");
    }
    
    if (m.isAbstract()) {
      pw.print("abstract ");
    }

    if (m.isStatic()) {
      pw.print("static ");
    }
    
    if (m.isFinal()) {
      pw.print("final ");
    }
    
    pw.println(m.type() + " " + m.name());
    
    Iterator iter = m.code().iterator(); 
    
    while (iter.hasNext()) {
      Object obj = iter.next();
      
      int opcode = 0;
      if(obj instanceof Instruction) {
	Instruction inst = (Instruction) obj;
	opcode = inst.opcodeClass();
	}
      
      //	pw.println(opcode + "        " + obj);
      pw.println("        " + obj);
    }
    
    pw.println("}");

    // Print the Constant Pool
    pw.println("\nConstant Pool:");
    ConstantPool cp = c.constants();
    for(int i = 0; i < cp.numConstants(); i++)
      pw.println("  " + i + ": " + cp.constants()[i]);

  }
}
