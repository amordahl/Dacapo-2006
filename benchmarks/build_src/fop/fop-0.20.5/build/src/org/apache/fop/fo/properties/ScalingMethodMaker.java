
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class ScalingMethodMaker extends  EnumProperty.Maker implements ScalingMethod {



  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

  protected final static EnumProperty s_propINTEGER_PIXELS = new EnumProperty(INTEGER_PIXELS);

  protected final static EnumProperty s_propRESAMPLE_ANY_METHOD = new EnumProperty(RESAMPLE_ANY_METHOD);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new ScalingMethodMaker(propName);
    }

    protected ScalingMethodMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("integer-pixels")) { return s_propINTEGER_PIXELS; }
    
      if (value.equals("resample-any-method")) { return s_propRESAMPLE_ANY_METHOD; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
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
