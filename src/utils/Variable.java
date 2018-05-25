package utils;

public class Variable {

    public static final int Type_Integer = 0;
    public static final int Type_Double = 1;
    public static final int Type_Binary = 2;
    public static final int Type_Not_Defined = 3;

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



    private String name;
    private int type;

    private String interval;
    private String exclusions;

    private boolean optimized;

    public Variable(String name, int type, String interval, String exclusions, boolean optimized) {
        this.name = name;
        this.type=type;
        this.interval=interval;
        this.exclusions=exclusions;
        this.optimized=optimized;
    }

    public Variable(String name, String type, String interval, String exclusions, boolean optimized) {
        this.name = name;
        this.type = StringToType(type);
        this.interval=interval;
        this.exclusions=exclusions;
        this.optimized=optimized;
    }

    public String getVariableName() {
        return name;
    }

    public int getType(){
        return type;
    }

    public String getType_toString(){
        return TypeToString(this.type);
    }

    public String getInterval() { return this.interval; }

    public String getExclusions() { return this.exclusions; }

    public Double[] parseValues(boolean isInterval) {
        String work_string;
        if(isInterval)
            work_string = interval;
        else
            work_string = exclusions;
        if(work_string.length() == 0)
            return null;

        String[] values = work_string.replace("\\s+", "").split(";");
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

    public boolean isOptimized(){
        return optimized;
    }

    @Override
    public String toString() {
        return "{Name: "+name+", Type :"+getType_toString()+"}";
    }
}
