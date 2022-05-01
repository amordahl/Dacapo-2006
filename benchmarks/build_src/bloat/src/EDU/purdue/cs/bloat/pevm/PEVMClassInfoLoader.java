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

import java.io.*;
import java.util.*;

import EDU.purdue.cs.bloat.reflect.*;

/**
 * <tt>PEVMClassInfoLoader</tt> interfaces with the PEVM to provide
 * access to classes residing the PEVM.
 */
public class PEVMClassInfoLoader implements ClassInfoLoader {
  public static boolean DEBUG = false;
  private static final int CACHE_LIMIT = 10;

  // The ClassLoader used to load classes that are BLOATed.  We assume
  // that the first time loadClass is called, the class that is named
  // is already loaded.  Subsequent calls to loadClass should use this
  // same ClassLoader to load classes.
  private ClassLoader classLoader;

  private LinkedList cache;

  private static void db(String s) {
    if(DEBUG)
      System.out.println(s);
  }

  public PEVMClassInfoLoader() {
    cache = new LinkedList();
    classLoader = ClassLoader.getSystemClassLoader();
  }

  /** 
   * Creates a <Tt>ClassInfo</tt> for a given class from information
   * found inside the PEVM.  The name of the class may be the name of
   * a class file (ending in .class), the dotted name of a class
   * (java.lang.String) or the internal slashed name of a class
   * (java/util/Vector).
   */
  public ClassInfo loadClass(String name) 
    throws ClassNotFoundException {

      db("PEVMClassInfoLoader: Loading " + name);

      // Sift out all of the cruft
      String slashName = new String(name);

      if(name.endsWith(".class")) {
	slashName = name.substring(0, name.lastIndexOf(".class"));
      }

      slashName = slashName.replace(File.separatorChar, '/');
      slashName = slashName.replace('.', '/');

      // Name with dots needed for Class.forName().  Grumble.
      String dottedName = slashName.replace('/', '.');

      db("PEVMClassInfoLoader: Loading " + dottedName + 
	 " from class loader " + classLoader);

      ClassInfo info;

      // Check for the ClassInfo in the cache
      Iterator iter = cache.iterator();
    
      while (iter.hasNext()) {
	info = (PEVMClassInfo) iter.next();
	
	if (slashName.equals(info.name())) {
	  db("PEVMClassInfoLoader: Found " + name + " in cache");
	  
	  // Move to the front of the cache.
	  iter.remove();
	  cache.addFirst(info);
	  
	  return info;
	}
      }

      // Load the class, but do not initialize it
      Class c = Class.forName(dottedName, false, classLoader);

      byte[] bytes = getBytesFromPEVM(c);
      DataInputStream stream = 
	new DataInputStream(new ByteArrayInputStream(bytes));

      info = new PEVMClassInfo(c, this, stream);

      if(info == null) {
	db("PEVMClassInfoLoader: " + name + " not found!");
	throw new ClassNotFoundException("Class " + name + 
					 " not found");
      }

      // If we've reached the cache size limit, remove the oldest info
      // in the cache.  Then add the new info.
      if (cache.size() == CACHE_LIMIT) {
	cache.removeLast();
      }
    
      cache.addFirst(info);

      return(info);
  }

  /**
   * Look inside the PEVM for the bytes constituting the classfile for
   * a given class.
   */
  public native byte[] getBytesFromPEVM(Class c);

  public String toString() {
    return("PEVM Class Info Loader");
  }
}
