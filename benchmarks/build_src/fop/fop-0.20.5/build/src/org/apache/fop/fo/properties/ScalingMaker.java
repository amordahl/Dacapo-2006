
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class ScalingMaker extends  EnumProperty.Maker implements Scaling {



  protected final static EnumProperty s_propUNIFORM = new EnumProperty(UNIFORM);

  protected final static EnumProperty s_propNON_UNIFORM = new EnumProperty(NON_UNIFORM);


    static public Property.Maker maker(String propName) {
	return new ScalingMaker(propName);
    }

    protected ScalingMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("uniform")) { return s_propUNIFORM; }
    
      if (value.equals("non-uniform")) { return s_propNON_UNIFORM; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "uniform", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
