/*

   Copyright 2001-2003  The Apache Software Foundation 

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
package org.apache.batik.bridge;

import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import org.apache.batik.ext.awt.LinearGradientPaint;
import org.apache.batik.ext.awt.MultipleGradientPaint;
import org.apache.batik.gvt.GraphicsNode;
import org.w3c.dom.Element;

/**
 * Bridge class for the &lt;linearGradient> element.
 *
 * @author <a href="mailto:tkormann@apache.org">Thierry Kormann</a>
 * @version $Id: SVGLinearGradientElementBridge.java,v 1.11 2004/08/18 07:12:35 vhardy Exp $
 */
public class SVGLinearGradientElementBridge
    extends AbstractSVGGradientElementBridge {

    /**
     * Constructs a new SVGLinearGradientElementBridge.
     */
    public SVGLinearGradientElementBridge() {}

    /**
     * Returns 'linearGradient'.
     */
    public String getLocalName() {
        return SVG_LINEAR_GRADIENT_TAG;
    }

    /**
     * Builds a linear gradient according to the specified parameters.
     *
     * @param paintElement the element that defines a Paint
     * @param paintedElement the element referencing the paint
     * @param paintedNode the graphics node on which the Paint will be applied
     * @param spreadMethod the spread method
     * @param colorSpace the color space (sRGB | LinearRGB)
     * @param transform the gradient transform
     * @param colors the colors of the gradient
     * @param offsets the offsets
     * @param ctx the bridge context to use
     */
    protected
        Paint buildGradient(Element paintElement,
                            Element paintedElement,
                            GraphicsNode paintedNode,
                            MultipleGradientPaint.CycleMethodEnum spreadMethod,
                            MultipleGradientPaint.ColorSpaceEnum colorSpace,
                            AffineTransform transform,
                            Color [] colors,
                            float [] offsets,
                            BridgeContext ctx) {

        // 'x1' attribute - default is 0%
        String x1Str = SVGUtilities.getChainableAttributeNS
            (paintElement, null, SVG_X1_ATTRIBUTE, ctx);
        if (x1Str.length() == 0) {
            x1Str = SVG_LINEAR_GRADIENT_X1_DEFAULT_VALUE;
        }

        // 'y1' attribute - default is 0%
        String y1Str = SVGUtilities.getChainableAttributeNS
            (paintElement, null, SVG_Y1_ATTRIBUTE, ctx);
        if (y1Str.length() == 0) {
            y1Str = SVG_LINEAR_GRADIENT_Y1_DEFAULT_VALUE;
        }

        // 'x2' attribute - default is 100%
        String x2Str = SVGUtilities.getChainableAttributeNS
            (paintElement, null, SVG_X2_ATTRIBUTE, ctx);
        if (x2Str.length() == 0) {
            x2Str = SVG_LINEAR_GRADIENT_X2_DEFAULT_VALUE;
        }

        // 'y2' attribute - default is 0%
        String y2Str = SVGUtilities.getChainableAttributeNS
            (paintElement, null, SVG_Y2_ATTRIBUTE, ctx);
        if (y2Str.length() == 0) {
            y2Str = SVG_LINEAR_GRADIENT_Y2_DEFAULT_VALUE;
        }

        // 'gradientUnits' attribute - default is objectBoundingBox
        short coordSystemType;
        String s = SVGUtilities.getChainableAttributeNS
            (paintElement, null, SVG_GRADIENT_UNITS_ATTRIBUTE, ctx);
        if (s.length() == 0) {
            coordSystemType = SVGUtilities.OBJECT_BOUNDING_BOX;
        } else {
            coordSystemType = SVGUtilities.parseCoordinateSystem
                (paintElement, SVG_GRADIENT_UNITS_ATTRIBUTE, s);
        }

        // additional transform to move to objectBoundingBox coordinate system
        if (coordSystemType == SVGUtilities.OBJECT_BOUNDING_BOX) {
            transform = SVGUtilities.toObjectBBox(transform, paintedNode);
        }
        UnitProcessor.Context uctx
            = UnitProcessor.createContext(ctx, paintElement);

        Point2D p1 = SVGUtilities.convertPoint(x1Str,
                                               SVG_X1_ATTRIBUTE,
                                               y1Str,
                                               SVG_Y1_ATTRIBUTE,
                                               coordSystemType,
                                               uctx);

        Point2D p2 = SVGUtilities.convertPoint(x2Str,
                                               SVG_X2_ATTRIBUTE,
                                               y2Str,
                                               SVG_Y2_ATTRIBUTE,
                                               coordSystemType,
                                               uctx);

	// If x1 = x2 and y1 = y2, then the area to be painted will be painted
	// as a single color using the color and opacity of the last gradient
	// stop.
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            return colors[colors.length-1];
	} else {
	    return new LinearGradientPaint(p1,
					   p2,
					   offsets,
					   colors,
					   spreadMethod,
					   colorSpace,
					   transform);
	}
    }
}
