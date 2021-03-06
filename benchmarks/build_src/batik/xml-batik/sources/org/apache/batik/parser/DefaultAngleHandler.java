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
 * This class provides an adapter for AngleHandler
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: DefaultAngleHandler.java,v 1.3 2004/08/18 07:14:46 vhardy Exp $
 */
public class DefaultAngleHandler implements AngleHandler {
    /**
     * The only instance of this class.
     */
    public final static AngleHandler INSTANCE
        = new DefaultAngleHandler();

    /**
     * This class does not need to be instantiated.
     */
    protected DefaultAngleHandler() {
    }

    /**
     * Implements {@link AngleHandler#startAngle()}.
     */
    public void startAngle() throws ParseException {
    }

    /**
     * Implements {@link AngleHandler#angleValue(float)}.
     */
    public void angleValue(float v) throws ParseException {
    }

    /**
     * Implements {@link AngleHandler#deg()}.
     */
    public void deg() throws ParseException {
    }

    /**
     * Implements {@link AngleHandler#grad()}.
     */
    public void grad() throws ParseException {
    }

    /**
     * Implements {@link AngleHandler#rad()}.
     */
    public void rad() throws ParseException {
    }

    /**
     * Implements {@link AngleHandler#endAngle()}.
     */
    public void endAngle() throws ParseException {
    }
}
