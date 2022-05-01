/*

   Copyright 2000-2001  The Apache Software Foundation 

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
package org.apache.batik.dom;

import org.apache.batik.dom.util.HashTable;
import org.w3c.dom.Node;
import org.w3c.dom.stylesheets.StyleSheet;

/**
 * This interface represents a StyleSheet factory.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: StyleSheetFactory.java,v 1.4 2004/08/18 07:13:08 vhardy Exp $
 */
public interface StyleSheetFactory {
    /**
     * Creates a stylesheet from the data of the xml-stylesheet
     * processing instruction or return null when it is not possible
     * to create the given stylesheet.
     */
    StyleSheet createStyleSheet(Node node, HashTable pseudoAttrs);
}