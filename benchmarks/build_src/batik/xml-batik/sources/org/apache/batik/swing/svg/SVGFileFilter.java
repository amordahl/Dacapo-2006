/*

   Copyright 2001,2003  The Apache Software Foundation 

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

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * This implementation of FileFilter will allows SVG files
 * with extention '.svg' or '.svgz'.
 *
 * @author <a href="mailto:vincent.hardy@eng.sun.com">Vincent Hardy</a>
 * @author <a href="mailto:cjolif@ilog.fr">Christophe Jolif</a>
 * @version $Id: SVGFileFilter.java,v 1.5 2005/03/27 08:58:36 cam Exp $
 */
public class SVGFileFilter extends FileFilter {
    /**
     * Returns true if <tt>f</tt> is an SVG file
     */
    public boolean accept(File f) {
        boolean accept = false;
        String fileName = null;
        if (f != null) {
            if (f.isDirectory()) {
                accept = true;
            } else {
                fileName = f.getPath().toLowerCase();
                if (fileName.endsWith(".svg") || fileName.endsWith(".svgz"))
                    accept = true;
            }
        }
        return accept;
    }

    /**
     * Returns the file description
     */
    public String getDescription() {
        return ".svg, .svgz";
    }
}
