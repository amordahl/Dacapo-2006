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

import org.apache.batik.gvt.GraphicsNode;

/**
 * This class represents an event which indicate an event originated
 * from a GVTTreeBuilder instance.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: GVTTreeBuilderEvent.java,v 1.3 2004/08/18 07:15:35 vhardy Exp $
 */
public class GVTTreeBuilderEvent extends EventObject {
    
    /**
     * The GVT root.
     */
    protected GraphicsNode gvtRoot;

    /**
     * Creates a new GVTTreeBuilderEvent.
     * @param source the object that originated the event, ie. the
     *               GVTTreeBuilder.
     * @param root   the GVT root.
     */
    public GVTTreeBuilderEvent(Object source, GraphicsNode root) {
        super(source);
        gvtRoot = root;
    }

    /**
     * Returns the GVT tree root, or null if the gvt construction
     * was not completed or just started.
     */
    public GraphicsNode getGVTRoot() {
        return gvtRoot;
    }
}
