
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class RelativeAlignMaker extends  EnumProperty.Maker implements RelativeAlign {



  protected final static EnumProperty s_propBEFORE = new EnumProperty(BEFORE);

  protected final static EnumProperty s_propBASELINE = new EnumProperty(BASELINE);


    static public Property.Maker maker(String propName) {
	return new RelativeAlignMaker(propName);
    }

    protected RelativeAlignMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("before")) { return s_propBEFORE; }
    
      if (value.equals("after")) { return s_propBASELINE; }
    
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
