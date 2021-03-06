/*

   Copyright 2001  The Apache Software Foundation 

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
package org.apache.batik.parser;

/**
 * This class provides an adapter for LengthHandler
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: DefaultLengthHandler.java,v 1.3 2004/08/18 07:14:46 vhardy Exp $
 */
public class DefaultLengthHandler implements LengthHandler {
    /**
     * The only instance of this class.
     */
    public final static LengthHandler INSTANCE = new DefaultLengthHandler();

    /**
     * This class does not need to be instantiated.
     */
    protected DefaultLengthHandler() {
    }

    /**
     * Implements {@link LengthHandler#startLength()}.
     */
    public void startLength() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#lengthValue(float)}.
     */
    public void lengthValue(float v) throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#em()}.
     */
    public void em() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#ex()}.
     */
    public void ex() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#in()}.
     */
    public void in() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#cm()}.
     */
    public void cm() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#mm()}.
     */
    public void mm() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#pc()}.
     */
    public void pc() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#pt()}.
     */
    public void pt() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#px()}.
     */
    public void px() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#percentage()}.
     */
    public void percentage() throws ParseException {
    }

    /**
     * Implements {@link LengthHandler#endLength()}.
     */
    public void endLength() throws ParseException {
    }
}
