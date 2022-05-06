
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class HyphenationKeepMaker extends  EnumProperty.Maker implements HyphenationKeep {



  protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

  protected final static EnumProperty s_propCOLUMN = new EnumProperty(COLUMN);

  protected final static EnumProperty s_propPAGE = new EnumProperty(PAGE);

  protected final static EnumProperty s_propINHERIT = new EnumProperty(INHERIT);


    static public Property.Maker maker(String propName) {
	return new HyphenationKeepMaker(propName);
    }

    protected HyphenationKeepMaker(String name) {
	super(name);

    }


   public boolean isInherited() { return true; }

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("column")) { return s_propCOLUMN; }
    
      if (value.equals("page")) { return s_propPAGE; }
    
      if (value.equals("inherit")) { return s_propINHERIT; }
    
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
