package solverandoptimizer.experiment;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import java.util.ArrayList;
import java.util.List;

public class IntegerExperiment {

    public static List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> configureAlgorithmList(List<ExperimentProblem<IntegerSolution>> problemList, int maxEvaluations) {
        List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithms = new ArrayList<>();

        for (int i = 0; i < problemList.size(); i++) {
            /* Esta simulações está a executar todos os algoritmos adequados à
            resolução de problemas deste tipo (Integer)
            Na aplicação final, os algoritmos a ser executados devem ser os
            indicados pelo utilizador na GUI da aplicação */

            Algorithm<List<IntegerSolution>> algorithm1 = new NSGAIIBuilder<>(problemList.get(i).getProblem(), new IntegerSBXCrossover(0.9, 20.0), new IntegerPolynomialMutation(1/problemList.get(i).getProblem().getNumberOfVariables(), 20.0)).setMaxEvaluations(maxEvaluations).setPopulationSize(100).build();
            algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm1, "NSGAII", problemList.get(i).getTag()));

            Algorithm<List<IntegerSolution>> algorithm2 = new SMSEMOABuilder<>(problemList.get(i).getProblem(), new IntegerSBXCrossover(0.9, 20.0),new IntegerPolynomialMutation(1/problemList.get(i).getProblem().getNumberOfVariables(), 20.0)).setMaxEvaluations(maxEvaluations).build();
            algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm2, "SMSEMOA", problemList.get(i).getTag()));

            Algorithm<List<IntegerSolution>> algorithm3 = new MOCellBuilder<>(problemList.get(i).getProblem(),new IntegerSBXCrossover(0.9, 20.0), new IntegerPolynomialMutation(1/problemList.get(i).getProblem().getNumberOfVariables(), 20.0)).setMaxEvaluations(maxEvaluations).build();
            algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm3, "MOCell", problemList.get(i).getTag()));

            Algorithm<List<IntegerSolution>> algorithm4 = new PAESBuilder<>(problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).setArchiveSize(100).setBiSections(2).setMutationOperator(new IntegerPolynomialMutation(1/problemList.get(i).getProblem().getNumberOfVariables(), 20.0)).build();
            algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm4, "PAES", problemList.get(i).getTag()));

            Algorithm<List<IntegerSolution>> algorithm5 = new RandomSearchBuilder<>(problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).build();
            algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm5, "RandomSearch", problemList.get(i).getTag()));
        }
        return algorithms;
    }
}
