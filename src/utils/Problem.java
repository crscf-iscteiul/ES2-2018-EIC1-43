package utils;

import java.io.File;

public class Problem {
    private String name;
    private String description;
    private int num_vars;
    private String max_time;
    private Variable[] variables;

    public Problem(String name, String description, int num_vars, String max_time, Variable[] variables ) {
        this.name = name;
        this.description = description;
        this.num_vars = num_vars;
        this.max_time = max_time;
        this.variables = variables;
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

    public void updateVaribles(Variable [] varibles){
        this.variables = varibles;
        this.num_vars = varibles.length;
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
        return this.getName()+" Variable number: "+this.num_vars;
    }
}
