package utils;

// TODO: Auto-generated Javadoc
/**
 * The Class Variable.
 */
public class Variable {

    /** The Constant Type_Integer. */
    public static final int Type_Integer = 0;
    
    /** The Constant Type_Double. */
    public static final int Type_Double = 1;
    
    /** The Constant Type_Binary. */
    public static final int Type_Binary = 2;
    
    /** The Constant Type_Not_Defined. */
    public static final int Type_Not_Defined = 3;

    /**
     * Type to string.
     *
     * @param t the t
     * @return the string
     */
    public static String TypeToString(int t){
        if(t==Variable.Type_Integer)
            return "Integer";
        else if (t==Variable.Type_Double)
            return "Double";
        else if (t==Variable.Type_Binary)
            return "Binary";
        else
            return "";
    }

    /**
     * String to type.
     *
     * @param t the t
     * @return the int
     */
    public static int StringToType(String t){
        if(t.equals("Integer"))
            return Variable.Type_Integer;
        else if (t.equals("Double"))
            return Variable.Type_Double;
        else if (t.equals("Binary"))
            return Variable.Type_Binary;
        else
            return Variable.Type_Not_Defined;
    }

    /** The name. */
    private String name;
    
    /** The type. */
    private int type;

    /** The interval. */
    private String interval;
    
    /** The exclusions. */
    private String exclusions;

    /** The optimized. */
    private boolean optimized;

    /**
     * Instantiates a new variable.
     *
     * @param name the name
     * @param type the type
     * @param interval the interval
     * @param exclusions the exclusions
     * @param optimized the optimized
     */
    public Variable(String name, int type, String interval, String exclusions, boolean optimized) {
        this.name = name;
        this.type=type;
        this.interval=interval;
        this.exclusions=exclusions;
        this.optimized=optimized;
    }

    /**
     * Instantiates a new variable.
     *
     * @param name the name
     * @param type the type
     * @param interval the interval
     * @param exclusions the exclusions
     * @param optimized the optimized
     */
    public Variable(String name, String type, String interval, String exclusions, boolean optimized) {
        this.name = name;
        this.type = StringToType(type);
        this.interval=interval;
        this.exclusions=exclusions;
        this.optimized=optimized;
    }

    /**
     * Gets the variable name.
     *
     * @return the variable name
     */
    public String getVariableName() {
        return name;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public int getType(){
        return type;
    }

    /**
     * Gets the type to string.
     *
     * @return the type to string
     */
    public String getType_toString(){
        return TypeToString(this.type);
    }

    /**
     * Gets the interval.
     *
     * @return the interval
     */
    public String getInterval() { return this.interval; }

    /**
     * Gets the exclusions.
     *
     * @return the exclusions
     */
    public String getExclusions() { return this.exclusions; }

    /**
     * Parses the values.
     *
     * @param isInterval the is interval
     * @return the double[]
     */
    public Double[] parseValues(boolean isInterval) {
        String work_string;
        if(isInterval)
            work_string = interval;
        else
            work_string = exclusions;
        if(work_string.length() == 0)
            return null;

        String[] values = work_string.replace(" ", "").split(";");
        Double[] array = new Double[values.length];
        if(type == 0 || type == 2) {
            for(int i = 0; i < values.length; i++)
                array[i] = (double)Integer.parseInt(values[i]);
        }
        if(type == 1) {
            for(int i = 0; i < values.length; i++)
                array[i] = Double.parseDouble(values[i]);
        }
        if(type == 3)
            return null;
        return array;
    }

    /**
     * Checks if is optimized.
     *
     * @return true, if is optimized
     */
    public boolean isOptimized(){
        return optimized;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{Name: "+name+", Type :"+getType_toString()+"}";
    }
}
