
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class WhiteSpaceMaker extends  EnumProperty.Maker implements WhiteSpace {



  protected final static EnumProperty s_propNORMAL = new EnumProperty(NORMAL);

  protected final static EnumProperty s_propPRE = new EnumProperty(PRE);

  protected final static EnumProperty s_propNOWRAP = new EnumProperty(NOWRAP);


    static public Property.Maker maker(String propName) {
	return new WhiteSpaceMaker(propName);
    }

    protected WhiteSpaceMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("normal")) { return s_propNORMAL; }
    
      if (value.equals("pre")) { return s_propPRE; }
    
      if (value.equals("nowrap")) { return s_propNOWRAP; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "normal", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
