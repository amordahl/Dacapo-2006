
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BlankOrNotBlankMaker extends  EnumProperty.Maker implements BlankOrNotBlank {



  protected final static EnumProperty s_propBLANK = new EnumProperty(BLANK);

  protected final static EnumProperty s_propNOT_BLANK = new EnumProperty(NOT_BLANK);

  protected final static EnumProperty s_propANY = new EnumProperty(ANY);


    static public Property.Maker maker(String propName) {
	return new BlankOrNotBlankMaker(propName);
    }

    protected BlankOrNotBlankMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("blank")) { return s_propBLANK; }
    
      if (value.equals("not-blank")) { return s_propNOT_BLANK; }
    
      if (value.equals("any")) { return s_propANY; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "any", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
