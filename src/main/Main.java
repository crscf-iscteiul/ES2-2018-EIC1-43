package main;

import org.w3c.dom.*;
import solver.SolverandOptimizer;
import utils.Problem;
import utils.Variable;
import utils.XML;

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
        //SolverandOptimizer.getInstance();
        try {
            XML.saveXMLProblem(System.getProperty("user.dir")+"/Problem.xml", XML.p);
            Problem p = XML.readXMLProblem(System.getProperty("user.dir")+"/Problem.xml");
            System.out.println(p.getProblemName());
            for(int i = 0; i < p.getVariables().length; i++) {
                System.out.println(p.getVariables()[i].getVariableName() + " - " + p.getVariables()[i].getType() + " - " + p.getVariables()[i].getVariable());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}