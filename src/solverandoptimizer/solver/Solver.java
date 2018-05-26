package solverandoptimizer.solver;

import gui.GUI;
import solverandoptimizer.SolverandOptimizer;
import solverandoptimizer.experiment.Binary.BinaryExperimentExternal;
import solverandoptimizer.experiment.Binary.BinaryExperimentInternal;
import solverandoptimizer.experiment.Double.DoubleExperimentExternal;
import solverandoptimizer.experiment.Double.DoubleExperimentInternal;
import solverandoptimizer.experiment.Integer.IntegerExperimentExternal;
import solverandoptimizer.experiment.Integer.IntegerExperimentInternal;
import utils.CompilerRandTex;
import utils.Variable;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private static Solver instance;
    public static Solver getInstance(){
        if(instance==null)
            instance=new Solver();
        return instance;
    }

    private List<String> algorithms;

    public synchronized void solve(Variable[] variables, List<String> algorithms){

        this.algorithms=algorithms;

        ArrayList<Variable> optimized_variables = new ArrayList<>();
        ArrayList<Variable> not_optimized_variables = new ArrayList<>();

        for(Variable v: variables) {
            if(v.isOptimized()){
                optimized_variables.add(v);
            } else {
                not_optimized_variables.add(v);
            }
        }
        try {
            switch (variables[0].getType()) {
                case (0):
                    if (!not_optimized_variables.isEmpty())
                        new IntegerExperimentInternal(not_optimized_variables);
                    if (!optimized_variables.isEmpty())
                        new IntegerExperimentExternal(optimized_variables);
                    break;
                case (1):
                    if (!not_optimized_variables.isEmpty())
                        new DoubleExperimentInternal(not_optimized_variables);
                    if (!optimized_variables.isEmpty())
                        new DoubleExperimentExternal(optimized_variables);
                    break;
                case (2):
                    if (!not_optimized_variables.isEmpty())
                        new BinaryExperimentInternal(not_optimized_variables);
                    if (!optimized_variables.isEmpty())
                        new BinaryExperimentExternal(optimized_variables);
                    break;
            }
            String type = variables[0].getType_toString();
            if (!not_optimized_variables.isEmpty()) {
                CompilerRandTex.getInstance().compileR(type + "ExperimentInternal");
                CompilerRandTex.getInstance().compileLatex(type + "ExperimentInternal");
                CompilerRandTex.getInstance().compileEPS(type + "ExperimentInternal");
            }
            if (!optimized_variables.isEmpty()) {
                CompilerRandTex.getInstance().compileR(type + "ExperimentExternal");
                CompilerRandTex.getInstance().compileLatex(type + "ExperimentExternal");
                CompilerRandTex.getInstance().compileEPS(type + "ExperimentExternal");
            }
        } catch (FileNotFoundException e){
            GUI.getInstance().end_optimization(false);
        } catch (IOException e) {
            GUI.getInstance().show_fatal_error(e.getMessage());
        }
        if(GUI.getInstance().end_optimization(true)) {
            String type = variables[0].getType_toString();
            if (Desktop.isDesktopSupported()) {
                try {
                    if (!not_optimized_variables.isEmpty()) {
                        File pdf = new File("experimentBaseDirectory\\" + type + "ExperimentInternal" + "\\R\\HV.Boxplot.pdf");
                        Desktop.getDesktop().open(pdf);
                        pdf = new File("experimentBaseDirectory\\" + type + "ExperimentInternal" + "\\latex\\"+ type + "ExperimentInternal.pdf");
                        Desktop.getDesktop().open(pdf);
                    }
                    if (!optimized_variables.isEmpty()) {
                        File pdf = new File("experimentBaseDirectory\\" + type + "ExperimentExternal" + "\\R\\HV.Boxplot.pdf");
                        Desktop.getDesktop().open(pdf);
                        pdf = new File("experimentBaseDirectory\\" + type + "ExperimentExternal" + "\\latex\\"+ type + "ExperimentExternal.pdf");
                        Desktop.getDesktop().open(pdf);
                    }
                } catch (IOException e) {
                    GUI.getInstance().show_error("No application registered for PDFs");
                }
            }
        }
    }

    public String runExternalJar(String solutionString){
        String evaluationResultString = "";
        try {
            String line;
            Process p = Runtime.getRuntime().exec("java -jar \""+SolverandOptimizer.getInstance().getProblem().getJarPath()+ "\" " + solutionString);
            BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = brinput.readLine()) != null) {
                evaluationResultString += line;
            }
            brinput.close();
            p.waitFor();

        } catch (Exception err) {
            err.printStackTrace();
        }
        return evaluationResultString;
    }

    public List<String> getAlgorithms(){
        return algorithms;
    }
}
