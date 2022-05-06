
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class PositionMaker extends  EnumProperty.Maker implements Position {



  protected final static EnumProperty s_propSTATIC = new EnumProperty(STATIC);

  protected final static EnumProperty s_propRELATIVE = new EnumProperty(RELATIVE);

  protected final static EnumProperty s_propABSOLUTE = new EnumProperty(ABSOLUTE);

  protected final static EnumProperty s_propFIXED = new EnumProperty(FIXED);


    static public Property.Maker maker(String propName) {
	return new PositionMaker(propName);
    }

    protected PositionMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("static")) { return s_propSTATIC; }
    
      if (value.equals("relative")) { return s_propRELATIVE; }
    
      if (value.equals("absolute")) { return s_propABSOLUTE; }
    
      if (value.equals("fixed")) { return s_propFIXED; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "static", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
