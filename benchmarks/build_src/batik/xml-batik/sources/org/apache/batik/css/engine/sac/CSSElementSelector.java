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
package org.apache.batik.css.engine.sac;

import org.w3c.dom.Element;

/**
 * This class implements the {@link org.w3c.css.sac.ElementSelector} interface.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: CSSElementSelector.java,v 1.4 2005/03/29 10:48:02 deweese Exp $
 */
public class CSSElementSelector extends AbstractElementSelector {

    /**
     * Creates a new ElementSelector object.
     */
    public CSSElementSelector(String uri, String name) {
	super(uri, name);
    }

    /**
     * <b>SAC</b>: Implements {@link
     * org.w3c.css.sac.Selector#getSelectorType()}.
     */
    public short getSelectorType() {
	return SAC_ELEMENT_NODE_SELECTOR;
    }

    /**
     * Tests whether this selector matches the given element.
     */
    public boolean match(Element e, String pseudoE) {
	String name = getLocalName();
	if (name == null) {
	    return true;
	}
        String eName;
        if (e.getPrefix() == null) eName = e.getNodeName();
        else                       eName = e.getLocalName();
        // According to CSS 2 section 5.1 element
        // names in selectors are case-sensitive for XML.
        return eName.equals(name);
        // For HTML
        // return eName.equalsIgnoreCase(name);
    }

    /**
     * Returns the specificity of this selector.
     */
    public int getSpecificity() {
	return (getLocalName() == null) ? 0 : 1;
    }

    /**
     * Returns a representation of the selector.
     */
    public String toString() {
	String name = getLocalName();
	if (name == null) {
	    return "*";
	}
	return name;
    }
}
