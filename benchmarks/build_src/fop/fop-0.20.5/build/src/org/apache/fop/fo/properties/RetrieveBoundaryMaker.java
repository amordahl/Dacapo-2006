
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class RetrieveBoundaryMaker extends  EnumProperty.Maker implements RetrieveBoundary {



  protected final static EnumProperty s_propPAGE = new EnumProperty(PAGE);

  protected final static EnumProperty s_propPAGE_SEQUENCE = new EnumProperty(PAGE_SEQUENCE);

  protected final static EnumProperty s_propDOCUMENT = new EnumProperty(DOCUMENT);


    static public Property.Maker maker(String propName) {
	return new RetrieveBoundaryMaker(propName);
    }

    protected RetrieveBoundaryMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("page")) { return s_propPAGE; }
    
      if (value.equals("page-sequence")) { return s_propPAGE_SEQUENCE; }
    
      if (value.equals("document")) { return s_propDOCUMENT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "page-sequence", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
