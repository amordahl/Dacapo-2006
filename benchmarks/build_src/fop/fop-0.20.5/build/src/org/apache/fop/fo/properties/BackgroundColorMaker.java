
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BackgroundColorMaker extends  GenericColor {




    static public Property.Maker maker(String propName) {
	return new BackgroundColorMaker(propName);
    }

    protected BackgroundColorMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "transparent", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

    // See if other value types are acceptable
    protected Property convertPropertyDatatype(Property p,
	PropertyList propertyList, FObj fo) {
      
      {
        String nameval =
		p.getNCname();
	if (nameval != null) {
	    return new ColorTypeProperty(
		    
        new ColorType(nameval));
        }
      }
      
      return super.convertPropertyDatatype(p, propertyList, fo);
    }

}
