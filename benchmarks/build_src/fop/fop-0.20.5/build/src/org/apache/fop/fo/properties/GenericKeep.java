
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class GenericKeep extends  KeepProperty.Maker {



  public interface Enums {

  public interface WithinPage {

    int AUTO =  Constants.AUTO;

    int ALWAYS =  Constants.ALWAYS;

  }

  public interface WithinLine {

    int AUTO =  Constants.AUTO;

    int ALWAYS =  Constants.ALWAYS;

  }

  public interface WithinColumn {

    int AUTO =  Constants.AUTO;

    int ALWAYS =  Constants.ALWAYS;

  }

  }

    static private class SP_WithinPageMaker
	extends NumberProperty.Maker implements Enums.WithinPage {
	SP_WithinPageMaker(String sPropName) {
	     super(sPropName);
        }

       protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

       protected final static EnumProperty s_propALWAYS = new EnumProperty(ALWAYS);

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("always")) { return s_propALWAYS; }
    
	return super.checkEnumValues(value);
    }

    }
    final private static Property.Maker s_WithinPageMaker =
	new SP_WithinPageMaker(
	     "generic-keep.within-page");
        
    static private class SP_WithinLineMaker
	extends NumberProperty.Maker implements Enums.WithinLine {
	SP_WithinLineMaker(String sPropName) {
	     super(sPropName);
        }

       protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

       protected final static EnumProperty s_propALWAYS = new EnumProperty(ALWAYS);

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("always")) { return s_propALWAYS; }
    
	return super.checkEnumValues(value);
    }

    }
    final private static Property.Maker s_WithinLineMaker =
	new SP_WithinLineMaker(
	     "generic-keep.within-line");
        
    static private class SP_WithinColumnMaker
	extends NumberProperty.Maker implements Enums.WithinColumn {
	SP_WithinColumnMaker(String sPropName) {
	     super(sPropName);
        }

       protected final static EnumProperty s_propAUTO = new EnumProperty(AUTO);

       protected final static EnumProperty s_propALWAYS = new EnumProperty(ALWAYS);

    public Property checkEnumValues(String value) {
    
      if (value.equals("auto")) { return s_propAUTO; }
    
      if (value.equals("always")) { return s_propALWAYS; }
    
	return super.checkEnumValues(value);
    }

    }
    final private static Property.Maker s_WithinColumnMaker =
	new SP_WithinColumnMaker(
	     "generic-keep.within-column");
        

    static public Property.Maker maker(String propName) {
	return new GenericKeep(propName);
    }

    protected GenericKeep(String name) {
	super(name);

        m_shorthandMaker= getSubpropMaker("within-page");

    }


    Property.Maker m_shorthandMaker;

    public Property checkEnumValues(String value) {
	return m_shorthandMaker.checkEnumValues(value);
    }

    protected boolean isCompoundMaker() {
      return true;
    }

    protected Property.Maker getSubpropMaker(String subprop) {
    
	if (subprop.equals("within-page"))
	  return s_WithinPageMaker;
    
	if (subprop.equals("within-line"))
	  return s_WithinLineMaker;
    
	if (subprop.equals("within-column"))
	  return s_WithinColumnMaker;
    
	return super.getSubpropMaker(subprop);
    }

    protected Property setSubprop(Property baseProp, String subpropName,
	Property subProp) {
        Keep val = baseProp.getKeep();
	// Do some type checking???
	// Check if one of our subproperties???
	val.setComponent(subpropName, subProp, false);
	return baseProp;
    }

    public Property getSubpropValue(Property baseProp, String subpropName) {
      Keep val = baseProp.getKeep();
      return val.getComponent(subpropName);
    }

    private Property m_defaultProp=null;
    public Property make(PropertyList propertyList) throws FOPException {
        if (m_defaultProp == null) {
            m_defaultProp=makeCompound(propertyList, propertyList.getParentFObj());
	}
        return m_defaultProp;
    }


    protected Property makeCompound(PropertyList pList, FObj fo) throws FOPException {
	Keep p = new Keep();
	Property subProp;
    
	 // set default for subprop within-page
	 subProp = getSubpropMaker("within-page").make(pList,
	  getDefaultForWithinPage(), fo);
	  p.setComponent("within-page", subProp, true);
    
	 // set default for subprop within-line
	 subProp = getSubpropMaker("within-line").make(pList,
	  getDefaultForWithinLine(), fo);
	  p.setComponent("within-line", subProp, true);
    
	 // set default for subprop within-column
	 subProp = getSubpropMaker("within-column").make(pList,
	  getDefaultForWithinColumn(), fo);
	  p.setComponent("within-column", subProp, true);
    
	return new KeepProperty(p);
    }

    
    
    protected String getDefaultForWithinPage() {
      
	return "auto";
        
    }
    
    protected String getDefaultForWithinLine() {
      
	return "auto";
        
    }
    
    protected String getDefaultForWithinColumn() {
      
	return "auto";
        
    }
    

    /** Set the appropriate components when the "base" property is set. */
    public Property convertProperty(Property p, PropertyList pList,FObj fo)
	throws FOPException
    {
        if (p instanceof KeepProperty) return p;
	if (! (p instanceof EnumProperty)) {
	  // delegate to the subprop maker to do conversions
	  p = m_shorthandMaker.convertProperty(p,pList,fo);
        }
	if (p != null) {
	  Property prop = makeCompound(pList, fo);

	  Keep pval = prop.getKeep();

	  pval.setComponent("within-page", p, false);

	  pval.setComponent("within-line", p, false);

	  pval.setComponent("within-column", p, false);

          return prop;
        }
	else return null;

    }


}
