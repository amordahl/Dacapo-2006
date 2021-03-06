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
package org.apache.batik.apps.svgbrowser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * This class filters file for a given <tt>SquiggleInputHandler</tt>
 *
 * @author <a mailto="vincent.hardy@sun.com">Vincent Hardy</a>
 * @version $Id: SquiggleInputHandlerFilter.java,v 1.4 2004/08/18 07:12:27 vhardy Exp $
 */
public class SquiggleInputHandlerFilter extends FileFilter {
    protected SquiggleInputHandler handler;

    public SquiggleInputHandlerFilter(SquiggleInputHandler handler) {
        this.handler = handler;
    }

    public boolean accept(File f) {
        return f.isDirectory() || handler.accept(f);
    }

    public String getDescription() {
        StringBuffer sb = new StringBuffer();
        String extensions[] = handler.getHandledExtensions();
        int n = extensions != null ? extensions.length : 0;
        for (int i=0; i<n; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(extensions[i]);
        }

        if (n > 0) {
            sb.append(" ");
        }

        sb.append(handler.getDescription());
        return sb.toString();
    }
}
