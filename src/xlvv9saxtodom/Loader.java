/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xlvv9saxtodom;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Xinhui Li
 */
public class Loader {
            static Node node;
    static ArrayList list = new ArrayList<String>();
    
    public static void load(File xmlCourseFile) throws Exception {
        //Course course = new Course();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                XMLNode root = new XMLNode();
                XMLNode currentnode = new XMLNode();
                Stack<XMLNode> stack = new Stack<XMLNode>();

                @Override
                public void characters(char[] arg0, int arg1, int arg2) throws SAXException { 
                    System.out.print(new String(arg0, arg1, arg2)); 
                    currentnode.content += new String(arg0, arg1, arg2);
                    super.characters(arg0, arg1, arg2); 
                } 

                @Override
                public void endDocument() throws SAXException { 
                    System.out.println("\nParse end"); 
                    super.endDocument(); 
                } 

                @Override
                public void endElement(String arg0, String arg1, String arg2) 
                        throws SAXException { 
                    System.out.print("</"); 
                    System.out.print(arg2); 

                    XMLNode poppedNode = stack.pop();
                    poppedNode.content = poppedNode.content.trim();
                    if (stack.isEmpty())
                    {
                        root = poppedNode;
                        currentnode = null;
                    }
                    else
                    {
                        currentnode = stack.lastElement();
                    }
                    System.out.print(">"); 
                    super.endElement(arg0, arg1, arg2); 
                } 

                @Override
                public void startDocument() throws SAXException { 
                    System.out.println("Start Parse"); 
                    root =null;
                    super.startDocument(); 
                } 

                @Override
                public void startElement(String arg0, String arg1, String arg2, 
                    Attributes arg3) throws SAXException { 
                      
                    System.out.print("<"); 
                    System.out.print(arg2); 

                    XMLNode node = new XMLNode();
                    node.name = arg2;
                    stack.add(node);
                    
                    if (currentnode.properties.get(arg2) != null)
                    {
                        currentnode.properties.get(arg2).add(node);
                    }
                    else
                    {
                        ArrayList<XMLNode> tmplist = new ArrayList<XMLNode>();
                        tmplist.add(node);
                        currentnode.properties.put(arg2, tmplist);
                    }
                    currentnode = node;
                    
                    if (arg3 != null) { 
                        for (int i = 0; i < arg3.getLength(); i++) { 
                            System.out.print(" " + arg3.getQName(i) + "=\"" + arg3.getValue(i) + "\""); ;
                            node.attributes.put(arg3.getQName(i), arg3.getValue(i));
                        } 
                    } 
                    System.out.print(">"); 
                    super.startElement(arg0, arg1, arg2, arg3); 
                }         
            };
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);  
        } catch (Exception e) {
            throw e;
        }
    }
}
