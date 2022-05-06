
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderAfterStyleMaker extends  GenericBorderStyle {




    static public Property.Maker maker(String propName) {
	return new BorderAfterStyleMaker(propName);
    }

    protected BorderAfterStyleMaker(String name) {
	super(name);

    }


    public boolean isCorrespondingForced(PropertyList propertyList) {
      StringBuffer sbExpr=new StringBuffer();
      
      sbExpr.setLength(0);
      sbExpr.append("border-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.AFTER));
sbExpr.append("-style");
      if (propertyList.getExplicit(sbExpr.toString()) != null) return true;
      
      return false;
    }
    

    public Property compute(PropertyList propertyList) throws FOPException {
      FObj parentFO = propertyList.getParentFObj();
      StringBuffer sbExpr=new StringBuffer();
      Property p=null;
      sbExpr.append("border-");
   sbExpr.append(propertyList.wmRelToAbs(PropertyList.AFTER));
sbExpr.append("-style");
      p= propertyList.getExplicitOrShorthand(sbExpr.toString());
      
      if (p != null) {
          p = convertProperty(p, propertyList, parentFO );
      }
      
      return p;
    }

}
