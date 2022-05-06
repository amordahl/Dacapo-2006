
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class RelativePositionMaker extends  EnumProperty.Maker implements RelativePosition {



  protected final static EnumProperty s_propSTATIC = new EnumProperty(STATIC);

  protected final static EnumProperty s_propRELATIVE = new EnumProperty(RELATIVE);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new RelativePositionMaker(propName);
    }

    protected RelativePositionMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("")) { return s_propSTATIC; }
    
      if (value.equals("static")) { return s_propSTATIC; }
    
      if (value.equals("relative")) { return s_propRELATIVE; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
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
