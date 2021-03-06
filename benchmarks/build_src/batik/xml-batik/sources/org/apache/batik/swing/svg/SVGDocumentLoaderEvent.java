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
package org.apache.batik.swing.svg;

import java.util.EventObject;

import org.w3c.dom.svg.SVGDocument;

/**
 * This class represents an event which indicate an event originated
 * from a SVGDocumentLoader instance.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: SVGDocumentLoaderEvent.java,v 1.3 2004/08/18 07:15:35 vhardy Exp $
 */
public class SVGDocumentLoaderEvent extends EventObject {
    
    /**
     * The associated SVG document.
     */
    protected SVGDocument svgDocument;

    /**
     * Creates a new SVGDocumentLoaderEvent.
     * @param source the object that originated the event, ie. the
     *               SVGDocumentLoader.
     * @param doc The associated document.
     */
    public SVGDocumentLoaderEvent(Object source, SVGDocument doc) {
        super(source);
        svgDocument = doc;
    }

    /**
     * Returns the associated SVG document, or null if the loading
     * was just started or an error occured.
     */
    public SVGDocument getSVGDocument() {
        return svgDocument;
    }
}
