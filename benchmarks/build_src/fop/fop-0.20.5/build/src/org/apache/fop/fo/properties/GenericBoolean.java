
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class GenericBoolean extends  EnumProperty.Maker {



  public interface Enums {

    int TRUE =  Constants.TRUE;

    int FALSE =  Constants.FALSE;

  }

  protected final static EnumProperty s_propTRUE = new EnumProperty(Enums.TRUE);

  protected final static EnumProperty s_propFALSE = new EnumProperty(Enums.FALSE);


    static public Property.Maker maker(String propName) {
	return new GenericBoolean(propName);
    }

    protected GenericBoolean(String name) {
	super(name);

    }


    public Property checkEnumValues(String value) {
    
      if (value.equals("true")) { return s_propTRUE; }
    
      if (value.equals("false")) { return s_propFALSE; }
    
	return super.checkEnumValues(value);
    }

}
