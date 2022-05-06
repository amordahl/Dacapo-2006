
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderRightMaker extends  ListProperty.Maker {




    static public Property.Maker maker(String propName) {
	return new BorderRightMaker(propName);
    }

    protected BorderRightMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

}
