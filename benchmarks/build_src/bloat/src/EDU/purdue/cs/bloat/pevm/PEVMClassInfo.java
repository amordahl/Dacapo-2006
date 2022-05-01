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

import EDU.purdue.cs.bloat.file.*;
import EDU.purdue.cs.bloat.reflect.*;
import EDU.purdue.cs.bloat.util.*;

/** 
 * <tt>PEVMClassInfo</tt> represents a class found inside the PEVM.
 * It behaves a lot like a <tt>bloat.file.ClassFile</tt> except that
 * it has additional hidden mechanisms to work with the PEVM.
 */
public class PEVMClassInfo implements ClassInfo {
  public static final boolean    DEBUG = false;

  private Class                  me;       // Class this represents
  private PEVMClassInfoLoader    loader;
  private Constant[]             constants;
  private int                    modifiers;
  private int                    thisClass;
  private int                    superClass;
  private int[]                  interfaces;
  private Field[]                fields;
  private Method[]               methods;
  private Attribute[]            attrs;

  private static void db(String s) {
    System.out.println(s);
  }

  /**
   * Constructor.  Parses a class file from a <Tt>DataInputStream</tt>
   * and creates the appropriate objects to model it.
   */
  public PEVMClassInfo(Class me, PEVMClassInfoLoader loader, 
		       DataInputStream in) {
    this.me = me;
    this.loader = loader;

    // Read in file contents from stream
    try {
      if(DEBUG) {
	System.out.println("PEVMClassInfo: Loading " + me.getName());
	System.out.println("  Reading header");
      }
      readHeader(in);

      if(DEBUG)
	System.out.println("  Reading constant pool");
      readConstantPool(in);

      if(DEBUG)
	System.out.println("  Reading access flags");
      readAccessFlags(in);

      if(DEBUG)
	System.out.println("  Reading class info");
      readClassInfo(in);

      if(DEBUG)
	System.out.println("  Reading fields");
      readFields(in);

      if(DEBUG)
	System.out.println("  Reading methods");
      readMethods(in);

      if(DEBUG)
	System.out.println("  Reading Attributes");
      readAttributes(in);
      in.close();

    } catch (IOException e) {
      throw new ClassFormatException(e.getMessage());
    }
  }

  /**
   * Returns the index of the <tt>Constant</tt> in this class's
   * constant pool representing information about this class.
   */
  public int classIndex() {
    return(thisClass);
  }

  /**
   * Commits changes made to this method back to the data structures
   * maintained within the PEVM.
   */
  public void commit() {
    // Luckily, this version of BLOAT only changes the following:
    //   * Methods
    //     - Code (duh)
    //     - Method size (number of instructions)
    //     - Maximum number of local variables
    //     - Maximum stack size
    //   * Try-catch blocks
    //     - Starting PC of protected region
    //     - Ending PC of protected region
    //     - PC of handler

    db("Begin committing " + this.name());

    // Write bytecodes back to PEVM
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(byteStream);

    try {
      writeHeader(out);
      writeConstantPool(out);
      writeAccessFlags(out);
      writeClassInfo(out);
      writeFields(out);
      writeMethods(out);
      writeAttributes(out);
      
      out.close();

    } catch(IOException ex) {
      System.err.println("An IOException occurred while committing " +
			 this.name() + " to the PEVM");
      System.err.println("  " + ex);
      System.err.println("Aborting commit");
      System.exit(1);
    }

    // Update PEVM's internal representation
    this.updateClassInPEVM();

    db("Done updating " + this.name());
  }

  /**
   * Writes the contents of the class represented by this
   * PEVMClassInfo to a file on disk.  This classfile is placed in a
   * given directory.
   */
  public void commitToDir(File dir) {
    /* Make sure dir is valid */
    if(dir.exists()) {
      if(!dir.isDirectory()) {
	System.err.println(dir + " is not a valid directory");
	System.err.println("Not committing " + this.name());
	return;
      }
      
    } else {
      dir.mkdirs();
    }

    /* Construct the classfile */
    String className = new String(this.name());
    String packages = className.substring(0,
					  className.lastIndexOf('/'));
    String dirName = packages.replace('/', File.separatorChar);
    String fileName = className.substring(className.lastIndexOf('/') +
					  1);
    File outDir = new File(dir, dirName);
    if(outDir.exists()) {
      if(!outDir.isDirectory()) {
	System.err.println(outDir + " is not a valid directory");
	System.err.println("Not committing " + this.name());
	return;
      }

    } else {
      outDir.mkdirs();
    }

    File outputFile = new File(outDir, fileName + ".class");
    System.out.println("Committing " + this.name() + " to " +
		       outputFile);

    try {
      FileOutputStream fileStream = new FileOutputStream(outputFile);
      DataOutputStream out = new DataOutputStream(fileStream);

      writeHeader(out);
      writeConstantPool(out);
      writeAccessFlags(out);
      writeClassInfo(out);
      writeFields(out);
      writeMethods(out);
      writeAttributes(out);
      
      out.close();

    } catch(IOException ex) {
      System.err.println("An IOException occurred while committing " +
			 this.name() + " to " + outputFile);
      System.err.println("  " + ex);
      System.err.println("Aborting commit");
      System.exit(1);
    }    

  }

