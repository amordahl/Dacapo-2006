/*

   Copyright 2001-2003  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.script.jacl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Locale;

import org.apache.batik.script.InterpreterException;

import tcl.lang.Interp;
import tcl.lang.ReflectObject;
import tcl.lang.TclException;

/**
 * A simple implementation of <code>Interpreter</code> interface to use
 * JACL Tcl parser.
 * @author <a href="mailto:cjolif@ilog.fr">Christophe Jolif</a>
 * @version $Id: JaclInterpreter.java,v 1.9 2004/08/18 07:14:56 vhardy Exp $
 */
public class JaclInterpreter implements org.apache.batik.script.Interpreter {
    private Interp interpreter = null;

    public JaclInterpreter() {
        interpreter = new Interp();
        try {
            interpreter.eval("package require java", 0);
        } catch (TclException e) {
        }
    }

    // org.apache.batik.script.Intepreter implementation

    public Object evaluate(Reader scriptreader)
        throws InterpreterException, IOException {
        return evaluate(scriptreader, "");
    }

    public Object evaluate(Reader scriptreader, String description) throws IOException, InterpreterException {
        // oups jacl doesn't accept reader in its eval method :-(
        StringBuffer sbuffer = new StringBuffer();
        char[] buffer = new char[1024];
        int val = 0;
        while ((val = scriptreader.read(buffer)) != -1) {
            sbuffer.append(buffer, 0, val);
        }
        String str = sbuffer.toString();
        return evaluate(str);
    }

    public Object evaluate(String script)
        throws InterpreterException {
        try {
            interpreter.eval(script, 0);
        } catch (TclException e) {
            throw new InterpreterException(e, e.getMessage(), -1, -1);
        } catch (RuntimeException re) {
            throw new InterpreterException(re, re.getMessage(), -1, -1);
        }
        return interpreter.getResult();
    }

    public void dispose() {
        interpreter.dispose();
    }

    public void bindObject(String name, Object object) {
        try {
            interpreter.
                setVar(name,
                       ReflectObject.
                       newInstance(interpreter, object.getClass(), object),
                       0);
        } catch (TclException e) {
            // should not happened we just register an object
        }
    }

    public void setOut(Writer out) {
        // no implementation of a default output function in Jacl
    }

    // org.apache.batik.i18n.Localizable implementation

    public Locale getLocale() {
        return Locale.getDefault();
    }

    public void setLocale(Locale locale) {
    }

    public String formatMessage(String key, Object[] args) {
        return null;
    }
}
