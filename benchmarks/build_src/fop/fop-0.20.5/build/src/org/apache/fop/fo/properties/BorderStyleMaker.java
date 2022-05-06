
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderStyleMaker extends  ListProperty.Maker {




    static public Property.Maker maker(String propName) {
	return new BorderStyleMaker(propName);
    }

    protected BorderStyleMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

}
