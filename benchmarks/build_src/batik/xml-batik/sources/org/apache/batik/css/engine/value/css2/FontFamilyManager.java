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
package org.apache.batik.css.engine.value.css2;

import org.apache.batik.css.engine.CSSContext;
import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.StyleMap;
import org.apache.batik.css.engine.value.AbstractValueManager;
import org.apache.batik.css.engine.value.ListValue;
import org.apache.batik.css.engine.value.StringMap;
import org.apache.batik.css.engine.value.StringValue;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.value.ValueConstants;
import org.apache.batik.css.engine.value.ValueManager;
import org.apache.batik.util.CSSConstants;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
 * This class provides a factory for the 'font-family' property values.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: FontFamilyManager.java,v 1.6 2005/03/27 08:58:31 cam Exp $
 */
public class FontFamilyManager extends AbstractValueManager {
    
    /**
     * The default value.
     */
    protected final static ListValue DEFAULT_VALUE = new ListValue();
    static {
        DEFAULT_VALUE.append
            (new StringValue(CSSPrimitiveValue.CSS_STRING,
                             "Arial"));
        DEFAULT_VALUE.append
            (new StringValue(CSSPrimitiveValue.CSS_STRING,
                             "Helvetica"));
        DEFAULT_VALUE.append
            (new StringValue(CSSPrimitiveValue.CSS_IDENT,
                             CSSConstants.CSS_SANS_SERIF_VALUE));
    }

    /**
     * The identifier values.
     */
    protected final static StringMap values = new StringMap();
    static {
	values.put(CSSConstants.CSS_CURSIVE_VALUE,
                   ValueConstants.CURSIVE_VALUE);
	values.put(CSSConstants.CSS_FANTASY_VALUE,
                   ValueConstants.FANTASY_VALUE);
	values.put(CSSConstants.CSS_MONOSPACE_VALUE,
                   ValueConstants.MONOSPACE_VALUE);
	values.put(CSSConstants.CSS_SERIF_VALUE,
                   ValueConstants.SERIF_VALUE);
	values.put(CSSConstants.CSS_SANS_SERIF_VALUE,
                   ValueConstants.SANS_SERIF_VALUE);
    }

    /**
     * Implements {@link ValueManager#isInheritedProperty()}.
     */
    public boolean isInheritedProperty() {
	return true;
    }

    /**
     * Implements {@link ValueManager#getPropertyName()}.
     */
    public String getPropertyName() {
	return CSSConstants.CSS_FONT_FAMILY_PROPERTY;
    }
    
    /**
     * Implements {@link ValueManager#getDefaultValue()}.
     */
    public Value getDefaultValue() {
        return DEFAULT_VALUE;
    }

    /**
     * Implements {@link ValueManager#createValue(LexicalUnit,CSSEngine)}.
     */
    public Value createValue(LexicalUnit lu, CSSEngine engine)
        throws DOMException {
        switch (lu.getLexicalUnitType()) {
        case LexicalUnit.SAC_INHERIT:
            return ValueConstants.INHERIT_VALUE;

        default:
            throw createInvalidLexicalUnitDOMException
                (lu.getLexicalUnitType());

        case LexicalUnit.SAC_IDENT:
        case LexicalUnit.SAC_STRING_VALUE:
        }
        ListValue result = new ListValue();
        for (;;) {
            switch (lu.getLexicalUnitType()) {
            case LexicalUnit.SAC_STRING_VALUE:
                result.append(new StringValue(CSSPrimitiveValue.CSS_STRING,
                                              lu.getStringValue()));
                lu = lu.getNextLexicalUnit();
                break;

            case LexicalUnit.SAC_IDENT:
                StringBuffer sb = new StringBuffer(lu.getStringValue());
                lu = lu.getNextLexicalUnit();
                if (lu != null &&
                    lu.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
                    do {
                        sb.append(' ');
                        sb.append(lu.getStringValue());
                        lu = lu.getNextLexicalUnit();
                    } while (lu != null &&
                             lu.getLexicalUnitType() == LexicalUnit.SAC_IDENT);
                    result.append(new StringValue(CSSPrimitiveValue.CSS_STRING,
                                                  sb.toString()));
                } else {
                    String id = sb.toString();
                    String s = id.toLowerCase().intern();
                    Value v = (Value)values.get(s);
                    result.append((v != null)
                                  ? v
                                  : new StringValue
                                        (CSSPrimitiveValue.CSS_STRING, id));
                }
            }
            if (lu == null) {
                return result;
            }
            if (lu.getLexicalUnitType() != LexicalUnit.SAC_OPERATOR_COMMA) {
                throw createInvalidLexicalUnitDOMException
                    (lu.getLexicalUnitType());
            }
            lu = lu.getNextLexicalUnit();
            if (lu == null) {
                throw createMalformedLexicalUnitDOMException();
            }
        }
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
        if (value == DEFAULT_VALUE) {
            CSSContext ctx = engine.getCSSContext();
            value = ctx.getDefaultFontFamily();
        }
        return value;
    }
}
