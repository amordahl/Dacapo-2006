
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class PaddingLeftMaker extends  GenericPadding {




    static public Property.Maker maker(String propName) {
	return new PaddingLeftMaker(propName);
    }

    protected PaddingLeftMaker(String name) {
	super(name);

    }



    public Property compute(PropertyList propertyList) throws FOPException {
      FObj parentFO = propertyList.getParentFObj();
      StringBuffer sbExpr=new StringBuffer();
      Property p=null;
      sbExpr.append("padding-");
   sbExpr.append(propertyList.wmAbsToRel(PropertyList.LEFT));

      p= propertyList.getExplicitOrShorthand(sbExpr.toString());
      
      if (p != null) {
          p = convertProperty(p, propertyList, parentFO );
      }
      
      return p;
    }

}
