
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class WrapOptionMaker extends  EnumProperty.Maker implements WrapOption {



  protected final static EnumProperty s_propWRAP = new EnumProperty(WRAP);

  protected final static EnumProperty s_propNO_WRAP = new EnumProperty(NO_WRAP);


    static public Property.Maker maker(String propName) {
	return new WrapOptionMaker(propName);
    }

    protected WrapOptionMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("wrap")) { return s_propWRAP; }
    
      if (value.equals("no-wrap")) { return s_propNO_WRAP; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "wrap", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
