package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;

public class XML {

        public static Variable[] vars = {new Variable("a", 4), new Variable("p", 0.0), new Variable("v", false), new Variable("c", false)};
        public static Problem p = new Problem("Test Problem", "Test problem relativo a criacao de xml",4,"00:05:00"  ,vars);


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
            try {
                Element variable = doc.createElement("Variable");
                variable.setAttribute("id", String.valueOf(i+1));
                Element variable_name = doc.createElement("Name");
                Element variable_type = doc.createElement("Type");
                Element variable_value = doc.createElement("Value");

                variable_name.appendChild(doc.createTextNode(p.getVariables()[i].getVariableName().replace(" ", "_")));
                variable_type.appendChild(doc.createTextNode(p.getVariables()[i].getType()));
                variable_value.appendChild(doc.createTextNode(p.getVariables()[i].getVariable().toString()));

                variable.appendChild(variable_name);
                variable.appendChild(variable_type);
                variable.appendChild(variable_value);
                variables.appendChild(variable);
            } catch ( NullPointerException e) {
                System.out.println("One or more variables were left blank.");
                System.out.println(p.getVariables().toString());
            }
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


        NodeList variableList = doc.getElementsByTagName("Variable");
        System.out.println("TOTAL VARIABLES: " + variableList.getLength());
        Variable[] vars_l = new Variable[variableList.getLength()];

        for (int i = 0; i < variableList.getLength(); i++) {
            Node firstVariableNode = variableList.item(i);
            if(firstVariableNode.getNodeType() == Node.ELEMENT_NODE) {
                Object var_Val = null;
                Element firstElement = (Element) firstVariableNode;

                NodeList varN = firstElement.getElementsByTagName("Name");
                String var_Name = varN.item(0).getFirstChild().getNodeValue();
                NodeList varT = firstElement.getElementsByTagName("Type");
                String var_Type = varT.item(0).getFirstChild().getNodeValue();
                NodeList varV = firstElement.getElementsByTagName("Value");

                if(var_Type.equals("Integer")) {
                    var_Val = Integer.valueOf(varV.item(0).getFirstChild().getNodeValue());
                } if(var_Type.equals("Boolean")) {
                    var_Val = Boolean.valueOf(varV.item(0).getFirstChild().getNodeValue());
                } if(var_Type.equals("Double")) {
                    var_Val = Double.valueOf(varV.item(0).getFirstChild().getNodeValue());
                }

            vars_l[i] = new Variable(var_Name, var_Val);
            }
        }

        return new Problem(p_name, p_description, mVars, m_Time, vars_l);
    }
}
