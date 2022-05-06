
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class CaptionSideMaker extends  EnumProperty.Maker implements CaptionSide {



  protected final static EnumProperty s_propBEFORE = new EnumProperty(BEFORE);

  protected final static EnumProperty s_propAFTER = new EnumProperty(AFTER);

  protected final static EnumProperty s_propSTART = new EnumProperty(START);

  protected final static EnumProperty s_propEND = new EnumProperty(END);

  protected final static EnumProperty s_propTOP = new EnumProperty(TOP);

  protected final static EnumProperty s_propBOTTOM = new EnumProperty(BOTTOM);

  protected final static EnumProperty s_propLEFT = new EnumProperty(LEFT);

  protected final static EnumProperty s_propRIGHT = new EnumProperty(RIGHT);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new CaptionSideMaker(propName);
    }

    protected CaptionSideMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("before")) { return s_propBEFORE; }
    
      if (value.equals("after")) { return s_propAFTER; }
    
      if (value.equals("start")) { return s_propSTART; }
    
      if (value.equals("end")) { return s_propEND; }
    
      if (value.equals("top")) { return s_propTOP; }
    
      if (value.equals("bottom")) { return s_propBOTTOM; }
    
      if (value.equals("left")) { return s_propLEFT; }
    
      if (value.equals("right")) { return s_propRIGHT; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "before", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
