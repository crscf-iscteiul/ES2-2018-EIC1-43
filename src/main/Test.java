package main;

import utils.CompilerRandTex;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
//        try {
//            XML.saveXMLProblem(System.getProperty("user.dir")+"/Problem.xml", XML.p);
//            Problem p = XML.readXMLProblem(System.getProperty("user.dir")+"/Problem.xml");
//            System.out.println(p.getName());
//            for(int i = 0; i < p.getVariables().length; i++) {
//                System.out.println(p.getVariables()[i].getVariableName() + " - " + p.getVariables()[i].getType_toString());
//                System.out.println("INCLUDED VALUES: ");
//                for(Object a : p.getVariables()[i].parseValues(true))
//                    System.out.println(a.toString());
//                System.out.println("EXCLUDED VALUES: ");
//                for(Object a : p.getVariables()[i].parseValues(false))
//                    System.out.println(a.toString());
//                System.out.println("IS OPTIMIZED: \n" + p.getVariables()[i].isOptimized());
//                System.out.println("-------------------------");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            CompilerRandTex.getInstance().compileFile("IntegerExperimentInternal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
