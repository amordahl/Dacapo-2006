
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class SuppressAtLineBreakMaker extends  EnumProperty.Maker implements SuppressAtLineBreak {



  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

  protected final static EnumProperty s_propSUPPRESS = new EnumProperty(SUPPRESS);

  protected final static EnumProperty s_propRETAIN = new EnumProperty(RETAIN);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new SuppressAtLineBreakMaker(propName);
    }

    protected SuppressAtLineBreakMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("suppress")) { return s_propSUPPRESS; }
    
      if (value.equals("retain")) { return s_propRETAIN; }
    
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
