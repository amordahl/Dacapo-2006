
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class RetrievePositionMaker extends  EnumProperty.Maker implements RetrievePosition {



  protected final static EnumProperty s_propFSWP = new EnumProperty(FSWP);

  protected final static EnumProperty s_propFIC = new EnumProperty(FIC);

  protected final static EnumProperty s_propLSWP = new EnumProperty(LSWP);

  protected final static EnumProperty s_propLEWP = new EnumProperty(LEWP);


    static public Property.Maker maker(String propName) {
	return new RetrievePositionMaker(propName);
    }

    protected RetrievePositionMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("first-starting-within-page")) { return s_propFSWP; }
    
      if (value.equals("first-including-carryover")) { return s_propFIC; }
    
      if (value.equals("last-starting-within-page")) { return s_propLSWP; }
    
      if (value.equals("last-ending-within-page")) { return s_propLEWP; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "first-starting-within-page", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
