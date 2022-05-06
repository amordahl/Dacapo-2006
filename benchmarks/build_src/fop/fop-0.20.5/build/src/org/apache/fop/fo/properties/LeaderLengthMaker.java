
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class LeaderLengthMaker extends  LengthRangeProperty.Maker {



    static private class SP_MinimumMaker
	extends LengthProperty.Maker {
	SP_MinimumMaker(String sPropName) {
	     super(sPropName);
        }

    /** Return object used to calculate base Length
     * for percent specifications.
     */
    public PercentBase getPercentBase(final FObj fo, final PropertyList propertyList) {
     
	return new LengthBase(fo, propertyList, LengthBase.CONTAINING_BOX);
       
    }

    }
    final private static Property.Maker s_MinimumMaker =
	new SP_MinimumMaker(
	     "leader-length.minimum");
        
    static private class SP_OptimumMaker
	extends LengthProperty.Maker {
	SP_OptimumMaker(String sPropName) {
	     super(sPropName);
        }

    /** Return object used to calculate base Length
     * for percent specifications.
     */
    public PercentBase getPercentBase(final FObj fo, final PropertyList propertyList) {
     
	return new LengthBase(fo, propertyList, LengthBase.CONTAINING_BOX);
       
    }

    }
    final private static Property.Maker s_OptimumMaker =
	new SP_OptimumMaker(
	     "leader-length.optimum");
        
    static private class SP_MaximumMaker
	extends LengthProperty.Maker {
	SP_MaximumMaker(String sPropName) {
	     super(sPropName);
        }

    /** Return object used to calculate base Length
     * for percent specifications.
     */
    public PercentBase getPercentBase(final FObj fo, final PropertyList propertyList) {
     
	return new LengthBase(fo, propertyList, LengthBase.CONTAINING_BOX);
       
    }

    }
    final private static Property.Maker s_MaximumMaker =
	new SP_MaximumMaker(
	     "leader-length.maximum");
        

    static public Property.Maker maker(String propName) {
	return new LeaderLengthMaker(propName);
    }

    protected LeaderLengthMaker(String name) {
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
    
	return super.getSubpropMaker(subprop);
    }

    protected Property setSubprop(Property baseProp, String subpropName,
	Property subProp) {
        LengthRange val = baseProp.getLengthRange();
	// Do some type checking???
	// Check if one of our subproperties???
	val.setComponent(subpropName, subProp, false);
	return baseProp;
    }

    public Property getSubpropValue(Property baseProp, String subpropName) {
      LengthRange val = baseProp.getLengthRange();
      return val.getComponent(subpropName);
    }

    public Property make(PropertyList propertyList) throws FOPException {
        return makeCompound(propertyList, propertyList.getParentFObj());
    }


    protected Property makeCompound(PropertyList pList, FObj fo) throws FOPException {
	LengthRange p = new LengthRange();
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
    
	return new LengthRangeProperty(p);
    }

    
    
    protected String getDefaultForMinimum() {
      
	return "0pt";
        
    }
    
    protected String getDefaultForOptimum() {
      
	return "12.0pt";
        
    }
    
    protected String getDefaultForMaximum() {
      
	return "100%";
        
    }
    

    /** Set the appropriate components when the "base" property is set. */
    public Property convertProperty(Property p, PropertyList pList,FObj fo)
	throws FOPException
    {
        if (p instanceof LengthRangeProperty) return p;
	if (! (p instanceof EnumProperty)) {
	  // delegate to the subprop maker to do conversions
	  p = m_shorthandMaker.convertProperty(p,pList,fo);
        }
	if (p != null) {
	  Property prop = makeCompound(pList, fo);

	  LengthRange pval = prop.getLengthRange();

	  pval.setComponent("minimum", p, false);

	  pval.setComponent("optimum", p, false);

	  pval.setComponent("maximum", p, false);

          return prop;
        }
	else return null;

    }


   public boolean isInherited() { return true; }

    /** Return object used to calculate base Length
     * for percent specifications.
     */
    public PercentBase getPercentBase(final FObj fo, final PropertyList propertyList) {
     
	return new LengthBase(fo, propertyList, LengthBase.CONTAINING_BOX);
       
    }

}
