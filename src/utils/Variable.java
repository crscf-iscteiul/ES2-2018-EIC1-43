package utils;

public class Variable {

    public static final int Type_Integer = 0;
    public static final int Type_Double = 1;
    public static final int Type_Binary = 2;
    public static final int Type_Not_Defined = 3;

    private String name;
    private int type;

    private String interval;
    private String exclusions;

    private boolean optimized;
    private String jar_path;

    public Variable(String name, int type, String interval, String exclusions, boolean optimized, String jar_path) {
        this.name = name;
        this.type=type;
        this.interval=interval;
        this.exclusions=exclusions;
        this.optimized=optimized;
        this.jar_path=jar_path;
    }

    public Variable(String name, String type, String interval, String exclusions, boolean optimized, String jar_path) {
        this.name = name;
        if(type.equals("Type_Integer"))
            this.type = Variable.Type_Integer;
        else if (type.equals("Type_Double"))
            this.type = Variable.Type_Double;
        else if (type.equals("Type_Binary"))
            this.type = Variable.Type_Binary;
        else
            this.type = Variable.Type_Not_Defined;
        this.interval=interval;
        this.exclusions=exclusions;
        this.optimized=optimized;
        this.jar_path=jar_path;
    }

    public String getVariableName() {
        return name;
    }

    public int getType(){
        return type;
    }

    public String getType_toString(){
        if(type==Variable.Type_Integer)
            return "Type_Integer";
        else if (type==Variable.Type_Double)
            return "Type_Double";
        else if (type==Variable.Type_Binary)
            return "Type_Binary";
        else
            return "";
    }

    public String getInterval() { return this.interval; }

    public String getExclusions() { return this.exclusions; }

    public String getJarPath() { return this.jar_path; }

    public Object[] parseValues(boolean isInterval) {
        String work_string;
        if(isInterval)
            work_string = interval;
        else
            work_string = exclusions;
        if(work_string.length() == 0)
            return null;

        String[] values = work_string.replace(" ", "").split(";");;
        Object[] array = new Object[values.length];
        if(type == 0 || type == 2) {
            for(int i = 0; i < values.length; i++)
                array[i] = Integer.parseInt(values[i]);
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
