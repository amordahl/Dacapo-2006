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
package org.apache.batik.css.dom;

import org.apache.batik.css.engine.CSSEngine;
import org.apache.batik.css.engine.CSSStylableElement;
import org.apache.batik.css.engine.SVGCSSEngine;
import org.apache.batik.css.engine.value.Value;
import org.apache.batik.css.engine.value.svg.SVGColorManager;
import org.apache.batik.css.engine.value.svg.SVGPaintManager;
import org.w3c.dom.css.CSSValue;

/**
 * This class represents the computed style of an SVG element.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: CSSOMSVGComputedStyle.java,v 1.6 2004/08/18 07:12:47 vhardy Exp $
 */
public class CSSOMSVGComputedStyle extends CSSOMComputedStyle {
    
    /**
     * Creates a new computed style.
     */
    public CSSOMSVGComputedStyle(CSSEngine e,
                                 CSSStylableElement elt,
                                 String pseudoElt) {
        super(e, elt, pseudoElt);
    }

    /**
     * Creates a CSSValue to manage the value at the given index.
     */
    protected CSSValue createCSSValue(int idx) {
        if (idx > SVGCSSEngine.FINAL_INDEX) {
            if (cssEngine.getValueManagers()[idx] instanceof SVGPaintManager) {
                return new ComputedCSSPaintValue(idx);
            }
            if (cssEngine.getValueManagers()[idx] instanceof SVGColorManager) {
                return new ComputedCSSColorValue(idx);
            }
        } else {
            switch (idx) {
            case SVGCSSEngine.FILL_INDEX:
            case SVGCSSEngine.STROKE_INDEX:
                return new ComputedCSSPaintValue(idx);

            case SVGCSSEngine.FLOOD_COLOR_INDEX:
            case SVGCSSEngine.LIGHTING_COLOR_INDEX:
            case SVGCSSEngine.STOP_COLOR_INDEX:
                return new ComputedCSSColorValue(idx);
            }
        }
        return super.createCSSValue(idx);
    }

    /**
     * To manage a computed color CSSValue.
     */
    protected class ComputedCSSColorValue
        extends CSSOMSVGColor
        implements CSSOMSVGColor.ValueProvider {
        
        /**
         * The index of the associated value.
         */
        protected int index;

        /**
         * Creates a new ComputedCSSColorValue.
         */
        public ComputedCSSColorValue(int idx) {
            super(null);
            valueProvider = this;
            index = idx;
        }

        /**
         * Returns the Value associated with this object.
         */
        public Value getValue() {
            return cssEngine.getComputedStyle(element, pseudoElement, index);
        }
    }

    /**
     * To manage a computed paint CSSValue.
     */
    public class ComputedCSSPaintValue
        extends CSSOMSVGPaint
        implements CSSOMSVGPaint.ValueProvider {
        
        /**
         * The index of the associated value.
         */
        protected int index;

        /**
         * Creates a new ComputedCSSPaintValue.
         */
        public ComputedCSSPaintValue(int idx) {
            super(null);
            valueProvider = this;
            index = idx;
        }

        /**
         * Returns the Value associated with this object.
         */
        public Value getValue() {
            return cssEngine.getComputedStyle(element, pseudoElement, index);
        }
    }
}
