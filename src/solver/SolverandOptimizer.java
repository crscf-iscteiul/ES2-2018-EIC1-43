package solver;

import gui.GUI;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import utils.Mail;
import utils.Problem;
import utils.UserSession;
import utils.XML;

import java.io.File;
import java.io.FileNotFoundException;
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
        return XML.load_config();
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