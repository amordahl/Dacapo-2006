/*

   Copyright 2003  The Apache Software Foundation 

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

import java.awt.geom.AffineTransform;

import org.w3c.dom.DOMException;
import org.w3c.dom.svg.SVGMatrix;

/**
 * This class is the implementation of
 * the SVGTransform interface.
 *
 * Create an identity SVGTransform
 *
 * @author <a href="mailto:nicolas.socheleau@bitflash.com">Nicolas Socheleau</a>
 * @version $Id: SVGOMTransform.java,v 1.4 2004/08/18 07:13:18 vhardy Exp $
 */
public class SVGOMTransform extends AbstractSVGTransform {


    public SVGOMTransform(){
        super();
        affineTransform = new AffineTransform();
    }

    protected SVGMatrix createMatrix(){
        return new AbstractSVGMatrix(){
                protected AffineTransform getAffineTransform(){
                    return SVGOMTransform.this.affineTransform;
                }

                public void setA(float a) throws DOMException {
                    SVGOMTransform.this.setType(SVG_TRANSFORM_MATRIX);
                    super.setA(a);
                }
                public void setB(float b) throws DOMException {
                    SVGOMTransform.this.setType(SVG_TRANSFORM_MATRIX);
                    super.setB(b);
                }
                public void setC(float c) throws DOMException {
                    SVGOMTransform.this.setType(SVG_TRANSFORM_MATRIX);
                    super.setC(c);
                }
                public void setD(float d) throws DOMException {
                    SVGOMTransform.this.setType(SVG_TRANSFORM_MATRIX);
                    super.setD(d);
                }
                public void setE(float e) throws DOMException {
                    SVGOMTransform.this.setType(SVG_TRANSFORM_MATRIX);
                    super.setE(e);
                }
                public void setF(float f) throws DOMException {
                    SVGOMTransform.this.setType(SVG_TRANSFORM_MATRIX);
                    super.setF(f);
                }
            };
    }
}
