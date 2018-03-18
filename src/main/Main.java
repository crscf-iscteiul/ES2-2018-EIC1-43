package main;

import org.w3c.dom.*;
import solver.SolverandOptimizer;
import utils.Problem;
import utils.Variable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) {
        SolverandOptimizer.getInstance();


//        Variable[] vars = {new Variable("a", 4), new Variable("p", 0.0), new Variable("v", false), new Variable("c", false)};
//        Problem p = new Problem("Test Problem", "Test problem relativo a criacao de xml", vars);
//
//        saveProblemXML("test", p);
//        Problem p_r = readXMLProblem("test");

    }

    public static void saveProblemXML(String filename, Problem p) {
        try {

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
            staff.setAttribute("description", p.getProblemDescription());
            staff.setAttribute("name", p.getProblemName());

            Element variables = doc.createElement("Variables");
            rootElement.appendChild(variables);

            // variable elements
            for (int i = 0; i < p.getVariables().length; i++) {
                Element variable = doc.createElement(p.getVariables()[i].getVariableName().replace(" ", "_"));
                variable.appendChild(doc.createTextNode(p.getVariables()[i].getVariable().toString().replace(" ", "_")));
                variables.appendChild(variable);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "\\" + filename + ".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static Problem readXMLProblem(String filename) {
        try {
            String p_description = "";
            String p_name = "";


            File config = new File(System.getProperty("user.dir") + "\\" + filename + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(config);
            doc.getDocumentElement().normalize();

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            XPathExpression expr = xpath.compile("/root/Problem/@*");

            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getNodeName().equals("description"))
                    p_description = nl.item(i).getNodeValue();
                if (nl.item(i).getNodeName().equals("name"))
                    p_name = nl.item(i).getNodeValue();
            }

            expr = xpath.compile("/root/Variables/*");
            nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            Variable[] vars_l = new Variable[nl.getLength()];
            for (int i = 0; i < nl.getLength(); i++) {
                Variable v = new Variable(nl.item(i).getNodeName(), nl.item(i).getFirstChild().getNodeValue().toString());
                vars_l[i] = v;
            }

            return new Problem(p_name, p_description, vars_l);

        } catch (FileNotFoundException e) {
            System.out.println("Make sure configuration file is available.");
        } catch (Exception e) {
        }
        return null;
    }

}