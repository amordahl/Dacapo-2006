
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class RuleStyleMaker extends  EnumProperty.Maker implements RuleStyle {



  protected final static EnumProperty s_propNONE = new EnumProperty(NONE);

  protected final static EnumProperty s_propDOTTED = new EnumProperty(DOTTED);

  protected final static EnumProperty s_propDASHED = new EnumProperty(DASHED);

  protected final static EnumProperty s_propSOLID = new EnumProperty(SOLID);

  protected final static EnumProperty s_propDOUBLE = new EnumProperty(DOUBLE);

  protected final static EnumProperty s_propGROOVE = new EnumProperty(GROOVE);

  protected final static EnumProperty s_propRIDGE = new EnumProperty(RIDGE);


    static public Property.Maker maker(String propName) {
	return new RuleStyleMaker(propName);
    }

    protected RuleStyleMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("none")) { return s_propNONE; }
    
      if (value.equals("dotted")) { return s_propDOTTED; }
    
      if (value.equals("dashed")) { return s_propDASHED; }
    
      if (value.equals("solid")) { return s_propSOLID; }
    
      if (value.equals("double")) { return s_propDOUBLE; }
    
      if (value.equals("groove")) { return s_propGROOVE; }
    
      if (value.equals("ridge")) { return s_propRIDGE; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "solid", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
