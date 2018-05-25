package solverandoptimizer.solver;

import solverandoptimizer.SolverandOptimizer;
import solverandoptimizer.experiment.Binary.BinaryExperimentExternal;
import solverandoptimizer.experiment.Binary.BinaryExperimentInternal;
import solverandoptimizer.experiment.Double.DoubleExperimentExternal;
import solverandoptimizer.experiment.Double.DoubleExperimentInternal;
import solverandoptimizer.experiment.Integer.IntegerExperimentExternal;
import solverandoptimizer.experiment.Integer.IntegerExperimentInternal;
import utils.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                case(0):
                    if(!not_optimized_variables.isEmpty())
                        new IntegerExperimentInternal(not_optimized_variables);
                    if(!optimized_variables.isEmpty())
                        new IntegerExperimentExternal(optimized_variables);
                    break;
                case(1):
                    if(!not_optimized_variables.isEmpty())
                        new DoubleExperimentInternal(not_optimized_variables);
                    if(!optimized_variables.isEmpty())
                        new DoubleExperimentExternal(optimized_variables);
                    break;
                case(2):
                    if(!not_optimized_variables.isEmpty())
                        new BinaryExperimentInternal(not_optimized_variables);
                    if(!optimized_variables.isEmpty())
                        new BinaryExperimentExternal(optimized_variables);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
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
