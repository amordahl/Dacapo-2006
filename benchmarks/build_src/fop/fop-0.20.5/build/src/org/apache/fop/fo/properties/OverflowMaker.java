
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class OverflowMaker extends  EnumProperty.Maker implements Overflow {



  protected final static EnumProperty s_propVISIBLE = new EnumProperty(VISIBLE);

  protected final static EnumProperty s_propHIDDEN = new EnumProperty(HIDDEN);

  protected final static EnumProperty s_propSCROLL = new EnumProperty(SCROLL);

  protected final static EnumProperty s_propERROR_IF_OVERFLOW = new EnumProperty(ERROR_IF_OVERFLOW);

  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);


    static public Property.Maker maker(String propName) {
	return new OverflowMaker(propName);
    }

    protected OverflowMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("visible")) { return s_propVISIBLE; }
    
      if (value.equals("hidden")) { return s_propHIDDEN; }
    
      if (value.equals("scroll")) { return s_propSCROLL; }
    
      if (value.equals("error-if-overflow")) { return s_propERROR_IF_OVERFLOW; }
    
      if (value.equals("auto")) { return s_propAUTO; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "auto", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
