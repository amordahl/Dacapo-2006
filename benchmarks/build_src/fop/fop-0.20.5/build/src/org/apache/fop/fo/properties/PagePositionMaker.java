
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class PagePositionMaker extends  EnumProperty.Maker implements PagePosition {



  protected final static EnumProperty s_propFIRST = new EnumProperty(FIRST);

  protected final static EnumProperty s_propLAST = new EnumProperty(LAST);

  protected final static EnumProperty s_propREST = new EnumProperty(REST);

  protected final static EnumProperty s_propANY = new EnumProperty(ANY);


    static public Property.Maker maker(String propName) {
	return new PagePositionMaker(propName);
    }

    protected PagePositionMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("first")) { return s_propFIRST; }
    
      if (value.equals("last")) { return s_propLAST; }
    
      if (value.equals("rest")) { return s_propREST; }
    
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
