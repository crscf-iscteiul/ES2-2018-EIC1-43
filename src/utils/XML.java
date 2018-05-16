package utils;

import gui.GUI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XML {

    /*        Variable[] vars = {new Variable("a", 4), new Variable("p", 0.0), new Variable("v", false), new Variable("c", false)};
        Problem p = new Problem("Test Problem", "Test problem relativo a criacao de xml",4,"00:05:00"  ,vars);

        saveProblemXML("test", p);
        Problem p_r = readXMLProblem("test");*/


    public static void saveXMLProblem(String file_path, Problem p) throws Exception{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("root");
        doc.appendChild(rootElement);

        // problem elements
        Element staff = doc.createElement("Problem");
        rootElement.appendChild(staff);

        // set attribute to problem element
        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(p.getProblemName()));
        staff.appendChild(name);

        Element desc = doc.createElement("Description");
        desc.appendChild(doc.createTextNode(p.getProblemDescription()));
        staff.appendChild(desc);

        Element variables = doc.createElement("Variables");
        staff.appendChild(variables);

        // variable elements
        for (int i = 0; i < p.getVariables().length; i++) {
            Element variable = doc.createElement(p.getVariables()[i].getVariableName().replace(" ", "_"));
            variable.appendChild(doc.createTextNode(p.getVariables()[i].getVariable().toString()));
            variables.appendChild(variable);
        }

        Element time = doc.createElement("Max_Time");
        time.appendChild(doc.createTextNode(p.getMaxTime()));
        staff.appendChild(time);

        Element maxVars = doc.createElement("Max_Vars");
        maxVars.appendChild(doc.createTextNode(String.valueOf(p.getNumVars())));
        staff.appendChild(maxVars);

        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(file_path));

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public static Problem readXMLProblem(String file_path) throws Exception {
        String p_description = "";
        String p_name = "";
        String m_Time = "";
        int mVars = 0;
        Variable[] vars = null;

        File config = new File(file_path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(config);
        doc.getDocumentElement().normalize();

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expr = xpath.compile("/root/Problem/*");

        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);


        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeName().equals("Name"))
                p_name = nl.item(i).getFirstChild().getNodeValue();
            if (nl.item(i).getNodeName().equals("Description"))
                p_description = nl.item(i).getFirstChild().getNodeValue();
            if (nl.item(i).getNodeName().equals("Max_Time"))
                m_Time = nl.item(i).getFirstChild().getNodeValue();
            if (nl.item(i).getNodeName().equals("Max_Vars"))
                mVars = Integer.parseInt(nl.item(i).getFirstChild().getNodeValue());
        }


        expr = xpath.compile("/root/Problem/Variables/*");

        nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        Variable[] vars_l = new Variable[nl.getLength()];
        for (int i = 0; i < nl.getLength(); i++) {
            Variable v = new Variable(nl.item(i).getNodeName(), nl.item(i).getFirstChild().getNodeValue().toString());
            vars_l[i] = v;
        }

        return new Problem(p_name, p_description, mVars, m_Time, vars_l);
    }
}
