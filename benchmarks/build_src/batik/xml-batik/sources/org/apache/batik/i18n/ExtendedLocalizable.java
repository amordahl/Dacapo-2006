/*

   Copyright 2000  The Apache Software Foundation 

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
package org.apache.batik.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This interface provides much more control over internationalization
 * than the Localizable interface.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: ExtendedLocalizable.java,v 1.3 2004/08/18 07:14:44 vhardy Exp $
 */
public interface ExtendedLocalizable extends Localizable {
    /**
     * Sets the group to which this object belongs.
     */
    void setLocaleGroup(LocaleGroup lg);

    /**
     * Returns the group to which this object belongs.
     */
    LocaleGroup getLocaleGroup();

    /**
     * Sets the default locale for all the instances of this class in
     * the same LocaleGroup. 
     */
    void setDefaultLocale(Locale l);

    /**
     * Gets the current default locale in the LocaleGroup.
     */
    Locale getDefaultLocale();

    /**
     * Returns the current resource bundle. Getting this object gives access
     * to the keys in the bundle, raw string resources, arrays of raw string
     * resources and object resources.
     */
    ResourceBundle getResourceBundle();
}
