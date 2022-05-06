
package org.apache.fop.fo.properties;

import java.util.Hashtable;
import org.apache.fop.datatypes.*;
import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

public class GenericColor extends  ColorTypeProperty.Maker {




    static public Property.Maker maker(String propName) {
	return new GenericColor(propName);
    }

    protected GenericColor(String name) {
	super(name);

    }


    // Initialize hashtable of keywords
    static Hashtable s_htKeywords;
    static {
	s_htKeywords = new Hashtable(147);
  
      	s_htKeywords.put("aliceblue", "#f0f8ff");
  
      	s_htKeywords.put("antiquewhite", "#faebd7");
  
      	s_htKeywords.put("aqua", "#00ffff");
  
      	s_htKeywords.put("aquamarine", "#7fffd4");
  
      	s_htKeywords.put("azure", "#f0ffff");
  
      	s_htKeywords.put("beige", "#f5f5dc");
  
      	s_htKeywords.put("bisque", "#ffe4c4");
  
      	s_htKeywords.put("black", "#000000");
  
      	s_htKeywords.put("blanchedalmond", "#ffebcd");
  
      	s_htKeywords.put("blue", "#0000ff");
  
      	s_htKeywords.put("blueviolet", "#8a2be2");
  
      	s_htKeywords.put("brown", "#a52a2a");
  
      	s_htKeywords.put("burlywood", "#deb887");
  
      	s_htKeywords.put("cadetblue", "#5f9ea0");
  
      	s_htKeywords.put("chartreuse", "#7fff00");
  
      	s_htKeywords.put("chocolate", "#d2691e");
  
      	s_htKeywords.put("coral", "#ff7f50");
  
      	s_htKeywords.put("cornflowerblue", "#6495ed");
  
      	s_htKeywords.put("cornsilk", "#fff8dc");
  
      	s_htKeywords.put("crimson", "#dc143c");
  
      	s_htKeywords.put("cyan", "#00ffff");
  
      	s_htKeywords.put("darkblue", "#00008b");
  
      	s_htKeywords.put("darkcyan", "#008b8b");
  
      	s_htKeywords.put("darkgoldenrod", "#b8860b");
  
      	s_htKeywords.put("darkgray", "#a9a9a9");
  
      	s_htKeywords.put("darkgreen", "#006400");
  
      	s_htKeywords.put("darkgrey", "#a9a9a9");
  
      	s_htKeywords.put("darkkhaki", "#bdb76b");
  
      	s_htKeywords.put("darkmagenta", "#8b008b");
  
      	s_htKeywords.put("darkolivegreen", "#556b2f");
  
      	s_htKeywords.put("darkorange", "#ff8c00");
  
      	s_htKeywords.put("darkorchid", "#9932cc");
  
      	s_htKeywords.put("darkred", "#8b0000");
  
      	s_htKeywords.put("darksalmon", "#e9967a");
  
      	s_htKeywords.put("darkseagreen", "#8fbc8f");
  
      	s_htKeywords.put("darkslateblue", "#483d8b");
  
      	s_htKeywords.put("darkslategray", "#2f4f4f");
  
      	s_htKeywords.put("darkslategrey", "#2f4f4f");
  
      	s_htKeywords.put("darkturquoise", "#00ced1");
  
      	s_htKeywords.put("darkviolet", "#9400d3");
  
      	s_htKeywords.put("deeppink", "#ff1493");
  
      	s_htKeywords.put("deepskyblue", "#00bfff");
  
      	s_htKeywords.put("dimgray", "#696969");
  
      	s_htKeywords.put("dimgrey", "#696969");
  
      	s_htKeywords.put("dodgerblue", "#1e90ff");
  
      	s_htKeywords.put("firebrick", "#b22222");
  
      	s_htKeywords.put("floralwhite", "#fffaf0");
  
      	s_htKeywords.put("forestgreen", "#228b22");
  
      	s_htKeywords.put("fuchsia", "#ff00ff");
  
      	s_htKeywords.put("gainsboro", "#dcdcdc");
  
      	s_htKeywords.put("lightpink", "#ffb6c1");
  
      	s_htKeywords.put("lightsalmon", "#ffa07a");
  
      	s_htKeywords.put("lightseagreen", "#20b2aa");
  
      	s_htKeywords.put("lightskyblue", "#87cefa");
  
      	s_htKeywords.put("lightslategray", "#778899");
  
      	s_htKeywords.put("lightslategrey", "#778899");
  
      	s_htKeywords.put("lightsteelblue", "#b0c4de");
  
      	s_htKeywords.put("lightyellow", "#ffffe0");
  
      	s_htKeywords.put("lime", "#00ff00");
  
      	s_htKeywords.put("limegreen", "#32cd32");
  
      	s_htKeywords.put("linen", "#faf0e6");
  
      	s_htKeywords.put("magenta", "#ff00ff");
  
      	s_htKeywords.put("maroon", "#800000");
  
      	s_htKeywords.put("mediumaquamarine", "#66cdaa");
  
      	s_htKeywords.put("mediumblue", "#0000cd");
  
      	s_htKeywords.put("mediumorchid", "#ba55d3");
  
      	s_htKeywords.put("mediumpurple", "#9370db");
  
      	s_htKeywords.put("mediumseagreen", "#3cb371");
  
      	s_htKeywords.put("mediumslateblue", "#7b68ee");
  
      	s_htKeywords.put("mediumspringgreen", "#00fa9a");
  
      	s_htKeywords.put("mediumturquoise", "#48d1cc");
  
      	s_htKeywords.put("mediumvioletred", "#c71585");
  
      	s_htKeywords.put("midnightblue", "#191970");
  
      	s_htKeywords.put("mintcream", "#f5fffa");
  
      	s_htKeywords.put("mistyrose", "#ffe4e1");
  
      	s_htKeywords.put("moccasin", "#ffe4b5");
  
      	s_htKeywords.put("navajowhite", "#ffdead");
  
      	s_htKeywords.put("navy", "#000080");
  
      	s_htKeywords.put("oldlace", "#fdf5e6");
  
      	s_htKeywords.put("olive", "#808000");
  
      	s_htKeywords.put("olivedrab", "#6b8e23");
  
      	s_htKeywords.put("orange", "#ffa500");
  
      	s_htKeywords.put("orangered", "#ff4500");
  
      	s_htKeywords.put("orchid", "#da70d6");
  
      	s_htKeywords.put("palegoldenrod", "#eee8aa");
  
      	s_htKeywords.put("palegreen", "#98fb98");
  
      	s_htKeywords.put("paleturquoise", "#afeeee");
  
      	s_htKeywords.put("palevioletred", "#db7093");
  
      	s_htKeywords.put("papayawhip", "#ffefd5");
  
      	s_htKeywords.put("peachpuff", "#ffdab9");
  
      	s_htKeywords.put("peru", "#cd853f");
  
      	s_htKeywords.put("pink", "#ffc0cb");
  
      	s_htKeywords.put("plum", "#dda0dd");
  
      	s_htKeywords.put("powderblue", "#b0e0e6");
  
      	s_htKeywords.put("purple", "#800080");
  
      	s_htKeywords.put("red", "#ff0000");
  
      	s_htKeywords.put("rosybrown", "#bc8f8f");
  
      	s_htKeywords.put("royalblue", "#4169e1");
  
      	s_htKeywords.put("saddlebrown", "#8b4513");
  
      	s_htKeywords.put("salmon", "#fa8072");
  
      	s_htKeywords.put("ghostwhite", "#f8f8ff");
  
      	s_htKeywords.put("gold", "#ffd700");
  
      	s_htKeywords.put("goldenrod", "#daa520");
  
      	s_htKeywords.put("gray", "#808080");
  
      	s_htKeywords.put("grey", "#808080");
  
      	s_htKeywords.put("green", "#008000");
  
      	s_htKeywords.put("greenyellow", "#adff2f");
  
      	s_htKeywords.put("honeydew", "#f0fff0");
  
      	s_htKeywords.put("hotpink", "#ff69b4");
  
      	s_htKeywords.put("indianred", "#cd5c5c");
  
      	s_htKeywords.put("indigo", "#4b0082");
  
      	s_htKeywords.put("ivory", "#fffff0");
  
      	s_htKeywords.put("khaki", "#f0e68c");
  
      	s_htKeywords.put("lavender", "#e6e6fa");
  
      	s_htKeywords.put("lavenderblush", "#fff0f5");
  
      	s_htKeywords.put("lawngreen", "#7cfc00");
  
      	s_htKeywords.put("lemonchiffon", "#fffacd");
  
      	s_htKeywords.put("lightblue", "#add8e6");
  
      	s_htKeywords.put("lightcoral", "#f08080");
  
      	s_htKeywords.put("lightcyan", "#e0ffff");
  
      	s_htKeywords.put("lightgoldenrodyellow", "#fafad2");
  
      	s_htKeywords.put("lightgray", "#d3d3d3");
  
      	s_htKeywords.put("lightgreen", "#90ee90");
  
      	s_htKeywords.put("lightgrey", "#d3d3d3");
  
      	s_htKeywords.put("sandybrown", "#f4a460");
  
      	s_htKeywords.put("seagreen", "#2e8b57");
  
      	s_htKeywords.put("seashell", "#fff5ee");
  
      	s_htKeywords.put("sienna", "#a0522d");
  
      	s_htKeywords.put("silver", "#c0c0c0");
  
      	s_htKeywords.put("skyblue", "#87ceeb");
  
      	s_htKeywords.put("slateblue", "#6a5acd");
  
      	s_htKeywords.put("slategray", "#708090");
  
      	s_htKeywords.put("slategrey", "#708090");
  
      	s_htKeywords.put("snow", "#fffafa");
  
      	s_htKeywords.put("springgreen", "#00ff7f");
  
      	s_htKeywords.put("steelblue", "#4682b4");
  
      	s_htKeywords.put("tan", "#d2b48c");
  
      	s_htKeywords.put("teal", "#008080");
  
      	s_htKeywords.put("thistle", "#d8bfd8");
  
      	s_htKeywords.put("tomato", "#ff6347");
  
      	s_htKeywords.put("turquoise", "#40e0d0");
  
      	s_htKeywords.put("violet", "#ee82ee");
  
      	s_htKeywords.put("wheat", "#f5deb3");
  
      	s_htKeywords.put("white", "#ffffff");
  
      	s_htKeywords.put("whitesmoke", "#f5f5f5");
  
      	s_htKeywords.put("yellow", "#ffff00");
  
      	s_htKeywords.put("yellowgreen", "#9acd32");
  
    }
    protected String checkValueKeywords(String keyword) {
      String value = (String)s_htKeywords.get(keyword);
      if (value == null) {
	return super.checkValueKeywords(keyword);
      }
      else return value;
    }

}