  /**  
   * Updates the internal representation of the class represented by
   * this <tt>PEVMClassInfo</tt>.  Each of the class's methods' max
   * stack size, max number of local variables, and try-catch blocks
   * are modified.
   */
  private void updateClassInPEVM() {
    MethodInfo[] methods = this.methods();
    for(int i = 0 ; i < methods.length; i++) {
      updateMethodInPEVM0(methods[i]);
    }
  }

  /** 
   * Updates the internal representation of a method in the class
   * represented by this <tt>PEVMClassInfo</tt>.  The method's max
   * stack size, max number of local variables, and try-catch blocks
   * are modified.
   */
  public void updateMethodInPEVM(MethodInfo method) {
    // Make sure that this method is indeed a method of this class.
    Assert.isTrue(method.declaringClass() == this);

    updateMethodInPEVM0(method);

  }

  private native void updateMethodInPEVM0(MethodInfo method);

  /**
   * Displays the PEVM's representation of this class.
   */
  public native void printClassInPEVM();

  /**
   * Returns the constants in this class's constant pool.
   */
  public Constant[] constants() {
    return(constants);
  }

  /**
   * Returns information about this class's fields.
   */
  public FieldInfo[] fields() {
    return(fields);
  }

  /**
   * Returns the indices of entries in the constant pool that
   * describe the interfaces that this class implements.
   */
  public int[] interfaceIndices() {
    return(interfaces);
  }

  /**
   * Returns the loader that was used to load information from the
   * PEVM into this <tt>PEVMClassInfo</tt>.
   */
  public ClassInfoLoader loader() {
    return(loader);
  }

  /**
   * Returns information describing this class's methods.
   */
  public MethodInfo[] methods() {
    return(methods);
  }

  /**
   * Returns this classes modifier flags.
   *
   * @see EDU.purdue.cs.bloat.reflect.Modifiers
   */
  public int modifiers() {
    return(modifiers);
  }

  /** 
   * Returns the name of this class as it appears in its class file.
   * That is, it returns the String value of the UTF8 constant at
   * index <tt>classIndex</tt>.
   */
  public String name() {
    Constant c = constants[thisClass];
    if (c.tag() == Constant.CLASS) {
      Integer nameIndex = (Integer) c.value();
      if (nameIndex != null) {
	c = constants[nameIndex.intValue()];
	if (c.tag() == Constant.UTF8) {
	  return (String) c.value();
	}
      }
    }
    
    throw new ClassFormatException("Couldn't find class name in file"); 
  }

  /**
   * Sets the value of the class's index in the constant pool.
   * Changes are not propagated back to the PEVM.
   */
  public void setClassIndex(int index) {
    this.thisClass = index;
  }

  /**
   * Sets the values of the constants in the constant pool.  Changes
   * are not propagated back to the PEVM.
   */
  public void setConstants(Constant[] constants) {
    this.constants = constants;
  }

  /** 
   * Sets the indices in the constant pool that describe the
   * interfaces implemented by this class.  Changes are not propagated
   * back to the PEVM.
   */
  public void setInterfaceIndices(int[] indices) {
    this.interfaces = indices;
  }

  /**
   * Sets the modifier flags for this class.  Changes are not
   * propagated back to the PEVM.
   */
  public void setModifiers(int modifiers) {
    this.modifiers = modifiers;
  }

  /** 
   * Sets the index in the constant pool that describes this class's
   * superclass.  Changes are not propagated back to the PEVM.
   */
  public void setSuperclassIndex(int index) {
    this.superClass = index;
  }

  /**
   * Returns the index in the constant pool of the entry that
   * describes this class's superclass.
   */
  public int superclassIndex() {
    return(this.superClass);
  }

