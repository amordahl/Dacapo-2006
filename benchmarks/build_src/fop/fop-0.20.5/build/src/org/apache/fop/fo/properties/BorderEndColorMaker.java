
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderEndColorMaker extends  GenericColor {




    static public Property.Maker maker(String propName) {
	return new BorderEndColorMaker(propName);
    }

    protected BorderEndColorMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public boolean isCorrespondingForced(PropertyList propertyList) {
      StringBuffer sbExpr=new StringBuffer();
      
      sbExpr.setLength(0);
      sbExpr.append("border-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.END));
sbExpr.append("-color");
      if (propertyList.getExplicit(sbExpr.toString()) != null) return true;
      
      return false;
    }
    

    public Property compute(PropertyList propertyList) throws FOPException {
      FObj parentFO = propertyList.getParentFObj();
      StringBuffer sbExpr=new StringBuffer();
      Property p=null;
      sbExpr.append("border-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.END));
sbExpr.append("-color");
      p= propertyList.getExplicitOrShorthand(sbExpr.toString());
      
      if (p != null) {
          p = convertProperty(p, propertyList, parentFO );
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
