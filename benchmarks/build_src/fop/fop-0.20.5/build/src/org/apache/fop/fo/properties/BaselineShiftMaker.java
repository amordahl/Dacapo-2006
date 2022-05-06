
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BaselineShiftMaker extends  LengthProperty.Maker implements BaselineShift {



  protected final static EnumProperty s_propBASELINE = new EnumProperty(BASELINE);

  protected final static EnumProperty s_propSUB = new EnumProperty(SUB);

  protected final static EnumProperty s_propSUPER = new EnumProperty(SUPER);


    static public Property.Maker maker(String propName) {
	return new BaselineShiftMaker(propName);
    }

    protected BaselineShiftMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("baseline")) { return s_propBASELINE; }
    
      if (value.equals("sub")) { return s_propSUB; }
    
      if (value.equals("super")) { return s_propSUPER; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "baseline", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
