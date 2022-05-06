
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class WritingModeMaker extends  EnumProperty.Maker implements WritingMode {



  protected final static EnumProperty s_propLR_TB = new EnumProperty(LR_TB);

  protected final static EnumProperty s_propRL_TB = new EnumProperty(RL_TB);

  protected final static EnumProperty s_propTB_RL = new EnumProperty(TB_RL);


    static public Property.Maker maker(String propName) {
	return new WritingModeMaker(propName);
    }

    protected WritingModeMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "lr-tb", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

    public Property checkEnumValues(String value) {
    
      if (value.equals("lr-tb")) { return s_propLR_TB; }
    
      if (value.equals("rl-tb")) { return s_propRL_TB; }
    
      if (value.equals("tb-rl")) { return s_propTB_RL; }
    
	return super.checkEnumValues(value);
    }

}
