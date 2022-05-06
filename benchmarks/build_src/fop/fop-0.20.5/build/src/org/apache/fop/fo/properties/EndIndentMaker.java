
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class EndIndentMaker extends  LengthProperty.Maker {




    static public Property.Maker maker(String propName) {
	return new EndIndentMaker(propName);
    }

    protected EndIndentMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public boolean isCorrespondingForced(PropertyList propertyList) {
      StringBuffer sbExpr=new StringBuffer();
      
      sbExpr.setLength(0);
      sbExpr.append("margin-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.END));

      if (propertyList.getExplicit(sbExpr.toString()) != null) return true;
      
      return false;
    }
    

    public Property compute(PropertyList propertyList) throws FOPException {
      FObj parentFO = propertyList.getParentFObj();
      StringBuffer sbExpr=new StringBuffer();
      Property p=null;
      sbExpr.append("margin-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.END));

	// Make sure the property is set before calculating it!
      if (propertyList.getExplicitOrShorthand(sbExpr.toString()) == null) return p;
      sbExpr.setLength(0);
      
   sbExpr.append("_fop-property-value("); 
   sbExpr.append("margin-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.END));

   sbExpr.append(")");
sbExpr.append("+");
   sbExpr.append("_fop-property-value("); 
   sbExpr.append("padding-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.END));

   sbExpr.append(")");
sbExpr.append("+");
   sbExpr.append("_fop-property-value("); 
   sbExpr.append("border-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.END));
sbExpr.append("-width");
   sbExpr.append(")");

      p= make(propertyList, sbExpr.toString(), propertyList.getParentFObj());
      
      if (p != null) {
          p = convertProperty(p, propertyList, parentFO );
      }
      
      return p;
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "0pt", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
