
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class SpanMaker extends  EnumProperty.Maker implements Span {



  protected final static EnumProperty s_propNONE = new EnumProperty(NONE);

  protected final static EnumProperty s_propALL = new EnumProperty(ALL);


    static public Property.Maker maker(String propName) {
	return new SpanMaker(propName);
    }

    protected SpanMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("none")) { return s_propNONE; }
    
      if (value.equals("all")) { return s_propALL; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "none", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
