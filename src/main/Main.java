package main;

import org.w3c.dom.*;
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
import java.io.File;



public class Main{

    public static void main(String[] args) {
        //SolverandOptimizer.getInstance();


        Variable[] vars = {new Variable("a", 4), new Variable("p", 0.0), new Variable("v", false), new Variable("c", false)};
        Problem p = new Problem("Test Problem", "Test problem relativo a criacao de xml", vars);



    }

    public void saveProblemXML(String filename, Problem p) {
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
            Attr attr = doc.createAttribute("name");
            attr.setValue(p.getProblemName());
            staff.setAttributeNode(attr);


            // description elements
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode(p.getProblemDescription()));
            staff.appendChild(description);

            // variable elements
            for(int i = 0; i < p.getVariables().length; i++) {
                Element variable = doc.createElement(p.getVariables()[i].getVariableName().replace(" ", "_"));
                variable.appendChild(doc.createTextNode(p.getVariables()[i].getVariable().toString()));
                staff.appendChild(variable);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + filename));

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
}