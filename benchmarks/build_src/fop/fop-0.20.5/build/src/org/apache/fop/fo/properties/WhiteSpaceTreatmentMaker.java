
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class WhiteSpaceTreatmentMaker extends  EnumProperty.Maker implements WhiteSpaceTreatment {



  protected final static EnumProperty s_propIGNORE = new EnumProperty(IGNORE);

  protected final static EnumProperty s_propPRESERVE = new EnumProperty(PRESERVE);

  protected final static EnumProperty s_propIGNORE_IF_BEFORE_LINEFEED = new EnumProperty(IGNORE_IF_BEFORE_LINEFEED);

  protected final static EnumProperty s_propIGNORE_IF_AFTER_LINEFEED = new EnumProperty(IGNORE_IF_AFTER_LINEFEED);

  protected final static EnumProperty s_propIGNORE_IF_SURROUNDING_LINEFEED = new EnumProperty(IGNORE_IF_SURROUNDING_LINEFEED);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new WhiteSpaceTreatmentMaker(propName);
    }

    protected WhiteSpaceTreatmentMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("ignore")) { return s_propIGNORE; }
    
      if (value.equals("preserve")) { return s_propPRESERVE; }
    
      if (value.equals("ignore-if-before-linefeed")) { return s_propIGNORE_IF_BEFORE_LINEFEED; }
    
      if (value.equals("ignore-if-after-linefeed")) { return s_propIGNORE_IF_AFTER_LINEFEED; }
    
      if (value.equals("ignore-if-surrounding-linefeed")) { return s_propIGNORE_IF_SURROUNDING_LINEFEED; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "ignore-if-surrounding-linefeed", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
