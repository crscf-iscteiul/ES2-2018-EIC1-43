package main;

import gui.GUI;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main{

    public static void main(String[] args){
        HashMap<String, String> adminInfo = new HashMap<>();
        System.out.println(System.getProperty("user.dir")+"\\config.xml");
        try {
            File config = new File(System.getProperty("user.dir") + "\\config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(config);
            doc.getDocumentElement().normalize();

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            XPathExpression expr = xpath.compile("/CONFIG/Administrator/@*");

            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for(int i = 0; i < nl.getLength(); i+=2) {
                String name = nl.item(i).getFirstChild().getNodeValue();
                String email = nl.item(i+1).getFirstChild().getNodeValue();
                adminInfo.put(name, email);
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("Make sure configuration file is available.");
        } catch(Exception e) {

        }

        GUI.getInstance();
    }


}


/*   TO ITERATE HASH-MAP

            Iterator it = adminInfo.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " : " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }

 */