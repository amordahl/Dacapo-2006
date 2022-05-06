
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class DirectionMaker extends  EnumProperty.Maker implements Direction {



  protected final static EnumProperty s_propLTR = new EnumProperty(LTR);

  protected final static EnumProperty s_propRTL = new EnumProperty(RTL);


    static public Property.Maker maker(String propName) {
	return new DirectionMaker(propName);
    }

    protected DirectionMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("ltr")) { return s_propLTR; }
    
      if (value.equals("rtl")) { return s_propRTL; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "ltr", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
