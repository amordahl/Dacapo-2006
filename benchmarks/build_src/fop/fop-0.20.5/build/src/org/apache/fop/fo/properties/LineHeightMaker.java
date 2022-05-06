
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class LineHeightMaker extends  LengthProperty.Maker {




    static public Property.Maker maker(String propName) {
	return new LineHeightMaker(propName);
    }

    protected LineHeightMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

   public boolean inheritsSpecified() {
	 return true;
   }

    public Property make(PropertyList propertyList) throws FOPException {
      
        return make(propertyList, "normal", propertyList.getParentFObj());
	
    }

    // Initialize hashtable of keywords
    static Hashtable s_htKeywords;
    static {
	s_htKeywords = new Hashtable(1);
  
      	s_htKeywords.put("normal", "1.2em");
  
    }
    protected String checkValueKeywords(String keyword) {
      String value = (String)s_htKeywords.get(keyword);
      if (value == null) {
	return super.checkValueKeywords(keyword);
      }
      else return value;
    }

    // See if other value types are acceptable
    protected Property convertPropertyDatatype(Property p,
	PropertyList propertyList, FObj fo) {
      
      {
        Number numval =
		p.getNumber();
	if (numval != null) {
	    return new LengthProperty(
		    
        new PercentLength(numval.doubleValue(),
        getPercentBase(fo,propertyList)));
        }
      }
      
      return super.convertPropertyDatatype(p, propertyList, fo);
    }

    /** Return object used to calculate base Length
     * for percent specifications.
     */
    public PercentBase getPercentBase(final FObj fo, final PropertyList propertyList) {
     
	return new LengthBase(fo, propertyList, LengthBase.FONTSIZE);
       
    }

}
