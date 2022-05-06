
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class LeaderPatternMaker extends  EnumProperty.Maker implements LeaderPattern {



  protected final static EnumProperty s_propSPACE = new EnumProperty(SPACE);

  protected final static EnumProperty s_propRULE = new EnumProperty(RULE);

  protected final static EnumProperty s_propDOTS = new EnumProperty(DOTS);

  protected final static EnumProperty s_propUSECONTENT = new EnumProperty(USECONTENT);


    static public Property.Maker maker(String propName) {
	return new LeaderPatternMaker(propName);
    }

    protected LeaderPatternMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("space")) { return s_propSPACE; }
    
      if (value.equals("rule")) { return s_propRULE; }
    
      if (value.equals("dots")) { return s_propDOTS; }
    
      if (value.equals("use-content")) { return s_propUSECONTENT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "space", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
