package solverandoptimizer.problems;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import utils.Variable;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BinaryProblemInternal extends AbstractBinaryProblem {

    private ArrayList<Integer> bits = new ArrayList<>();

    public BinaryProblemInternal(List<Variable> variables){
        setNumberOfVariables(variables.size());
        setNumberOfObjectives(2);
        setName("Binary_Variables_Internal");

        for(Variable v: variables)
            bits.add((int)(double)v.parseValues(true)[0]);
    }

    @Override
    protected int getBitsPerVariable(int index) {
        return bits.get(index) ;
    }

    @Override
    public void evaluate(BinarySolution solution) {
        int counterOnes;
        int counterZeroes;
        counterOnes = 0;
        counterZeroes = 0;

        BitSet bitset = solution.getVariableValue(0) ;
        for (int i = 0; i < bitset.length(); i++) {
            if (bitset.get(i)) {
                counterOnes++;
            } else {
                counterZeroes++;
            }
        }
        solution.setObjective(0, -1.0 * counterOnes);
        solution.setObjective(1, -1.0 * counterZeroes);
    }
}
