package utils;

public class Variable {
    String variableName;
    Integer integerVar;
    Double doubleVar;
    Boolean booleanVar;

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
}
