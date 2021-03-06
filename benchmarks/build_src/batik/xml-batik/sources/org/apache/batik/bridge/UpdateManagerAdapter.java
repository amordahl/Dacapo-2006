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
package org.apache.batik.bridge;

/**
 * This is an adapter for the UpdateManagerListener interface.
 * It's methods do nothing.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: UpdateManagerAdapter.java,v 1.2 2004/08/18 07:12:36 vhardy Exp $
 */
public class UpdateManagerAdapter implements UpdateManagerListener {

    /**
     * Called when the manager was started.
     */
    public void managerStarted(UpdateManagerEvent e) { }

    /**
     * Called when the manager was suspended.
     */
    public void managerSuspended(UpdateManagerEvent e) { }
    
    /**
     * Called when the manager was resumed.
     */
    public void managerResumed(UpdateManagerEvent e) { }

    /**
     * Called when the manager was stopped.
     */
    public void managerStopped(UpdateManagerEvent e) { }

    /**
     * Called when an update started.
     */
    public void updateStarted(UpdateManagerEvent e) { }

    /**
     * Called when an update was completed.
     */
    public void updateCompleted(UpdateManagerEvent e) { }

    /**
     * Called when an update failed.
     */
    public void updateFailed(UpdateManagerEvent e) { }

}
