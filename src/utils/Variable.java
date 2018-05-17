package utils;

public class Variable {

    private static final int Integer = 0;
    private static final int Double = 1;
    private static final int Binary = 2;
    private static final int Not_Defined = 2;

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
        if(type.equals("Integer"))
            this.type = Variable.Integer;
        else if (type.equals("Double"))
            this.type = Variable.Double;
        else if (type.equals("Binary"))
            this.type = Variable.Binary;
        else
            this.type = Variable.Not_Defined;
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
        if(type==Variable.Integer)
            return "Integer";
        else if (type==Variable.Double)
            return "Double";
        else if (type==Variable.Binary)
            return "Binary";
        else
            return "";
    }

    public String getInterval() { return this.interval; }

    public String getExclusions() { return this.exclusions; }

    public String getJarPath() { return this.jar_path; }
    public boolean isOptimized(){
        return optimized;
    }

    @Override
    public String toString() {
        return "{Name: "+name+", Type :"+getType()+"}";
    }
}
