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

/**
 * Optimizes Java classes.  Loads classes by interacting with the PEVM.
 */

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

public class TimingOptimizer extends Optimizer {

  protected File                outputDir;
  protected PrintWriter         times;

  protected void useTimeForClass(String className) {
    if(outputDir == null) {
      times = new PrintWriter(System.out, true);
      return;
    }

    File file = new File(outputDir, className + ".times");
    File parent = file.getParentFile();
    if(!parent.exists())
      parent.mkdirs();

    try {
      String fileName = file.getPath();
      FileWriter fw = new FileWriter(fileName, true);
      times = new PrintWriter(System.out, true);

      //      System.out.println("Writing times to " + fileName);

    } catch(IOException ex) {
      System.err.println(ex.toString());
      System.exit(1);
    }
  }

  protected void time(String s) {
    if(times != null)
      times.println(s);
  }

  /**
   * Constructor.  Print timing information to the console.
   */
  public TimingOptimizer() {
    super();
    outputDir = null;
  }

  /**
   * Constructor.  Place timing information in files located in a
   * given directory.
   */
  public TimingOptimizer(String outputDirName) {
    // Load classes from PEVM
    super();

    //    try {
    outputDir = new File(outputDirName);

    /*
      } catch(IOException ex) {
      System.err.println(ex.toString());
      System.exit(1);
      }
      */
  }

  private static final PrintWriter console = new
     PrintWriter(System.out, true);

