/*

   Copyright 2002-2003  The Apache Software Foundation 

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
package org.apache.batik.css.engine.value.svg;

import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.ListValue;
import org.apache.batik.css.engine.value.URIValue;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.value.ValueManager;
import org.apache.batik.util.CSSConstants;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.DOMException;

/**
 * This class provides a manager for the SVGPaint property values.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: SVGPaintManager.java,v 1.7 2005/03/27 08:58:31 cam Exp $
 */
public class SVGPaintManager extends SVGColorManager {
    
    /**
     * Creates a new SVGPaintManager.
     */
    public SVGPaintManager(String prop) {
        super(prop);
    }

    /**
     * Creates a new SVGPaintManager.
     * @param prop The property name.
     * @param v The default value.
     */
    public SVGPaintManager(String prop, Value v) {
        super(prop, v);
    }

    /**
     * Implements {@link ValueManager#isInheritedProperty()}.
     */
    public boolean isInheritedProperty() {
	return true;
    }

    /**
     * Implements {@link ValueManager#createValue(LexicalUnit,CSSEngine)}.
     */
    public Value createValue(LexicalUnit lu, CSSEngine engine)
        throws DOMException {
	switch (lu.getLexicalUnitType()) {
        case LexicalUnit.SAC_IDENT:
            if (lu.getStringValue().equalsIgnoreCase
                (CSSConstants.CSS_NONE_VALUE)) {
                return SVGValueConstants.NONE_VALUE;
            }
            // Fall through
        default:
            return super.createValue(lu, engine);
        case LexicalUnit.SAC_URI:
        }
        String value = lu.getStringValue();
        String uri = resolveURI(engine.getCSSBaseURI(), value);
        lu = lu.getNextLexicalUnit();
        if (lu == null) {
            return new URIValue(value, uri);
        }

        ListValue result = new ListValue(' ');
        result.append(new URIValue(value, uri));

	if (lu.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
            if (lu.getStringValue().equalsIgnoreCase
                (CSSConstants.CSS_NONE_VALUE)) {
                result.append(SVGValueConstants.NONE_VALUE);
                return result;
            }
        }
        Value v = super.createValue(lu, engine);
        if (v.getCssValueType() == CSSValue.CSS_CUSTOM) {
            ListValue lv = (ListValue)v;
            for (int i = 0; i < lv.getLength(); i++) {
                result.append(lv.item(i));
            }
        } else {
            result.append(v);
        }
        return result;
    }

    /**
     * Implements {@link
     * ValueManager#computeValue(CSSStylableElement,String,CSSEngine,int,StyleMap,Value)}.
     */
    public Value computeValue(CSSStylableElement elt,
                              String pseudo,
                              CSSEngine engine,
                              int idx,
                              StyleMap sm,
                              Value value) {
        if (value == SVGValueConstants.NONE_VALUE) {
            return value;
        }
        if (value.getCssValueType() == CSSValue.CSS_VALUE_LIST) {
            ListValue lv = (ListValue)value;
            Value v = lv.item(0);
            if (v.getPrimitiveType() == CSSPrimitiveValue.CSS_URI) {
                v = lv.item(1);
                if (v == SVGValueConstants.NONE_VALUE) {
                    return value;
                }
                Value t = super.computeValue(elt, pseudo, engine, idx, sm, v);
                if (t != v) {
                    ListValue result = new ListValue(' ');
                    result.append(lv.item(0));
                    result.append(t);
                    if (lv.getLength() == 3) {
                        result.append(lv.item(1));
                    }
                    return result;
                }
                return value;
            }
        }
        return super.computeValue(elt, pseudo, engine, idx, sm, value);
    }
}
