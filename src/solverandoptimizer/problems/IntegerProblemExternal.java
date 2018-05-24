package solverandoptimizer.problems;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import solverandoptimizer.SolverandOptimizer;
import utils.Variable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IntegerProblemExternal extends AbstractIntegerProblem {

    public IntegerProblemExternal(Variable[] variables) {
        setNumberOfVariables(variables.length);
        setNumberOfObjectives(2);
        setName(SolverandOptimizer.getInstance().getProblem().getName() + " - Integer Variables External");

        List<Integer> lowerLimit = new ArrayList<>(variables.length);
        List<Integer> upperLimit = new ArrayList<>(variables.length);

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

    public void evaluate(IntegerSolution solution) {
        String solutionString = "";
        String evaluationResultString = "";
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            solutionString = solutionString + " " + solution.getVariableValue(i);
        }
        try {
            String line;
            Process p = Runtime.getRuntime().exec("java -jar \""+SolverandOptimizer.getInstance().getProblem().getJarPath()+ "\" " + solutionString);
            BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = brinput.readLine()) != null) {
                evaluationResultString += line;
            }
            brinput.close();
            p.waitFor();

        } catch (Exception err) {
            err.printStackTrace();
        }

        String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
        // It is assumed that all evaluated criteria are returned in the same result string
        for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
            solution.setObjective(i, Integer.parseInt(individualEvaluationCriteria[i]));
        }
    }
}