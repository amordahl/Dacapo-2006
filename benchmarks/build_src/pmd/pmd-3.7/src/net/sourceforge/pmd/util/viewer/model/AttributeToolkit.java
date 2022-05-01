package net.sourceforge.pmd.util.viewer.model;


import net.sourceforge.pmd.jaxen.Attribute;


/**
 * A toolkit for vaious attribute translations
 *
 * @author Boris Gruschko ( boris at gruschko.org )
 * @version $Id: AttributeToolkit.java,v 1.8 2006/02/10 14:26:32 tomcopeland Exp $
 */

public class AttributeToolkit {

    /**
     * formats a value for its usage in XPath expressions
     *
     * @param attribute atribute which value should be formatted
     * @return formmated value
     */
    public static String formatValueForXPath(Attribute attribute) {
        return "'" + attribute.getValue() + "'";
    }

    /**
     * constructs a predicate from the given attribute
     *
     * @param attribute attribute to be formatted as predicate
     * @return predicate
     */
    public static String constructPredicate(Attribute attribute) {
        return "[@" + attribute.getName() + "=" +
                formatValueForXPath(attribute) + "]";
    }
}