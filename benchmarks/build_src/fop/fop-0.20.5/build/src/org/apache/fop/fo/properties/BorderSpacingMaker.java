
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class BorderSpacingMaker extends  LengthPairProperty.Maker {



    final private static Property.Maker s_BlockProgressionDirectionMaker =
	new LengthProperty.Maker(
	     "border-spacing.block-progression-direction");
	
    final private static Property.Maker s_InlineProgressionDirectionMaker =
	new LengthProperty.Maker(
	     "border-spacing.inline-progression-direction");
	

    static public Property.Maker maker(String propName) {
	return new BorderSpacingMaker(propName);
    }

    protected BorderSpacingMaker(String name) {
	super(name);

        m_shorthandMaker= getSubpropMaker("block-progression-direction");

    }


    Property.Maker m_shorthandMaker;

    public Property checkEnumValues(String value) {
	return m_shorthandMaker.checkEnumValues(value);
    }

    protected boolean isCompoundMaker() {
      return true;
    }

    protected Property.Maker getSubpropMaker(String subprop) {
    
	if (subprop.equals("block-progression-direction"))
	  return s_BlockProgressionDirectionMaker;
    
	if (subprop.equals("inline-progression-direction"))
	  return s_InlineProgressionDirectionMaker;
    
	return super.getSubpropMaker(subprop);
    }

    protected Property setSubprop(Property baseProp, String subpropName,
	Property subProp) {
        LengthPair val = baseProp.getLengthPair();
	// Do some type checking???
	// Check if one of our subproperties???
	val.setComponent(subpropName, subProp, false);
	return baseProp;
    }

    public Property getSubpropValue(Property baseProp, String subpropName) {
      LengthPair val = baseProp.getLengthPair();
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
	LengthPair p = new LengthPair();
	Property subProp;
    
	 // set default for subprop block-progression-direction
	 subProp = getSubpropMaker("block-progression-direction").make(pList,
	  getDefaultForBlockProgressionDirection(), fo);
	  p.setComponent("block-progression-direction", subProp, true);
    
	 // set default for subprop inline-progression-direction
	 subProp = getSubpropMaker("inline-progression-direction").make(pList,
	  getDefaultForInlineProgressionDirection(), fo);
	  p.setComponent("inline-progression-direction", subProp, true);
    
	return new LengthPairProperty(p);
    }

    
    
    protected String getDefaultForBlockProgressionDirection() {
      
	return "0pt";
        
    }
    
    protected String getDefaultForInlineProgressionDirection() {
      
	return "0pt";
        
    }
    

    /** Set the appropriate components when the "base" property is set. */
    public Property convertProperty(Property p, PropertyList pList,FObj fo)
	throws FOPException
    {
        if (p instanceof LengthPairProperty) return p;
	if (! (p instanceof EnumProperty)) {
	  // delegate to the subprop maker to do conversions
	  p = m_shorthandMaker.convertProperty(p,pList,fo);
        }
	if (p != null) {
	  Property prop = makeCompound(pList, fo);

	  LengthPair pval = prop.getLengthPair();

	  pval.setComponent("block-progression-direction", p, false);

	  pval.setComponent("inline-progression-direction", p, false);

          return prop;
        }
	else return null;

    }


   public boolean isInherited() { return true; }

}
