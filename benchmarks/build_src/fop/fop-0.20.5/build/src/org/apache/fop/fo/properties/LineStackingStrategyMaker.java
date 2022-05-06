
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class LineStackingStrategyMaker extends  EnumProperty.Maker implements LineStackingStrategy {



  protected final static EnumProperty s_propLINE_HEIGHT = new EnumProperty(LINE_HEIGHT);

  protected final static EnumProperty s_propFONT_HEIGHT = new EnumProperty(FONT_HEIGHT);

  protected final static EnumProperty s_propMAX_HEIGHT = new EnumProperty(MAX_HEIGHT);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new LineStackingStrategyMaker(propName);
    }

    protected LineStackingStrategyMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("line-height")) { return s_propLINE_HEIGHT; }
    
      if (value.equals("font-height")) { return s_propFONT_HEIGHT; }
    
      if (value.equals("max-height")) { return s_propMAX_HEIGHT; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "line-height", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
