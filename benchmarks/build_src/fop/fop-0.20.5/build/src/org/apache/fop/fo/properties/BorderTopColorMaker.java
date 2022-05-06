
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderTopColorMaker extends  GenericColor {




    static public Property.Maker maker(String propName) {
	return new BorderTopColorMaker(propName);
    }

    protected BorderTopColorMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }


    public Property compute(PropertyList propertyList) throws FOPException {
      FObj parentFO = propertyList.getParentFObj();
      StringBuffer sbExpr=new StringBuffer();
      Property p=null;
      sbExpr.append("border-");
   sbExpr.append(propertyList.wmAbsToRel(PropertyList.TOP));
sbExpr.append("-color");
      p= propertyList.getExplicitOrShorthand(sbExpr.toString());
      
      if (p != null) {
          p = convertProperty(p, propertyList, parentFO );
      }
      
      return p;
    }

    public Property getShorthand(PropertyList propertyList) {
      Property p = null;
      ListProperty listprop;
      
      if (p == null) {
         listprop = (ListProperty)propertyList.getExplicit("border-top");
         if (listprop != null) {
           // Get a parser for the shorthand to set the individual properties
           ShorthandParser shparser = new GenericShorthandParser(listprop);
             p = shparser.getValueForProperty(getPropName(), this, propertyList);
         }
      }
      
      if (p == null) {
         listprop = (ListProperty)propertyList.getExplicit("border-color");
         if (listprop != null) {
           // Get a parser for the shorthand to set the individual properties
           ShorthandParser shparser = new BoxPropShorthandParser(listprop);
             p = shparser.getValueForProperty(getPropName(), this, propertyList);
         }
      }
      
      if (p == null) {
         listprop = (ListProperty)propertyList.getExplicit("border");
         if (listprop != null) {
           // Get a parser for the shorthand to set the individual properties
           ShorthandParser shparser = new GenericShorthandParser(listprop);
             p = shparser.getValueForProperty(getPropName(), this, propertyList);
         }
      }
      
      return p;
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "black", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
