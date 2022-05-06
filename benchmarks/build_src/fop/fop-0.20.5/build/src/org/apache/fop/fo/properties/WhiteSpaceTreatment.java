
package org.apache.fop.fo.properties;


    public interface WhiteSpaceTreatment{
   
     public final static int IGNORE = Constants.IGNORE;

     public final static int PRESERVE = Constants.PRESERVE;

     public final static int IGNORE_IF_BEFORE_LINEFEED = Constants.IGNORE_IF_BEFORE_LINEFEED;

     public final static int IGNORE_IF_AFTER_LINEFEED = Constants.IGNORE_IF_AFTER_LINEFEED;

     public final static int IGNORE_IF_SURROUNDING_LINEFEED = Constants.IGNORE_IF_SURROUNDING_LINEFEED;

     public final static int INHERIT = Constants.INHERIT;

    }
