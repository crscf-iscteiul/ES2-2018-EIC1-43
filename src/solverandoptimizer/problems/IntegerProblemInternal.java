package solverandoptimizer.problems;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import utils.Variable;

import java.util.ArrayList;
import java.util.List;

public class IntegerProblemInternal extends AbstractIntegerProblem {

    public IntegerProblemInternal(List<Variable> variables){
        setNumberOfVariables(variables.size());
        setNumberOfObjectives(2);
        setName("Integer_Variables_Internal");

        List<Integer> lowerLimit = new ArrayList<>(variables.size());
        List<Integer> upperLimit = new ArrayList<>(variables.size());

        for (Variable v: variables) {
            //TODO check if the limits even exist
            Double[] limits = v.parseValues(true);

            lowerLimit.add((int)(double)limits[0]);
            upperLimit.add((int)(double)limits[1]);
        }

        //TODO Find how to exclude values

        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
    }

    @Override
    public void evaluate(IntegerSolution solution) {
        double[] fx = new double[getNumberOfObjectives()];
        int[] x = new int[getNumberOfVariables()];
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            x[i] = solution.getVariableValue(i) ;
        }

        fx[0] = 0;
        for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
            fx[0] += Math.abs(x[0]+Math.random()*10); // Example for testing
        }

        fx[1] = 0;
        for (int var = 0; var < solution.getNumberOfVariables(); var++) {
            fx[1] += Math.abs(x[1]+Math.random()*10); // Example for testing
        }
        solution.setObjective(0, fx[0]);
        solution.setObjective(1, fx[1]);
    }
}
