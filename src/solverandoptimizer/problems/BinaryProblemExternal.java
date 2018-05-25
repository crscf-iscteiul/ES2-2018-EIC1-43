package solverandoptimizer.problems;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.JMetalException;
import solverandoptimizer.SolverandOptimizer;
import solverandoptimizer.solver.Solver;
import utils.Variable;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BinaryProblemExternal extends AbstractBinaryProblem {

    private ArrayList<Integer> bits;

    public BinaryProblemExternal(List<Variable> variables){
        setNumberOfVariables(variables.size());
        setNumberOfObjectives(2);
        setName(SolverandOptimizer.getInstance().getProblem().getName()+" - Binary Variables External");

        for(Variable v: variables)
            bits.add((int)(double)v.parseValues(true)[0]);
    }

    @Override
    protected int getBitsPerVariable(int index) {
        if (index != 0) {
            throw new JMetalException("Problem MyBinaryProblem has only a variable. Index = " + index) ;
        }
        return bits.get(index) ;
    }

    @Override
    public void evaluate(BinarySolution solution) {
        String solutionString ="";
        BitSet bitset = solution.getVariableValue(0) ;
        solutionString = bitset.toString();
        String evaluationResultString = Solver.getInstance().runExternalJar(solutionString);
        String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
        // It is assumed that all evaluated criteria are returned in the same result string
        for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
            solution.setObjective(i, Double.parseDouble(individualEvaluationCriteria[i]));
        }
    }
}
