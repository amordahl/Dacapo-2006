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
 * Optimizes Java classes.  Loads classes by interacting with the PEVM.
 */
public class Optimizer {

  protected ClassInfoLoader     loader;
  protected Editor              editor;

  /**
   * Constructor.
   */
  public Optimizer() {
    loader = new PEVMClassInfoLoader();
    editor = new Editor(loader, false);
  }

  /**
   * Optimizes a given method in a given class.  Requires
   * fully-qualified names.  This guy gets called from inside the PEVM.
   */
  public static void optimize(String className, String methodName, 
			      String signature) {
    /* Invoking a non-static method from inside the PEVM was a pain in
       the butt.  Cheat.  Make a new Optimizer each time or risk an
       assertion failure during garbage collection. */

    Optimizer optimizer = new DumpingOptimizer("/home/davewhit/dump");
    optimizer.optimize0(className, methodName + signature);
  }

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

    optimize(editor, method);

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
    //    db("  Array Initialization Compaction");
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
}
