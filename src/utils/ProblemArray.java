package utils;

import java.util.ArrayList;

public class ProblemArray {
    private ArrayList<Integer> integerArrayList;
    private ArrayList<Double> doubleArrayList;
    private ArrayList<Long> longArrayList;
    private ArrayList<Float> floatArrayList;
    private ArrayList<Boolean> booleanArrayList;

    private int type;

    public ProblemArray(String type) {
        if(type.equals("Integer")) {
            this.type = 0;
            this.integerArrayList = new ArrayList<>();
        }
        if(type.equals("Double")) {
            this.type = 1;
            this.doubleArrayList = new ArrayList<>();
        }
        if(type.equals("Long")) {
            this.type = 2;
            this.longArrayList = new ArrayList<>();
        }
        if(type.equals("Float")) {
            this.type = 3;
            this.floatArrayList = new ArrayList<>();
        }
        if(type.equals("Binary")) {
            this.type = 4;
            this.booleanArrayList = new ArrayList<>();
        }
    }

    public ArrayList<?> getArray() {
        switch(this.type) {
            case(0):
                return integerArrayList;
            case(1):
                return doubleArrayList;
            case(2):
                return longArrayList;
            case(3):
                return floatArrayList;
            case(4):
                return booleanArrayList;
        }
        return null;
    }

    public void addElements(ArrayList<?> elementList) {
        switch(this.type) {
            case(0):
                for(Object a : elementList) {
                    this.integerArrayList.add((int) a);
                }
                break;
            case(1):
                for(Object a : elementList) {
                    this.doubleArrayList.add((double)a);
                }
                break;
            case(2):
                for(Object a : elementList)
                    this.longArrayList.add((Long)a);
                break;
            case(3):
                for(Object a : elementList)
                    this.floatArrayList.add((Float) a);
                break;
            case(4):
                for(Object a : elementList)
                    this.booleanArrayList.add((boolean) a);
        }
    }

    public void clearArray() {
        switch(this.type) {
            case(0):
                this.integerArrayList.clear();
                break;
            case(1):
                this.doubleArrayList.clear();
                break;
            case(2):
                this.longArrayList.clear();
                break;
            case(3):
                this.floatArrayList.clear();
                break;
            case(4):
                this.booleanArrayList.clear();
                break;
        }
    }
}
