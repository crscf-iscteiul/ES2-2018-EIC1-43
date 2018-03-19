package utils;

public class Variable {

    private String variableName;

    private Integer integerVar;
    private Double doubleVar;
    private Boolean booleanVar;

    private boolean optimized = false;
    private String jarPath;

    private String interval;
    private String exclusions;

    public Variable(String name, Object i) {
        this.variableName = name;
        if (i.getClass().equals(Integer.class))
            integerVar = (int)i;
        if(i.getClass().equals(Double.class))
            doubleVar = (double) i;
        if(i.getClass().equals(Boolean.class))
            booleanVar = (Boolean) i;
    }

    public String getVariableName() {
        return variableName;
    }

    public Object getVariable() {
        if(integerVar != null)
            return integerVar;
        if(doubleVar != null)
            return doubleVar;
        if(booleanVar != null)
            return booleanVar;
        return null;
    }

    public String getType(){
        if(integerVar != null)
            return "Integer";
        if(doubleVar != null)
            return "Double";
        if(booleanVar != null)
            return "Boolean";
        return "";
    }

    public boolean isOptimized(){
        return optimized;
    }
}
