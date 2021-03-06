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
package org.apache.batik.bridge;


/**
 * This implementation does not allow any external resources to be 
 * referenced from an SVG document.
 *
 * @author <a href="mailto:vhardy@apache.org">Vincent Hardy</a>
 * @version $Id: NoLoadExternalResourceSecurity.java,v 1.6 2004/08/18 07:12:32 vhardy Exp $
 */
public class NoLoadExternalResourceSecurity implements ExternalResourceSecurity {
    /**
     * Message when trying to load an external resource
     */
    public static final String ERROR_NO_EXTERNAL_RESOURCE_ALLOWED
        = "NoLoadExternalResourceSecurity.error.no.external.resource.allowed";

    /**
     * The exception is built in the constructor and thrown if 
     * not null and the checkLoadExternalResource method is called.
     */
    protected SecurityException se;

    /**
     * Controls whether the external resource should be loaded or not.
     *
     * @throws SecurityException if the externalResource should not be loaded.
     */
    public void checkLoadExternalResource(){
        if (se != null) {
            se.fillInStackTrace();
            throw se;
        }
    }

    /**
     */
    public NoLoadExternalResourceSecurity(){
        se = new SecurityException
            (Messages.formatMessage(ERROR_NO_EXTERNAL_RESOURCE_ALLOWED,
                                    null));
        
    }
}


    
