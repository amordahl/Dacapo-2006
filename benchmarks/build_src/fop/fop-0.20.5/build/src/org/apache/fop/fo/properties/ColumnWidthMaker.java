
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class ColumnWidthMaker extends  LengthProperty.Maker {




    static public Property.Maker maker(String propName) {
	return new ColumnWidthMaker(propName);
    }

    protected ColumnWidthMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property make(PropertyList propertyList) throws FOPException {
      
        return make(propertyList, "proportional-column-width(1)", propertyList.getParentFObj());
	
    }

}
