package utils;

import java.io.File;
import java.util.ArrayList;

public class Problem {
    private String problemName;
    private String problemDescription;
    private int numVars;
    private String maxTime;
    private Variable[] variables;

    public Problem(String problemName, String problemDescription, int maxVars, String maxTime, Variable[] variables ) {
        this.problemName = problemName;
        this.problemDescription = problemDescription;
        this.numVars =maxVars;
        this.maxTime = maxTime;
        this.variables = variables;
    }

    public String getProblemName() {
        return problemName;
    }

    public String getMaxTime() { return this.maxTime; }

    public String getProblemDescription() {
        return problemDescription;
    }

    public int getNumVars() {
        return numVars;
    }

    public Variable[] getVariables() {
        return variables;
    }

    public void updateVaribles(Variable [] varibles){
        this.variables = varibles;
        this.numVars = varibles.length;
    }

    public boolean isProblemValid(){
        for(Variable v : variables){
            System.out.println(v);
            if(v.getVariableName().equals(""))
                return false;
            if(v.getType()==Variable.Type_Not_Defined)
                return false;
            if(v.isOptimized() && v.getJarPath().equals(""))
                return false;
            if(v.isOptimized() && !new File(v.getJarPath()).isFile())
                return false;
            //TODO add parse test interval and exclusions
        }
        return true;
    }

    public String toString(){
        return this.getProblemName()+" Variable number: "+this.numVars;
    }
}
