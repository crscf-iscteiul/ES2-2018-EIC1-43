package solverandoptimizer.problems;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import solverandoptimizer.solver.Solver;
import utils.Variable;

import java.util.ArrayList;
import java.util.List;

public class IntegerProblemExternal extends AbstractIntegerProblem {

    public IntegerProblemExternal(List<Variable> variables) {
        setNumberOfVariables(variables.size());
        setNumberOfObjectives(2);
        setName("Integer_Variables_External");

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
        String solutionString = "";
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            solutionString = solutionString + " " + solution.getVariableValue(i);
        }
        String evaluationResultString = Solver.getInstance().runExternalJar(solutionString);
        String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
        // It is assumed that all evaluated criteria are returned in the same result string
        for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
            solution.setObjective(i, Integer.parseInt(individualEvaluationCriteria[i]));
        }
    }
}