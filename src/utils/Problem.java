package utils;

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

    public void setmaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public int getNumVars() {
        return numVars;
    }

    public Variable[] getVariables() {
        return variables;
    }

    public boolean addNewVariable(Variable var) {
        if(variables.length + 1 <= numVars) {
            Variable[] newVars = new Variable[variables.length + 1];
            for (int i = 0; i < variables.length; i++)
                newVars[i] = variables[i];
            newVars[variables.length + 1] = var;
            variables = newVars;
            return true;
        }
        return false;
    }

    public void setVariables(Variable[] variables) {
        this.numVars = variables.length;
        this.variables = variables;
    }

    public String toString(){
        return this.getProblemName()+" Variable number: "+this.numVars;
    }
}
