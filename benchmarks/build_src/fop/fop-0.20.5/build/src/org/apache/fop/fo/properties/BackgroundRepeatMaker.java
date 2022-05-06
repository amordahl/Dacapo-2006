
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BackgroundRepeatMaker extends  EnumProperty.Maker implements BackgroundRepeat {



  protected final static EnumProperty s_propREPEAT = new EnumProperty(REPEAT);

  protected final static EnumProperty s_propREPEAT_X = new EnumProperty(REPEAT_X);

  protected final static EnumProperty s_propREPEAT_Y = new EnumProperty(REPEAT_Y);

  protected final static EnumProperty s_propNO_REPEAT = new EnumProperty(NO_REPEAT);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new BackgroundRepeatMaker(propName);
    }

    protected BackgroundRepeatMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("repeat")) { return s_propREPEAT; }
    
      if (value.equals("repeat-x")) { return s_propREPEAT_X; }
    
      if (value.equals("repeat-y")) { return s_propREPEAT_Y; }
    
      if (value.equals("no-repeat")) { return s_propNO_REPEAT; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "repeat", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