  protected void optimize0(String className, String methodName) {

    // Figure out where the times go
    PrintWriter times = null;
    if(outputDir == null) {
      times = console;

    } else {
      File file = new File(outputDir, className + ".times");
      File parent = file.getParentFile();
      if(!parent.exists())
	parent.mkdirs();

      try {
	String fileName = file.getPath();
	FileWriter fw = new FileWriter(fileName, true);
	times = new PrintWriter(fw, true);

	//	System.out.println("Writing times to " + fileName);
	
      } catch(IOException ex) {
	System.err.println(ex.toString());
	System.exit(1);
      }      
    }

    times.println("Optimizing: " + className + "." + methodName);

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

    optimize(editor, method, times);

    times.flush();
    times.close();

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
  protected void optimize(Editor editor, MethodEditor method, 
			  PrintWriter times) {

    long first = System.currentTimeMillis();
    long last = first;
    long curr = last;

    // Count the number of putfields
    Iterator insts = method.code().iterator();
    CountPutfields visitor = new CountPutfields();
    while(insts.hasNext()) {
      Object o = insts.next();
      if(o instanceof Instruction) {
	Instruction inst = (Instruction) o;
	inst.visit(visitor);
      }
    }
    times.println("  Number of putfields (before): " + 
		  visitor.putfieldCount);

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

    curr = System.currentTimeMillis();
    times.println("  Create and Initialize CFG: " + (curr - last) + " ms");
    last = curr;

    SSA.transform(cfg);

    curr = System.currentTimeMillis();
    times.println("  Transform to SSA form: " + (curr - last) + " ms");
    last = curr;

    ExprPropagation copy = new ExprPropagation(cfg);
    copy.transform();

    curr = System.currentTimeMillis();
    times.println("  Expression Propagation 1: " + (curr - last) + " ms");
    last = curr;

    DeadCodeElimination dce = new DeadCodeElimination(cfg);
    dce.transform();

    curr = System.currentTimeMillis();
    times.println("  Dead Code Elimination 1: " + (curr - last) + " ms");
    last = curr;

    TypeInference.transform(cfg, editor.hierarchy());

    curr = System.currentTimeMillis();
    times.println("  Type Inference: " + (curr - last) + " ms");
    last = curr;

    /*
    if(false)
      return;
      */

    (new ValueNumbering()).transform(cfg);

    curr = System.currentTimeMillis();
    times.println("  Value Numbering: " + (curr - last) + " ms");
    last = curr;

    (new ValueFolding()).transform(cfg);

    curr = System.currentTimeMillis();
    times.println("  Value Folding 1: " + (curr - last) + " ms");
    last = curr;

    SSAPRE pre = new SSAPRE(cfg, editor);
    pre.transform();

    curr = System.currentTimeMillis();
    times.println("  SSAPRE: " + (curr - last) + " ms");
    last = curr;

    (new ValueFolding()).transform(cfg);

    curr = System.currentTimeMillis();
    times.println("  Value Folding 2: " + (curr - last) + " ms");
    last = curr;

    ExprPropagation copy2 = new ExprPropagation(cfg);
    copy2.transform();

    curr = System.currentTimeMillis();
    times.println("  Expression Propagation 2: " + (curr - last) + " ms");
    last = curr;

    dce = new DeadCodeElimination(cfg);
    dce.transform();

    curr = System.currentTimeMillis();
    times.println("  Dead Code Elimination 2: " + (curr - last) + " ms");
    last = curr;

    (new PersistentCheckElimination()).transform(cfg);

    curr = System.currentTimeMillis();
    times.println("  Persistent Check Elimination: " + (curr - last) + " ms");
    last = curr;

    cfg.visit(new VerifyCFG());

    curr = System.currentTimeMillis();
    times.println("  Verifying CFG: " + (curr - last) + " ms");
    last = curr;

    Liveness liveness = new Liveness(cfg);
    RegisterAllocator alloc = new RegisterAllocator(cfg, liveness);

    curr = System.currentTimeMillis();
    times.println("  Register Allocation: " + (curr - last) + " ms");
    last = curr;

    CodeGenerator codegen = new CodeGenerator(method);
    codegen.replacePhis(cfg);

    codegen.simplifyControlFlow(cfg);
    codegen.allocReturnAddresses(cfg, alloc);

    method.clearCode();

    cfg.visit(codegen);

    curr = System.currentTimeMillis();
    times.println("  Code Generation: " + (curr - last) + " ms");
    last = curr;

    Peephole.transform(method);

    // Count the number of putfields
    insts = method.code().iterator();
    visitor = new CountPutfields();
    while(insts.hasNext()) {
      Object o = insts.next();
      if(o instanceof Instruction) {
	Instruction inst = (Instruction) o;
	inst.visit(visitor);
      }
    }
    times.println("  Number of putfields (after): " + 
		  visitor.putfieldCount);
    times.println("  Number of putfield_nowbs (after): " +
		  visitor.putfield_nowbCount);

    editor.commit(method.methodInfo());

    curr = System.currentTimeMillis();
    times.println("  Peephole and commit: " + (curr - last) + " ms");
    last = curr;

    curr = System.currentTimeMillis();
    times.println("  Total time: " + (curr - first) + " ms");

    return;
  }

  private static void usage() {
    PrintStream err = System.err;
    err.println("Optimize methods from a file instead of the PEVM");
    err.println("Usage: java EDU.purdue.cs.bloat.pevm.Optimizer " +
		"[options] method");
    err.println("  -f fileName     Read method names from fileName");
    err.println("  method          Fully specified method (e.g. " + 
		"java/lang/String.hashCode()I)");
  }

  /**
   * Main method to BLOAT methods from a ClassFileLoader instead of
   * the PEVM.  Useful for making comparisons between the two.
   */
  /*
  public static void main(String[] args) {
    String methodName = null;

    // Make a new FileOptimizer
    FileOptimizer optimizer = new FileOptimizer();

    // Parse the command line
    for(int i = 0; i < args.length; i++) {
      if(args[i].equals("-f")) {
	// Read method names from a file
	i++;
	if(i >= args.length) {
	  System.err.println("Missing input file name");
	  break;
	}

	try {
	  FileReader fr = new FileReader(args[i]);
	  BufferedReader br = new BufferedReader(fr);

	  while(br.ready()) {
	    // Each line of the file contains the name of a method
	    String line = br.readLine();
	    System.out.println(line);
	    MethodEditor methodEditor = optimizer.getMethodNamed(line);
	    optimizer.optimize(optimizer.editor, methodEditor);
	  }

	} catch(IOException ex) {
	  System.err.println(ex.toString());
	  System.exit(1);
	}

	return;

      } else {
	if(i != args.length - 1) {
	  System.err.println("Unknown option: " + args[i]);
	  break;
	}

	// Must be a method name
	methodName = args[i];
	MethodEditor methodEditor = optimizer.getMethodNamed(methodName);
	optimizer.optimize(optimizer.editor, methodEditor);
	return;
      }
    }

    // Something must have gone wrong
    usage();
  }
  */

  /**
   * Parse a method's name and return the MethodEditor for that method.
   */
  protected MethodEditor getMethodNamed(String methodName) {
    String className = methodName.substring(0,
					    methodName.indexOf('.'));
    String name = methodName.substring(methodName.indexOf('.') + 1);

    // Put files in right directory
    this.useTimeForClass(className);
    time("Optimizing: " + methodName);

    try {
      ClassEditor classEditor = editor.editClass(className);

      MethodInfo[] methods = classEditor.methods();
      for(int i = 0; i < methods.length; i++) {
	try {
	  MethodEditor method = editor.editMethod(methods[i]);
	  String name2 = method.name() + method.type();
	  if(name.equals(name2))
	    return(method);
	  
	} catch(ClassFormatException ex) {
	  System.err.println(ex.getMessage());
	  continue;
	}
      }

    } catch(ClassNotFoundException ex) {
      System.err.println(ex.toString());
      return(null);
    }

    // If we get here we didn't find the method!
    throw new Error("Couldn't find method: " + methodName);
  }
}

class CountPutfields extends InstructionAdapter {

  public int putfieldCount = 0;
  public int putfield_nowbCount = 0;

  public void visit_putfield(Instruction inst) {
    putfieldCount++;
  }

  public void visit_putfield_nowb(Instruction inst) {
    putfield_nowbCount++;
  }

}
