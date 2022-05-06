
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderCollapseMaker extends  EnumProperty.Maker implements BorderCollapse {



  protected final static EnumProperty s_propSEPARATE = new EnumProperty(SEPARATE);

  protected final static EnumProperty s_propCOLLAPSE = new EnumProperty(COLLAPSE);


    static public Property.Maker maker(String propName) {
	return new BorderCollapseMaker(propName);
    }

    protected BorderCollapseMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "collapse", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

    public Property checkEnumValues(String value) {
    
      if (value.equals("separate")) { return s_propSEPARATE; }
    
      if (value.equals("collapse")) { return s_propCOLLAPSE; }
    
	return super.checkEnumValues(value);
    }

}
