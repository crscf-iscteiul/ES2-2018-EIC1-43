package solverandoptimizer.problems;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import solverandoptimizer.solver.Solver;
import utils.Variable;

import java.util.ArrayList;
import java.util.List;

public class DoubleProblemExternal extends AbstractDoubleProblem {

    public DoubleProblemExternal(List<Variable> variables) {
        setNumberOfVariables(variables.size());
        setNumberOfObjectives(2);
        setName("Double_Variables_External");

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
        String solutionString ="";
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            solutionString = solutionString + " " + solution.getVariableValue(i);
        }
        String evaluationResultString = Solver.getInstance().runExternalJar(solutionString);
        String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
        // It is assumed that all evaluated criteria are returned in the same result string
        for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
            solution.setObjective(i, Double.parseDouble(individualEvaluationCriteria[i]));
        }
    }
}
