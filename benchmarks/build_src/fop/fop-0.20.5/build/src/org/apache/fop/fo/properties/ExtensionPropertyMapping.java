
package org.apache.fop.fo.properties;

import java.util.HashMap;
import java.util.Set;
//import org.apache.fop.svg.*;

public class ExtensionPropertyMapping {

  private static HashMap s_htGeneric = new HashMap();
  private static HashMap s_htElementLists = new HashMap();
  
  static {
    // Generate the generic mapping
    s_htGeneric.put("external-destination", ExternalDestinationMaker.maker("external-destination"));
    s_htGeneric.put("internal-destination", InternalDestinationMaker.maker("internal-destination"));
    s_htGeneric.put("destination-name", DestinationNameMaker.maker("destination-name"));

  }


  public static HashMap getGenericMappings() {
    return s_htGeneric;
  }

  public static Set getElementMappings() {
    return s_htElementLists.keySet();
  }

  public static HashMap getElementMapping(String elemName) {
    return (HashMap)s_htElementLists.get(elemName);
  }
}
