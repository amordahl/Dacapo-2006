/*

   Copyright 2000-2003  The Apache Software Foundation 

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

import org.apache.batik.dom.AbstractNode;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGZoomAndPan;

/**
 * This class provides support for SVGZoomAndPan features.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: SVGZoomAndPanSupport.java,v 1.6 2004/08/18 07:13:19 vhardy Exp $
 */
public class SVGZoomAndPanSupport implements SVGConstants {

    /**
     * This class does not need to be instantiated.
     */
    protected SVGZoomAndPanSupport() {
    }
    
    /**
     * Sets the zoomAndPan attribute value.
     */
    public static void setZoomAndPan(Element elt, short val)
	throws DOMException {
	switch (val) {
	case SVGZoomAndPan.SVG_ZOOMANDPAN_DISABLE:
	    elt.setAttributeNS(null, SVG_ZOOM_AND_PAN_ATTRIBUTE,
                               SVG_DISABLE_VALUE);
	    break;
	case SVGZoomAndPan.SVG_ZOOMANDPAN_MAGNIFY:
	    elt.setAttributeNS(null, SVG_ZOOM_AND_PAN_ATTRIBUTE,
                               SVG_MAGNIFY_VALUE);
	    break;
	default:
	    throw ((AbstractNode)elt).createDOMException
		(DOMException.INVALID_MODIFICATION_ERR,
		 "zoom.and.pane",
		 new Object[] { new Integer(val) });
	}
    }

    /**
     * Returns the ZoomAndPan attribute value.
     */
    public static short getZoomAndPan(Element elt) {
	String s = elt.getAttributeNS(null, SVG_ZOOM_AND_PAN_ATTRIBUTE);
	if (s.equals(SVG_MAGNIFY_VALUE)) {
	    return SVGZoomAndPan.SVG_ZOOMANDPAN_MAGNIFY;
	}
	return SVGZoomAndPan.SVG_ZOOMANDPAN_DISABLE;
    }
}
