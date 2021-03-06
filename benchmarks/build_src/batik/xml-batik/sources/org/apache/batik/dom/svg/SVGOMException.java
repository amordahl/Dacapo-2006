/*

   Copyright 2002  The Apache Software Foundation 

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
package org.apache.batik.dom.svg;

import org.w3c.dom.svg.SVGException;

/**
 * An implementation of the SVGException class.
 *
 * @author <a href="mailto:tkormann@ilog.fr">Thierry Kormann</a>
 * @version $Id: SVGOMException.java,v 1.3 2004/08/18 07:13:15 vhardy Exp $
 */
public class SVGOMException extends SVGException {

    /**
     * Constructs a new <tt>SVGOMException</tt> with the specified parameters.
     *
     * @param code the exception code
     * @param message the error message
     */
    public SVGOMException(short code, String message) {
        super(code, message);
    }
}
