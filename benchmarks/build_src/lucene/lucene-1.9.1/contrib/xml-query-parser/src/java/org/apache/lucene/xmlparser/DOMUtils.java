package org.apache.lucene.xmlparser;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class DOMUtils
{
	/* Convenience method where there is only one child Element of a given name */
	public static Element getChildByTagName(Element e, String name)
	{
	       for (Node kid = e.getFirstChild(); kid != null; kid = kid.getNextSibling())
		{
			if( (kid.getNodeType()==Node.ELEMENT_NODE) && (name.equals(kid.getNodeName())) )
			{
				return (Element)kid;
			}
		}
		return null;
	}

	/**
	 * Returns an attribute value from this node, or first parent node with this attribute defined
	 * @param element 
	 * @param attributeName
	 * @return A non-zero-length value if defined, otherwise null
	 */
	public static String getAttributeWithInheritance(Element element, String attributeName)
	{
		String result=element.getAttribute(attributeName);
		if( (result==null)|| ("".equals(result) ) )
		{
			Node n=element.getParentNode();
			if((n==element)||(n==null))
			{
				return null;
			}
			Element parent=(Element) n;
			return getAttributeWithInheritance(parent,attributeName);
		}
		return result;		
	}



	/* Convenience method where there is only one child Element of a given name */
	public static String getChildTextByTagName(Element e, String tagName)
	{
		Element child=getChildByTagName(e,tagName);
		if(child!=null)
		{
			return getText(child);
		}
		return null;
	}

	/* Convenience method to append a new child with text*/
	public static Element insertChild(Element parent, String tagName, String text)
	{
	  	Element child = parent.getOwnerDocument().createElement(tagName);
		parent.appendChild(child);
		if(text!=null)
		{
		  	child.appendChild(child.getOwnerDocument().createTextNode(text));
		}
		return child;
	}

	public static String getAttribute(Element element, String attributeName, String deflt)
	{
		String result=element.getAttribute(attributeName);
		if( (result==null)|| ("".equals(result) ) )
		{
			return deflt;
		}
		return result;
	}
	public static float getAttribute(Element element, String attributeName, float deflt)
	{
		String result=element.getAttribute(attributeName);
		if( (result==null)|| ("".equals(result) ) )
		{
			return deflt;
		}
		return Float.parseFloat(result);
	}	

	public static int getAttribute(Element element, String attributeName, int deflt)
	{
		String result=element.getAttribute(attributeName);
		if( (result==null)|| ("".equals(result) ) )
		{
			return deflt;
		}
		return Integer.parseInt(result);
	}
	
	public static boolean getAttribute(Element element, String attributeName,
			boolean deflt)
	{
		String result = element.getAttribute(attributeName);
		if ((result == null) || ("".equals(result)))
		{
			return deflt;
		}
		return Boolean.getBoolean(result);
	}	

	/* Returns text of node and all child nodes - without markup */
	//MH changed to Node from Element 25/11/2005
	public static String getText(Node e)
	{
		StringBuffer sb=new StringBuffer();
		getTextBuffer(e, sb);
		return sb.toString();
	}
	
	public static Element getFirstChildElement(Element element)
	{
		for (Node kid = element.getFirstChild(); kid != null; kid = kid
				.getNextSibling())
		{
			if (kid.getNodeType() == Node.ELEMENT_NODE) 
			{
				return (Element) kid;
			}
		}
		return null;
	}	

	private static void getTextBuffer(Node e, StringBuffer sb)
	{
	    for (Node kid = e.getFirstChild(); kid != null; kid = kid.getNextSibling())
		{
			switch(kid.getNodeType())
			{
				case Node.TEXT_NODE:
				{
					sb.append(kid.getNodeValue());
					break;
				}
				case Node.ELEMENT_NODE:
				{
					getTextBuffer(kid, sb);
					break;
				}
				case Node.ENTITY_REFERENCE_NODE:
				{
					getTextBuffer(kid, sb);
					break;
				}
			}
		}
	}

	/**
	* Helper method to parse an XML file into a DOM tree, given a filename.
	* @param pXmlFile name of the XML file to be parsed
	* @return an org.w3c.dom.Document object
	*/
	public static Document loadXML(Reader is)
	{

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		
		try
		{
			db = dbf.newDocumentBuilder();
		}
		catch (Exception se)
		{
			throw new RuntimeException("Parser configuration error", se);
		}

		// Step 3: parse the input file
		org.w3c.dom.Document doc = null;
		try
		{
			doc = db.parse(new InputSource(is));
			//doc = db.parse(is);
		}
		catch (Exception se)
		{
			throw new RuntimeException("Error parsing file:" + se, se);
		}

		return doc;
	}
}



