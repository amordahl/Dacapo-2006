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
package org.apache.batik.css.parser;

import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.SimpleSelector;

/**
 * This class provides an implementation of the
 * {@link org.w3c.css.sac.ConditionalSelector} interface.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: DefaultConditionalSelector.java,v 1.3 2004/08/18 07:13:02 vhardy Exp $
 */
public class DefaultConditionalSelector implements ConditionalSelector {

    /**
     * The simple selector.
     */
    protected SimpleSelector simpleSelector;

    /**
     * The condition.
     */
    protected Condition condition;

    /**
     * Creates a new ConditionalSelector object.
     */
    public DefaultConditionalSelector(SimpleSelector s, Condition c) {
	simpleSelector = s;
	condition      = c;
    }

    /**
     * <b>SAC</b>: Implements {@link
     * org.w3c.css.sac.Selector#getSelectorType()}.
     */
    public short getSelectorType() {
	return SAC_CONDITIONAL_SELECTOR;
    }

    /**
     * <b>SAC</b>: Implements {@link
     * org.w3c.css.sac.ConditionalSelector#getSimpleSelector()}.
     */    
    public SimpleSelector getSimpleSelector() {
	return simpleSelector;
    }

    /**
     * <b>SAC</b>: Implements {@link
     * org.w3c.css.sac.ConditionalSelector#getCondition()}.
     */    
    public Condition getCondition() {
	return condition;
    }

    /**
     * Returns a representation of the selector.
     */
    public String toString() {
	return "" + simpleSelector + condition;
    }
}
