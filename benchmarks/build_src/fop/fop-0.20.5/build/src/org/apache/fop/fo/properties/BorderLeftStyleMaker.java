
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderLeftStyleMaker extends  GenericBorderStyle {




    static public Property.Maker maker(String propName) {
	return new BorderLeftStyleMaker(propName);
    }

    protected BorderLeftStyleMaker(String name) {
	super(name);

    }



    public Property compute(PropertyList propertyList) throws FOPException {
      FObj parentFO = propertyList.getParentFObj();
      StringBuffer sbExpr=new StringBuffer();
      Property p=null;
      sbExpr.append("border-");
   sbExpr.append(propertyList.wmAbsToRel(PropertyList.LEFT));
sbExpr.append("-style");
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
         listprop = (ListProperty)propertyList.getExplicit("border-left");
         if (listprop != null) {
           // Get a parser for the shorthand to set the individual properties
           ShorthandParser shparser = new GenericShorthandParser(listprop);
             p = shparser.getValueForProperty(getPropName(), this, propertyList);
         }
      }
      
      if (p == null) {
         listprop = (ListProperty)propertyList.getExplicit("border-style");
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

}
