package solver;

import gui.GUI;
import main.Main;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.Mail;
import utils.Problem;
import utils.UserSession;
import utils.XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


public class SolverandOptimizer {

    private static SolverandOptimizer instance;
    public static SolverandOptimizer getInstance() {
        if (instance == null)
            instance = new SolverandOptimizer();
        return instance;
    }

    private UserSession user;
    private Problem problem;

    private SolverandOptimizer(){
        new Thread(){
            @Override
            public void run() {
                Mail.getInstance();
            }
        }.run();
        GUI.getInstance();
    }

    public void setUser(UserSession user){
        this.user = user;
    }

    public UserSession getUser() {
        return user;
    }

    public Problem getProblem() { return this.problem; }

    public HashMap<String, String> load_config(){
        HashMap<String, String> adminInfo = new HashMap();
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
        return adminInfo;
    }

    public void changeProblem(Problem problem){
        this.problem = problem;
    }

    public boolean saveProblem(String file_path) {
        try {
            XML.saveXMLProblem(file_path, problem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loadProblem(String file_path) {
        try {
            problem = XML.readXMLProblem(file_path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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