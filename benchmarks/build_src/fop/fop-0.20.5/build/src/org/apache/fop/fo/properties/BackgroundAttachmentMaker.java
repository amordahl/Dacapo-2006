
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BackgroundAttachmentMaker extends  EnumProperty.Maker implements BackgroundAttachment {



  protected final static EnumProperty s_propSCROLL = new EnumProperty(SCROLL);

  protected final static EnumProperty s_propFIXED = new EnumProperty(FIXED);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new BackgroundAttachmentMaker(propName);
    }

    protected BackgroundAttachmentMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("scroll")) { return s_propSCROLL; }
    
      if (value.equals("fixed")) { return s_propFIXED; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "scroll", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
