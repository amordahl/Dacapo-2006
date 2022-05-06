
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class ForcePageCountMaker extends  EnumProperty.Maker implements ForcePageCount {



  protected final static EnumProperty s_propEVEN = new EnumProperty(EVEN);

  protected final static EnumProperty s_propODD = new EnumProperty(ODD);

  protected final static EnumProperty s_propEND_ON_EVEN = new EnumProperty(END_ON_EVEN);

  protected final static EnumProperty s_propEND_ON_ODD = new EnumProperty(END_ON_ODD);

  protected final static EnumProperty s_propNO_FORCE = new EnumProperty(NO_FORCE);

  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);


    static public Property.Maker maker(String propName) {
	return new ForcePageCountMaker(propName);
    }

    protected ForcePageCountMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("even")) { return s_propEVEN; }
    
      if (value.equals("odd")) { return s_propODD; }
    
      if (value.equals("end-on-even")) { return s_propEND_ON_EVEN; }
    
      if (value.equals("end-on-odd")) { return s_propEND_ON_ODD; }
    
      if (value.equals("no-force")) { return s_propNO_FORCE; }
    
      if (value.equals("auto")) { return s_propAUTO; }
    
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
