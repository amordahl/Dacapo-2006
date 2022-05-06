
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class GenericBreak extends  EnumProperty.Maker {



  public interface Enums {

    int AUTO =  Constants.AUTO;

    int COLUMN =  Constants.COLUMN;

    int PAGE =  Constants.PAGE;

    int EVEN_PAGE =  Constants.EVEN_PAGE;

    int ODD_PAGE =  Constants.ODD_PAGE;

  }

  protected final static EnumProperty s_propAUTO = new EnumProperty(Enums.AUTO);

  protected final static EnumProperty s_propCOLUMN = new EnumProperty(Enums.COLUMN);

  protected final static EnumProperty s_propPAGE = new EnumProperty(Enums.PAGE);

  protected final static EnumProperty s_propEVEN_PAGE = new EnumProperty(Enums.EVEN_PAGE);

  protected final static EnumProperty s_propODD_PAGE = new EnumProperty(Enums.ODD_PAGE);


    static public Property.Maker maker(String propName) {
	return new GenericBreak(propName);
    }

    protected GenericBreak(String name) {
	super(name);

    }


   public boolean isInherited() { return false; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("column")) { return s_propCOLUMN; }
    
      if (value.equals("page")) { return s_propPAGE; }
    
      if (value.equals("even-page")) { return s_propEVEN_PAGE; }
    
      if (value.equals("odd-page")) { return s_propODD_PAGE; }
    
	return super.checkEnumValues(value);
    }

    private Property m_defaultProp=null;
  
    public Property make(PropertyList propertyList) throws FOPException {
      
        if (m_defaultProp == null) {
            m_defaultProp=make(propertyList, "auto", propertyList.getParentFObj());
	}
        return m_defaultProp;
	
    }

}
