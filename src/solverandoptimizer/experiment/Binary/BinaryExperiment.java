package solverandoptimizer.experiment.Binary;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHCBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.HUXCrossover;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.operator.impl.selection.RankingAndCrowdingSelection;
import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import solverandoptimizer.solver.Solver;

import java.util.ArrayList;
import java.util.List;

public class BinaryExperiment {

    public static List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> configureAlgorithmList(List<ExperimentProblem<BinarySolution>> problemList, int maxEvaluations) {
        List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> algorithms = new ArrayList<>();
        List<String> al = Solver.getInstance().getAlgorithms();

        for (int i = 0; i < problemList.size(); i++) {
            if (al.contains("NSGA-II")) {
                Algorithm<List<BinarySolution>> algorithm = new NSGAIIBuilder<>(problemList.get(i).getProblem(), new SinglePointCrossover(1.0), new BitFlipMutation(1.0 / ((AbstractBinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0))).setMaxEvaluations(maxEvaluations).setPopulationSize(100).build();
                algorithms.add(new ExperimentAlgorithm<BinarySolution, List<BinarySolution>>(algorithm, "NSGAII", problemList.get(i).getTag()));
            }
            if (al.contains("SMSEMOA")) {
                Algorithm<List<BinarySolution>> algorithm2 = new SMSEMOABuilder<>(problemList.get(i).getProblem(), new SinglePointCrossover(1.0), new BitFlipMutation(1.0 / ((AbstractBinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0))).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<BinarySolution, List<BinarySolution>>(algorithm2, "SMSEMOA", problemList.get(i).getTag()));
            }
            if (al.contains("MOCell")) {
                Algorithm<List<BinarySolution>> algorithm3 = new MOCellBuilder<>(problemList.get(i).getProblem(), new SinglePointCrossover(1.0), new BitFlipMutation(1.0 / ((AbstractBinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0))).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<BinarySolution, List<BinarySolution>>(algorithm3, "MOCell", problemList.get(i).getTag()));
            }
            if (al.contains("MOCH")) {
                Algorithm<List<BinarySolution>> algorithm4 = new MOCHCBuilder((BinaryProblem) problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).setCrossover(new HUXCrossover(1.0)).setNewGenerationSelection(new RankingAndCrowdingSelection<BinarySolution>(100)).setCataclysmicMutation(new BitFlipMutation(0.35)).setParentSelection(new RandomSelection<BinarySolution>()).setEvaluator(new SequentialSolutionListEvaluator<BinarySolution>()).build();
                algorithms.add(new ExperimentAlgorithm<BinarySolution, List<BinarySolution>>(algorithm4, "MOCH", problemList.get(i).getTag()));
            }
            if (al.contains("PAES")) {
                Algorithm<List<BinarySolution>> algorithm5 = new PAESBuilder<>(problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).setArchiveSize(100).setBiSections(2).setMutationOperator(new BitFlipMutation(1.0 / ((AbstractBinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0))).build();
                algorithms.add(new ExperimentAlgorithm<BinarySolution, List<BinarySolution>>(algorithm5, "PAES", problemList.get(i).getTag()));
            }
            if (al.contains("RandomSearch")) {
                Algorithm<List<BinarySolution>> algorithm6 = new RandomSearchBuilder<>(problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<BinarySolution, List<BinarySolution>>(algorithm6, "RandomSearch", problemList.get(i).getTag()));
            }
            if(al.contains("SPEA2")) {
                Algorithm<List<BinarySolution>> algorithm7 = new SPEA2Builder<>(problemList.get(i).getProblem(), new SinglePointCrossover(1.0), new BitFlipMutation(1.0 / ((AbstractBinaryProblem) problemList.get(i).getProblem()).getNumberOfBits(0))).setMaxIterations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<BinarySolution, List<BinarySolution>>(algorithm7, "SPEA2", problemList.get(i).getTag()));
            }
        }
        return algorithms;




    }
}
