package utils;

import solverandoptimizer.SolverandOptimizer;
import solverandoptimizer.solver.Solver;

import java.awt.datatransfer.DataFlavor;
import java.io.File;

public class Problem {
    private String name;
    private String description;
    private int num_vars;
    private String max_time;
    private Variable[] variables;

    private String jar_path;

    public Problem(String name, String description, int num_vars, String max_time, Variable[] variables) {
        this.name = name;
        this.description = description;
        this.num_vars = num_vars;
        this.max_time = max_time;
        this.variables = variables;
        this.jar_path="";
    }

    public String getName() {
        return name;
    }

    public String getMaxTime() { return this.max_time; }

    public String getDescription() {
        return description;
    }

    public int getNumVars() {
        return num_vars;
    }

    public Variable[] getVariables() {
        return variables;
    }

    public void updateVaribles(Variable [] variables){
        this.variables = variables;
        this.num_vars = variables.length;
    }

    public boolean isProblemValid(){
        for(Variable v : variables){
            if(v.getVariableName().equals(""))
                return false;
            if(v.getType()==Variable.Type_Not_Defined)
                return false;
            if(v.isOptimized() && this.jar_path.equals(""))
                return false;
            if(v.isOptimized() && !new File(this.jar_path).isFile())
                return false;
            //TODO add parse test interval and exclusions
        }
        return true;
    }

    public void setJarPath(String jar_path) {
        this.jar_path = jar_path;
    }

    public String getJarPath() {
        return jar_path;
    }

    public String toString(){
        return this.getName()+" Variable number: "+this.num_vars;
    }
}
