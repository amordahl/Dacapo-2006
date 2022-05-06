
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class FontSelectionStrategyMaker extends  EnumProperty.Maker implements FontSelectionStrategy {



  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

  protected final static EnumProperty s_propCHARACTER_BY_CHARACTER = new EnumProperty(CHARACTER_BY_CHARACTER);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new FontSelectionStrategyMaker(propName);
    }

    protected FontSelectionStrategyMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("character-by-character")) { return s_propCHARACTER_BY_CHARACTER; }
    
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