  /**
   * Commits the changes made to this class back to a
   * <Tt>DataOutputStream</tt>. 
   */
  private void commitToStream(DataOutputStream out) {

    try {
      writeHeader(out);
      writeConstantPool(out);
      writeAccessFlags(out);
      writeClassInfo(out);
      writeFields(out);
      writeMethods(out);
      writeAttributes(out);
      
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Write the class file header.
   */
  private void writeHeader(DataOutputStream out)
    throws IOException {
      out.writeInt(0xCAFEBABE);
      out.writeShort(3);
      out.writeShort(45);
  }
  
  /**
   * Write the class's constant pool.
   */
  private void writeConstantPool(DataOutputStream out)
    throws IOException {
      out.writeShort(constants.length);
    
      // Write the constants.  The first constant is reserved for
      // internal use by the JVM, so start at 1.
      for (int i = 1; i < constants.length; i++) {
	writeConstant(out, constants[i]);
      
	switch (constants[i].tag()) {
	case Constant.LONG:
	case Constant.DOUBLE:
	  // Longs and doubles take up 2 constant pool entries.
	  i++;
	  break;
	}
      }
  }
  
  /**
   * Read a constant from the constant pool.
   */
  private Constant readConstant(DataInputStream in)
    throws IOException {
      int tag = in.readUnsignedByte();
      Object value;
    
      switch (tag) {
      case Constant.CLASS:
      case Constant.STRING:
	value = new Integer(in.readUnsignedShort());
	break;
      case Constant.FIELD_REF:
      case Constant.METHOD_REF:
      case Constant.INTERFACE_METHOD_REF:
      case Constant.NAME_AND_TYPE:
	value = new int[2];
	((int[]) value)[0] = in.readUnsignedShort();
	((int[]) value)[1] = in.readUnsignedShort();
	break;
      case Constant.INTEGER:
	value = new Integer(in.readInt());
	break;
      case Constant.FLOAT:
	value = new Float(in.readFloat());
	break;
      case Constant.LONG:
	// Longs take up 2 constant pool entries.
	value = new Long(in.readLong());
	break;
      case Constant.DOUBLE:
	// Doubles take up 2 constant pool entries.
	value = new Double(in.readDouble());
	break;
      case Constant.UTF8:
	value = in.readUTF();
	break;
      default:
	throw new ClassFormatException(this.name() +
				       ": Invalid constant tag: " + tag);
      }
    
      return new Constant(tag, value);
  }
  
  /**
   * Write a constant in the constant pool.
   */
  private void writeConstant(DataOutputStream out, Constant constant)
    throws IOException {
      int tag = constant.tag();
      Object value = constant.value();
    
      out.writeByte(tag);
    
      switch (tag) {
      case Constant.CLASS:
      case Constant.STRING:
	out.writeShort(((Integer) value).intValue());
	break;
      case Constant.INTEGER:
	out.writeInt(((Integer) value).intValue());
	break;
      case Constant.FLOAT:
	out.writeFloat(((Float) value).floatValue());
	break;
      case Constant.LONG:
	out.writeLong(((Long) value).longValue());
	break;
      case Constant.DOUBLE:
	out.writeDouble(((Double) value).doubleValue());
	break;
      case Constant.UTF8:
	out.writeUTF((String) value);
	break;
      case Constant.FIELD_REF:
      case Constant.METHOD_REF:
      case Constant.INTERFACE_METHOD_REF:
      case Constant.NAME_AND_TYPE:
	out.writeShort(((int[]) value)[0]);
	out.writeShort(((int[]) value)[1]);
	break;
      }
  }
  
  /**
   * Write the class's access flags.
   */
  private void writeAccessFlags(DataOutputStream out)
    throws IOException {
      out.writeShort(modifiers);
  }
  
  /**
   * Write the class's name, superclass, and interfaces.
   */
  private void writeClassInfo(DataOutputStream out)
    throws IOException {
      int index;
    
      out.writeShort(thisClass);
      out.writeShort(superClass);
    
      out.writeShort(interfaces.length);
    
      for (int i = 0; i < interfaces.length; i++) {
	out.writeShort(interfaces[i]);
      }
  }
  
  /**
   * Write the class's fields.
   */
  private void writeFields(DataOutputStream out)
    throws IOException {
      out.writeShort(fields.length);
    
      for (int i = 0; i < fields.length; i++) {
	fields[i].write(out);
      }
  }
  
  /**
   * Write the class's methods.
   */
  private void writeMethods(DataOutputStream out)
    throws IOException {
      out.writeShort(methods.length);
    
      for (int i = 0; i < methods.length; i++) {
	methods[i].write(out);
      }
  }
  
  /**
   * Write the class's attributes.  No attributes are written
   * by this method since none are required.
   */
  private void writeAttributes(DataOutputStream out)
    throws IOException
  {
    out.writeShort(attrs.length);
    
    for (int i = 0; i < attrs.length; i++) {
      out.writeShort(attrs[i].nameIndex());
      out.writeInt(attrs[i].length());
      attrs[i].writeData(out);
    }
  }
  
  /**
   * Read the class file header.
   */
  private void readHeader(DataInputStream in)
    throws IOException {
      int magic = in.readInt();
    
      if (magic != 0xCAFEBABE) {
	throw new ClassFormatError("Bad magic number.");
      }
    
      int major = in.readUnsignedShort();
      int minor = in.readUnsignedShort();
  }
  
  /**
   * Read the class's constant pool.  Constants in the constant pool
   * are modeled by an array of <tt>reflect.Constant</tt>/
   */
  private void readConstantPool(DataInputStream in)
    throws IOException {
      int count = in.readUnsignedShort();
    
      constants = new Constant[count];
    
      // The first constant is reserved for internal use by the JVM.
      constants[0] = null;
    
      // Read the constants.
      for (int i = 1; i < count; i++) {
	constants[i] = readConstant(in);
      
	switch (constants[i].tag()) {
	case Constant.LONG:
	case Constant.DOUBLE:
	  // Longs and doubles take up 2 constant pool entries.
	  constants[++i] = null;
	  break;
	}
      }
  }
  
  /**
   * Read the class's access flags.
   */
  private void readAccessFlags(DataInputStream in)
    throws IOException {
      modifiers = in.readUnsignedShort();
  }
  
  /**
   * Read the class's name, superclass, and interfaces.
   */
  private void readClassInfo(DataInputStream in)
    throws IOException {
      int index;
    
      thisClass = in.readUnsignedShort();
      superClass = in.readUnsignedShort();
    
      int numInterfaces = in.readUnsignedShort();
    
      interfaces = new int[numInterfaces];
    
      for (int i = 0; i < numInterfaces; i++) {
	interfaces[i] = in.readUnsignedShort();
      }
  }
  
  /**
   * Read the class's fields.
   */
  private void readFields(DataInputStream in)
    throws IOException {
      int numFields = in.readUnsignedShort();
    
      fields = new Field[numFields];
    
      for (int i = 0; i < numFields; i++) {
	fields[i] = new Field(in, this);
      }
  }
  
  /**
   * Read the class's methods.
   */
  private void readMethods(DataInputStream in)
    throws IOException {
      int numMethods = in.readUnsignedShort();
    
      methods = new Method[numMethods];
    
      for (int i = 0; i < numMethods; i++) {
	methods[i] = new Method(in, this);
      }
  }
  
  /**
   * Read the class's attributes.  Since none of the attributes
   * are required, just read the length of each attribute and
   * skip that many bytes.
   */
  private void readAttributes(DataInputStream in)
    throws IOException {
      int numAttributes = in.readUnsignedShort();
    
      attrs = new Attribute[numAttributes];
    
      for (int i = 0; i < numAttributes; i++) {
	int nameIndex = in.readUnsignedShort();
	int length = in.readInt();
	attrs[i] = new GenericAttribute(in, nameIndex, length);
      }
  }

  /**
   * Prints a textual representation of this classfile to a PrintStream.
   * The text includes information such as the constants in the constant
   * pool, the name of the class's superclass, its modifier flags, its
   * fields, and its methods.
   */
  public void print(PrintWriter out) {
    out.print("(constants");
    for (int i = 0; i < constants.length; i++) {
      out.print("\n    " + i + ": " + constants[i]);
    }
    out.println(")");
    
    out.println("(class " + classIndex() + ")");
    out.println("(super " + superclassIndex() + ")");
    
    out.print("(interfaces");
    for (int i = 0; i < interfaces.length; i++) {
      out.print("\n    " + i + ": " + interfaces[i]);
    }
    out.println(")");
    
    out.print("(modifiers");
    if ((modifiers & Modifiers.PUBLIC) != 0)
      out.print(" PUBLIC");
    if ((modifiers & Modifiers.FINAL) != 0)
      out.print(" FINAL");
    if ((modifiers & Modifiers.SUPER) != 0)
      out.print(" SUPER");
    if ((modifiers & Modifiers.INTERFACE) != 0)
      out.print(" INTERFACE");
    if ((modifiers & Modifiers.ABSTRACT) != 0)
      out.print(" ABSTRACT");
    out.println(")");
    
    out.print("(fields");
    for (int i = 0; i < fields.length; i++) {
      out.print("\n    " + i + ": " + fields[i]);
    }
    out.println(")");
    
    out.print("(methods");
    for (int i = 0; i < methods.length; i++) {
      out.print("\n    " + i + ": " + methods[i]);
    }
    out.println(")");
  }

  public void print(PrintStream out) {
    print(new PrintWriter(out, true));
  }

  /**
   * Returns a textual representation of this PEVMClassInfo.
   */
  public String toString() {
    return("(PEVMClassInfo " + this.name() + ")");
  }
}
