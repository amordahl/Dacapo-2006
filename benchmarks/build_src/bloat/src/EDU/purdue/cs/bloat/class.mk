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

.SUFFIXES: .java .class

# JAVA_HOME = /gcm/where/jdk/1.3/sparc.Solaris
JAVAC = $(JAVA_HOME)/bin/javac
JFLAGS = -g
CLASSPATH = $(JAVA_HOME)/lib/classes.zip

all: class

clean:
	rm -f *.class

class:
	@files=`$(MAKE) -n _class | grep $(JAVAC) | cut -d' ' -f4`; \
	cpath=$(CLASSPATH):`(cd ../../../../..; pwd)`; \
	if [ "x$$files" != "x" ]; then \
	    echo $(JAVAC) $(JFLAGS) -classpath $$cpath $$files; \
	    $(JAVAC) $(JFLAGS) -classpath $$cpath $$files; \
	fi

_class: $(CLASS)

.java.class:
	cpath=$(CLASSPATH):`(cd ../../../../..; pwd)`; \
	$(JAVAC) -classpath $$cpath $<
