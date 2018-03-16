package utils;

import java.util.ArrayList;

public class Problem {
    private String problemName;
    private String problemDescription;
    private int numVars;
    private ProblemArray variables;
    private String type;

    public Problem(String problemName, String problemDescription, int numVars, String type, ArrayList<?> variables ) {
        this.problemName = problemName;
        this.type = type;
        this.problemDescription = problemDescription;
        this.numVars = numVars;
        this.variables = new ProblemArray(type);
        this.variables.addElements(variables);
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

    public void setNumVars(int numVars) {
        this.numVars = numVars;
    }

    public String getType() {
        return type;
    }

    public ArrayList<?> getVariables() {
        return variables.getArray();
    }

    public void setVariables(ArrayList<?> variables, String type) {
        if(this.type.equals(type)) {
            this.variables.clearArray();
            this.variables.addElements(variables);
        } else {
            this.variables = new ProblemArray(type);
            this.variables.addElements(variables);
        }
    }
}
