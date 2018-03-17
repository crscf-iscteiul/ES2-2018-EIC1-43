package utils;

import java.util.ArrayList;

public class Problem {
    private String problemName;
    private String problemDescription;
    private int numVars;
    private Variable[] variables;

    public Problem(String problemName, String problemDescription, Variable[] variables ) {
        this.problemName = problemName;
        this.problemDescription = problemDescription;
        this.numVars = variables.length;
        this.variables = variables;
    }

    public String getProblemName() {
        return problemName;
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

    public void setVariables(Variable[] variables) {
        this.numVars = variables.length;
        this.variables = variables;
    }
}
