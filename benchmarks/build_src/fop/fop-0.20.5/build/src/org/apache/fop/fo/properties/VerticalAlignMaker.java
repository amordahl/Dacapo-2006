
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class VerticalAlignMaker extends  EnumProperty.Maker implements VerticalAlign {



  protected final static EnumProperty s_propBASELINE = new EnumProperty(BASELINE);

  protected final static EnumProperty s_propMIDDLE = new EnumProperty(MIDDLE);

  protected final static EnumProperty s_propSUB = new EnumProperty(SUB);

  protected final static EnumProperty s_propSUPER = new EnumProperty(SUPER);

  protected final static EnumProperty s_propTEXT_TOP = new EnumProperty(TEXT_TOP);

  protected final static EnumProperty s_propTEXT_BOTTOM = new EnumProperty(TEXT_BOTTOM);

  protected final static EnumProperty s_propTOP = new EnumProperty(TOP);

  protected final static EnumProperty s_propBOTTOM = new EnumProperty(BOTTOM);


    static public Property.Maker maker(String propName) {
	return new VerticalAlignMaker(propName);
    }

    protected VerticalAlignMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("baseline")) { return s_propBASELINE; }
    
      if (value.equals("middle")) { return s_propMIDDLE; }
    
      if (value.equals("sub")) { return s_propSUB; }
    
      if (value.equals("super")) { return s_propSUPER; }
    
      if (value.equals("text-top")) { return s_propTEXT_TOP; }
    
      if (value.equals("text-bottom")) { return s_propTEXT_BOTTOM; }
    
      if (value.equals("top")) { return s_propTOP; }
    
      if (value.equals("bottom")) { return s_propBOTTOM; }
    
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
