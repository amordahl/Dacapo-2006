
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class AbsolutePositionMaker extends  EnumProperty.Maker implements AbsolutePosition {



  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

  protected final static EnumProperty s_propFIXED = new EnumProperty(FIXED);

  protected final static EnumProperty s_propABSOLUTE = new EnumProperty(ABSOLUTE);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new AbsolutePositionMaker(propName);
    }

    protected AbsolutePositionMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("fixed")) { return s_propFIXED; }
    
      if (value.equals("absolute")) { return s_propABSOLUTE; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
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
