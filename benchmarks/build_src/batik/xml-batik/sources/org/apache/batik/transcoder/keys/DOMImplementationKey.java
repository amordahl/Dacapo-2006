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
package org.apache.batik.transcoder.keys;

import org.apache.batik.transcoder.TranscodingHints;
import org.w3c.dom.DOMImplementation;

/**
 * A transcoding Key represented as a DOMImplementation.
 *
 * @author <a href="mailto:Thierry.Kormann@sophia.inria.fr">Thierry Kormann</a>
 * @version $Id: DOMImplementationKey.java,v 1.3 2004/08/18 07:15:44 vhardy Exp $
 */
public class DOMImplementationKey extends TranscodingHints.Key {

    public boolean isCompatibleValue(Object v) {
        return (v instanceof DOMImplementation);
    }
}
