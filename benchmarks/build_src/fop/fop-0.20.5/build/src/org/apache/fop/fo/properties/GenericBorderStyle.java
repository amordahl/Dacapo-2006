
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class GenericBorderStyle extends  EnumProperty.Maker {



  public interface Enums {

    int NONE =  Constants.NONE;

    int HIDDEN =  Constants.HIDDEN;

    int DOTTED =  Constants.DOTTED;

    int DASHED =  Constants.DASHED;

    int SOLID =  Constants.SOLID;

    int DOUBLE =  Constants.DOUBLE;

    int GROOVE =  Constants.GROOVE;

    int RIDGE =  Constants.RIDGE;

    int INSET =  Constants.INSET;

    int OUTSET =  Constants.OUTSET;

  }

  protected final static EnumProperty s_propNONE = new EnumProperty(Enums.NONE);

  protected final static EnumProperty s_propHIDDEN = new EnumProperty(Enums.HIDDEN);

  protected final static EnumProperty s_propDOTTED = new EnumProperty(Enums.DOTTED);

  protected final static EnumProperty s_propDASHED = new EnumProperty(Enums.DASHED);

  protected final static EnumProperty s_propSOLID = new EnumProperty(Enums.SOLID);

  protected final static EnumProperty s_propDOUBLE = new EnumProperty(Enums.DOUBLE);

  protected final static EnumProperty s_propGROOVE = new EnumProperty(Enums.GROOVE);

  protected final static EnumProperty s_propRIDGE = new EnumProperty(Enums.RIDGE);

  protected final static EnumProperty s_propINSET = new EnumProperty(Enums.INSET);

  protected final static EnumProperty s_propOUTSET = new EnumProperty(Enums.OUTSET);


    static public Property.Maker maker(String propName) {
	return new GenericBorderStyle(propName);
    }

    protected GenericBorderStyle(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property getShorthand(PropertyList propertyList) {
      Property p = null;
      ListProperty listprop;
      
      if (p == null) {
         listprop = (ListProperty)propertyList.getExplicit("border-style");
         if (listprop != null) {
           // Get a parser for the shorthand to set the individual properties
           ShorthandParser shparser = new BoxPropShorthandParser(listprop);
             p = shparser.getValueForProperty(getPropName(), this, propertyList);
         }
      }
      
      return p;
    }

    public Property checkEnumValues(String value) {
    
      if (value.equals("none")) { return s_propNONE; }
    
      if (value.equals("hidden")) { return s_propHIDDEN; }
    
      if (value.equals("dotted")) { return s_propDOTTED; }
    
      if (value.equals("dashed")) { return s_propDASHED; }
    
      if (value.equals("solid")) { return s_propSOLID; }
    
      if (value.equals("double")) { return s_propDOUBLE; }
    
      if (value.equals("groove")) { return s_propGROOVE; }
    
      if (value.equals("ridge")) { return s_propRIDGE; }
    
      if (value.equals("inset")) { return s_propINSET; }
    
      if (value.equals("outset")) { return s_propOUTSET; }
    
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
