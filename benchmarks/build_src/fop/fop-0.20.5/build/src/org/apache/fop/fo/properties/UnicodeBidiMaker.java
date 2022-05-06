
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class UnicodeBidiMaker extends  EnumProperty.Maker implements UnicodeBidi {



  protected final static EnumProperty s_propNORMAL = new EnumProperty(NORMAL);

  protected final static EnumProperty s_propEMBED = new EnumProperty(EMBED);

  protected final static EnumProperty s_propBIDI_OVERRIDE = new EnumProperty(BIDI_OVERRIDE);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new UnicodeBidiMaker(propName);
    }

    protected UnicodeBidiMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("normal")) { return s_propNORMAL; }
    
      if (value.equals("embed")) { return s_propEMBED; }
    
      if (value.equals("bidi-override")) { return s_propBIDI_OVERRIDE; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "normal", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
