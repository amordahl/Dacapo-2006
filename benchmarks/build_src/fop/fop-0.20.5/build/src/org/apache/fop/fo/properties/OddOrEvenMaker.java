
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class OddOrEvenMaker extends  EnumProperty.Maker implements OddOrEven {



  protected final static EnumProperty s_propODD = new EnumProperty(ODD);

  protected final static EnumProperty s_propEVEN = new EnumProperty(EVEN);

  protected final static EnumProperty s_propANY = new EnumProperty(ANY);


    static public Property.Maker maker(String propName) {
	return new OddOrEvenMaker(propName);
    }

    protected OddOrEvenMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("odd")) { return s_propODD; }
    
      if (value.equals("even")) { return s_propEVEN; }
    
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
