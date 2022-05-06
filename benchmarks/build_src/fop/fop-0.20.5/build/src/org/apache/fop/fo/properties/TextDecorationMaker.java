
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class TextDecorationMaker extends  EnumProperty.Maker implements TextDecoration {



  protected final static EnumProperty s_propNONE = new EnumProperty(NONE);

  protected final static EnumProperty s_propUNDERLINE = new EnumProperty(UNDERLINE);

  protected final static EnumProperty s_propOVERLINE = new EnumProperty(OVERLINE);

  protected final static EnumProperty s_propLINE_THROUGH = new EnumProperty(LINE_THROUGH);

  protected final static EnumProperty s_propBLINK = new EnumProperty(BLINK);

  protected final static EnumProperty s_propNO_UNDERLINE = new EnumProperty(NO_UNDERLINE);

  protected final static EnumProperty s_propNO_OVERLINE = new EnumProperty(NO_OVERLINE);

  protected final static EnumProperty s_propNO_LINE_THROUGH = new EnumProperty(NO_LINE_THROUGH);

  protected final static EnumProperty s_propNO_BLINK = new EnumProperty(NO_BLINK);


    static public Property.Maker maker(String propName) {
	return new TextDecorationMaker(propName);
    }

    protected TextDecorationMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("none")) { return s_propNONE; }
    
      if (value.equals("underline")) { return s_propUNDERLINE; }
    
      if (value.equals("overline")) { return s_propOVERLINE; }
    
      if (value.equals("line-through")) { return s_propLINE_THROUGH; }
    
      if (value.equals("blink")) { return s_propBLINK; }
    
      if (value.equals("no-underline")) { return s_propNO_UNDERLINE; }
    
      if (value.equals("no-overline")) { return s_propNO_OVERLINE; }
    
      if (value.equals("no-line-through")) { return s_propNO_LINE_THROUGH; }
    
      if (value.equals("no-blink")) { return s_propNO_BLINK; }
    
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
