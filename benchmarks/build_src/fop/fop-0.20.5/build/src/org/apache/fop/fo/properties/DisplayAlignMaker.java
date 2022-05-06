
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class DisplayAlignMaker extends  EnumProperty.Maker implements DisplayAlign {



  protected final static EnumProperty s_propBEFORE = new EnumProperty(BEFORE);

  protected final static EnumProperty s_propAFTER = new EnumProperty(AFTER);

  protected final static EnumProperty s_propCENTER = new EnumProperty(CENTER);

  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);


    static public Property.Maker maker(String propName) {
	return new DisplayAlignMaker(propName);
    }

    protected DisplayAlignMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("before")) { return s_propBEFORE; }
    
      if (value.equals("after")) { return s_propAFTER; }
    
      if (value.equals("center")) { return s_propCENTER; }
    
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
