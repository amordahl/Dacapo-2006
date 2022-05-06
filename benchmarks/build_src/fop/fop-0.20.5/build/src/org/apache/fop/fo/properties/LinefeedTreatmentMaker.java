
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class LinefeedTreatmentMaker extends  EnumProperty.Maker implements LinefeedTreatment {



  protected final static EnumProperty s_propIGNORE = new EnumProperty(IGNORE);

  protected final static EnumProperty s_propPRESERVE = new EnumProperty(PRESERVE);

  protected final static EnumProperty s_propTREAT_AS_SPACE = new EnumProperty(TREAT_AS_SPACE);

  protected final static EnumProperty s_propTREAT_AS_ZERO_WIDTH_SPACE = new EnumProperty(TREAT_AS_ZERO_WIDTH_SPACE);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new LinefeedTreatmentMaker(propName);
    }

    protected LinefeedTreatmentMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("ignore")) { return s_propIGNORE; }
    
      if (value.equals("preserve")) { return s_propPRESERVE; }
    
      if (value.equals("treat-as-space")) { return s_propTREAT_AS_SPACE; }
    
      if (value.equals("treat-as-zero-width-space")) { return s_propTREAT_AS_ZERO_WIDTH_SPACE; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "treat-as-space", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
