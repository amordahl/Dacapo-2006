
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class GenericBorderWidth extends  LengthProperty.Maker {




    static public Property.Maker maker(String propName) {
	return new GenericBorderWidth(propName);
    }

    protected GenericBorderWidth(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property getShorthand(PropertyList propertyList) {
      Property p = null;
      ListProperty listprop;
      
      if (p == null) {
         listprop = (ListProperty)propertyList.getExplicit("border-width");
         if (listprop != null) {
           // Get a parser for the shorthand to set the individual properties
           ShorthandParser shparser = new BoxPropShorthandParser(listprop);
             p = shparser.getValueForProperty(getPropName(), this, propertyList);
         }
      }
      
      return p;
    }

    // Initialize hashtable of keywords
    static Hashtable s_htKeywords;
    static {
	s_htKeywords = new Hashtable(3);
  
      	s_htKeywords.put("thin", "0.5pt");
  
      	s_htKeywords.put("medium", "1pt");
  
      	s_htKeywords.put("thick", "2pt");
  
    }
    protected String checkValueKeywords(String keyword) {
      String value = (String)s_htKeywords.get(keyword);
      if (value == null) {
	return super.checkValueKeywords(keyword);
      }
      else return value;
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "0pt", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
