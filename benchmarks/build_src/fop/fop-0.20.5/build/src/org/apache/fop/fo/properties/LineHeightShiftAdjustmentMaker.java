
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class LineHeightShiftAdjustmentMaker extends  EnumProperty.Maker implements LineHeightShiftAdjustment {



  protected final static EnumProperty s_propCONSIDER_SHIFTS = new EnumProperty(CONSIDER_SHIFTS);

  protected final static EnumProperty s_propDISREGARD_SHIFTS = new EnumProperty(DISREGARD_SHIFTS);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new LineHeightShiftAdjustmentMaker(propName);
    }

    protected LineHeightShiftAdjustmentMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("consider-shifts")) { return s_propCONSIDER_SHIFTS; }
    
      if (value.equals("disregard-shifts")) { return s_propDISREGARD_SHIFTS; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "consider-shifts", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
