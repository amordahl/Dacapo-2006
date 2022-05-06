
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class GenericSpace extends  SpaceProperty.Maker {



  public interface Enums {

  public interface Precedence {

    int FORCE =  Constants.FORCE;

  }

  public interface Conditionality {

    int DISCARD =  Constants.DISCARD;

    int RETAIN =  Constants.RETAIN;

  }

  }

    final private static Property.Maker s_MinimumMaker =
	new LengthProperty.Maker(
	     "generic-space.minimum");
	
    final private static Property.Maker s_OptimumMaker =
	new LengthProperty.Maker(
	     "generic-space.optimum");
	
    final private static Property.Maker s_MaximumMaker =
	new LengthProperty.Maker(
	     "generic-space.maximum");
	
    static private class SP_PrecedenceMaker
	extends NumberProperty.Maker implements Enums.Precedence {
	SP_PrecedenceMaker(String sPropName) {
	     super(sPropName);
        }

       protected final static EnumProperty s_propFORCE = new EnumProperty(FORCE);

    public Property checkEnumValues(String value) {
    
      if (value.equals("force")) { return s_propFORCE; }
    
	return super.checkEnumValues(value);
    }

    }
    final private static Property.Maker s_PrecedenceMaker =
	new SP_PrecedenceMaker(
	     "generic-space.precedence");
        
    static private class SP_ConditionalityMaker
	extends EnumProperty.Maker implements Enums.Conditionality {
	SP_ConditionalityMaker(String sPropName) {
	     super(sPropName);
        }

       protected final static EnumProperty s_propDISCARD = new EnumProperty(DISCARD);

       protected final static EnumProperty s_propRETAIN = new EnumProperty(RETAIN);

    public Property checkEnumValues(String value) {
    
      if (value.equals("discard")) { return s_propDISCARD; }
    
      if (value.equals("retain")) { return s_propRETAIN; }
    
	return super.checkEnumValues(value);
    }

    }
    final private static Property.Maker s_ConditionalityMaker =
	new SP_ConditionalityMaker(
	     "generic-space.conditionality");
        

    static public Property.Maker maker(String propName) {
	return new GenericSpace(propName);
    }

    protected GenericSpace(String name) {
	super(name);

        m_shorthandMaker= getSubpropMaker("minimum");

    }


    Property.Maker m_shorthandMaker;

    public Property checkEnumValues(String value) {
	return m_shorthandMaker.checkEnumValues(value);
    }

    protected boolean isCompoundMaker() {
      return true;
    }

    protected Property.Maker getSubpropMaker(String subprop) {
    
	if (subprop.equals("minimum"))
	  return s_MinimumMaker;
    
	if (subprop.equals("optimum"))
	  return s_OptimumMaker;
    
	if (subprop.equals("maximum"))
	  return s_MaximumMaker;
    
	if (subprop.equals("precedence"))
	  return s_PrecedenceMaker;
    
	if (subprop.equals("conditionality"))
	  return s_ConditionalityMaker;
    
	return super.getSubpropMaker(subprop);
    }

    protected Property setSubprop(Property baseProp, String subpropName,
	Property subProp) {
        Space val = baseProp.getSpace();
	// Do some type checking???
	// Check if one of our subproperties???
	val.setComponent(subpropName, subProp, false);
	return baseProp;
    }

    public Property getSubpropValue(Property baseProp, String subpropName) {
      Space val = baseProp.getSpace();
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
	Space p = new Space();
	Property subProp;
    
	 // set default for subprop minimum
	 subProp = getSubpropMaker("minimum").make(pList,
	  getDefaultForMinimum(), fo);
	  p.setComponent("minimum", subProp, true);
    
	 // set default for subprop optimum
	 subProp = getSubpropMaker("optimum").make(pList,
	  getDefaultForOptimum(), fo);
	  p.setComponent("optimum", subProp, true);
    
	 // set default for subprop maximum
	 subProp = getSubpropMaker("maximum").make(pList,
	  getDefaultForMaximum(), fo);
	  p.setComponent("maximum", subProp, true);
    
	 // set default for subprop precedence
	 subProp = getSubpropMaker("precedence").make(pList,
	  getDefaultForPrecedence(), fo);
	  p.setComponent("precedence", subProp, true);
    
	 // set default for subprop conditionality
	 subProp = getSubpropMaker("conditionality").make(pList,
	  getDefaultForConditionality(), fo);
	  p.setComponent("conditionality", subProp, true);
    
	return new SpaceProperty(p);
    }

    
    
    protected String getDefaultForMinimum() {
      
	return "0pt";
        
    }
    
    protected String getDefaultForOptimum() {
      
	return "0pt";
        
    }
    
    protected String getDefaultForMaximum() {
      
	return "0pt";
        
    }
    
    protected String getDefaultForPrecedence() {
      
	return "0";
        
    }
    
    protected String getDefaultForConditionality() {
      
	return "discard";
        
    }
    

    /** Set the appropriate components when the "base" property is set. */
    public Property convertProperty(Property p, PropertyList pList,FObj fo)
	throws FOPException
    {
        if (p instanceof SpaceProperty) return p;
	if (! (p instanceof EnumProperty)) {
	  // delegate to the subprop maker to do conversions
	  p = m_shorthandMaker.convertProperty(p,pList,fo);
        }
	if (p != null) {
	  Property prop = makeCompound(pList, fo);

	  Space pval = prop.getSpace();

	  pval.setComponent("minimum", p, false);

	  pval.setComponent("optimum", p, false);

	  pval.setComponent("maximum", p, false);

          return prop;
        }
	else return null;

    }


   public boolean isInherited() { return false; }

}
