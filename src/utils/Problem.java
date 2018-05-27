package utils;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Class Problem.
 */
public class Problem {
    
    /** The name. */
    private String name;
    
    /** The description. */
    private String description;
    
    /** The num vars. */
    private int num_vars;
    
    /** The max time. */
    private String max_time;
    
    /** The variables. */
    private Variable[] variables;

    /** The jar path. */
    private String jar_path;

    /**
     * Instantiates a new problem.
     *
     * @param name the name
     * @param description the description
     * @param num_vars the num vars
     * @param max_time the max time
     * @param variables the variables
     */
    public Problem(String name, String description, int num_vars, String max_time, Variable[] variables) {
        this.name = name;
        this.description = description;
        this.num_vars = num_vars;
        this.max_time = max_time;
        this.variables = variables;
        this.jar_path="";
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the max time.
     *
     * @return the max time
     */
    public String getMaxTime() { return this.max_time; }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the num vars.
     *
     * @return the num vars
     */
    public int getNumVars() {
        return num_vars;
    }

    /**
     * Gets the variables.
     *
     * @return the variables
     */
    public Variable[] getVariables() {
        return variables;
    }

    /**
     * Update varibles.
     *
     * @param variables the variables
     */
    public void updateVaribles(Variable [] variables){
        this.variables = variables;
        this.num_vars = variables.length;
    }

    /**
     * Checks if is problem valid.
     *
     * @return true, if is problem valid
     */
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
        }
        return true;
    }

    /**
     * Sets the jar path.
     *
     * @param jar_path the new jar path
     */
    public void setJarPath(String jar_path) {
        this.jar_path = jar_path;
    }

    /**
     * Gets the jar path.
     *
     * @return the jar path
     */
    public String getJarPath() {
        return jar_path;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return this.getName()+" Variable number: "+this.num_vars;
    }
}
