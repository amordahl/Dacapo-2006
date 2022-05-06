
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class StartsRowMaker extends  EnumProperty.Maker implements StartsRow {



  protected final static EnumProperty s_propTRUE = new EnumProperty(TRUE);

  protected final static EnumProperty s_propFALSE = new EnumProperty(FALSE);


    static public Property.Maker maker(String propName) {
	return new StartsRowMaker(propName);
    }

    protected StartsRowMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("true")) { return s_propTRUE; }
    
      if (value.equals("false")) { return s_propFALSE; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "false", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
