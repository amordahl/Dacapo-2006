# All files in the distribution of BLOAT (Bytecode Level Optimization and
# Analysis tool for Java(tm)) are Copyright 1997-2001 by the Purdue
# Research Foundation of Purdue University.  All rights reserved.
#
# Redistribution and use in source and binary forms are permitted
# provided that this entire copyright notice is duplicated in all such
# copies, and that any documentation, announcements, and other
# materials related to such distribution and use acknowledge that the
# software was developed at Purdue University, West Lafayette, IN by
# Antony Hosking, David Whitlock, and Nathaniel Nystrom.  No charge
# may be made for copies, derivations, or distributions of this
# material without the express written consent of the copyright
# holder.  Neither the name of the University nor the name of the
# author may be used to endorse or promote products derived from this
# material without specific prior written permission.  THIS SOFTWARE
# IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED WARRANTIES,
# INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
# MERCHANTABILITY AND FITNESS FOR ANY PARTICULAR PURPOSE.
#
# Java is a trademark of Sun Microsystems, Inc.

CLASS = \
	PEVMClassInfoLoader.class \
	PEVMClassInfo.class

JNIH =  EDU_purdue_cs_bloat_pevm_PEVMClassInfoLoader.h \
	EDU_purdue_cs_bloat_pevm_PEVMClassInfo.h

OBJ = PEVMClassInfoLoader.o PEVMClassInfo.o

LIB = libBLOAT_PEVM.so

.SUFFIXES: .java .class .c .o

JDK_HOME = /proj/forest/java/bootstrap-EVM
JAVAC = $(JDK_HOME)/bin/javac
JAVAH = $(JDK_HOME)/bin/javah
JFLAGS = -g
BLOAT_HOME = /home/davewhit/bytecode/src

CC = /usr/dist/pkgs/devpro/5.x-sparc/bin/cc
CFLAGS = -I$(JDK_HOME)/include -I$(JDK_HOME)/include/solaris

all: class $(LIB)

clean:
	rm -f *.class *.o $(JNIH) $(OBJ) $(LIB)

class:
	@files=`$(MAKE) -n _class | grep javac | cut -d' ' -f4`; \
	cpath=$(CLASSPATH):`(cd ../../../../..; pwd)`; \
	if [ "x$$files" != "x" ]; then \
	    echo $(JAVAC) $(JFLAGS) -classpath $$cpath $$files; \
	    $(JAVAC) $(JFLAGS) -classpath $$cpath $$files; \
	fi

_class: $(CLASS)

.java.class:
	cpath=$(CLASSPATH):`(cd ../../../../..; pwd)`; \
	$(JAVAC) -classpath $$cpath $<

libBLOAT_PEVM.so: PEVMClassInfoLoader.o PEVMClassInfo.o
	$(CC) PEVMClassInfoLoader.o PEVMClassInfo.o -o libBLOAT_PEVM.so -G

EDU_purdue_cs_bloat_pevm_PEVMClassInfoLoader.h: PEVMClassInfoLoader.java
	cpath=$(CLASSPATH):`(cd ../../../../..; pwd)`; \
	$(JAVAH) -jni -classpath $$cpath \
	EDU.purdue.cs.bloat.pevm.PEVMClassInfoLoader

EDU_purdue_cs_bloat_pevm_PEVMClassInfo.h: PEVMClassInfo.java
	cpath=$(CLASSPATH):`(cd ../../../../..; pwd)`; \
	$(JAVAH) -jni -classpath $$cpath \
	EDU.purdue.cs.bloat.pevm.PEVMClassInfo

PEVMClassInfoLoader.o: PEVMClassInfoLoader.c EDU_purdue_cs_bloat_pevm_PEVMClassInfoLoader.h
	$(CC) -c $(CFLAGS) PEVMClassInfoLoader.c

PEVMClassInfo.o: PEVMClassInfo.c EDU_purdue_cs_bloat_pevm_PEVMClassInfo.h
	$(CC) -c $(CFLAGS) PEVMClassInfo.c