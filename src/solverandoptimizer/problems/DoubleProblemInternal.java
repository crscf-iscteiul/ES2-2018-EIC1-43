package solverandoptimizer.problems;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import solverandoptimizer.SolverandOptimizer;
import utils.Variable;

import java.util.ArrayList;
import java.util.List;

public class DoubleProblemInternal extends AbstractDoubleProblem {

    public DoubleProblemInternal(List<Variable> variables) {
        setNumberOfVariables(variables.size());
        setNumberOfObjectives(2);
        setName(SolverandOptimizer.getInstance().getProblem().getName()+" - Double Variables Internal");

        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

        for (Variable v: variables) {
            //TODO check if the limits even exist
            Double[] limits = v.parseValues(true);
            lowerLimit.add(limits[0]);
            upperLimit.add(limits[1]);
        }

        //TODO Find how to exclude values

        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
    }

    @Override
    public void evaluate(DoubleSolution solution){

        double[] fx = new double[getNumberOfObjectives()];
        double[] x = new double[getNumberOfVariables()];
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            x[i] = solution.getVariableValue(i) ;
        }

        fx[0] = 0.0;
        for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
            fx[0] += Math.abs(x[0]); // Example for testing
        }

        fx[1] = 0.0;
        for (int var = 0; var < solution.getNumberOfVariables(); var++) {
            fx[1] += Math.abs(x[1]); // Example for testing
        }

        solution.setObjective(0, fx[0]);
        solution.setObjective(1, fx[1]);
    }
}
