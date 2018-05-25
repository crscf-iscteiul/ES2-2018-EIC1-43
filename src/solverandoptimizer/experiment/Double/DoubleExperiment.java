package solverandoptimizer.experiment.Double;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3Builder;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEABuilder;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import solverandoptimizer.solver.Solver;

import java.util.ArrayList;
import java.util.List;

public class DoubleExperiment {

    public static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(List<ExperimentProblem<DoubleSolution>> problemList, int maxEvaluations) {
        List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();
        List<String> al = Solver.getInstance().getAlgorithms();

        for (int i = 0; i < problemList.size(); i++) {
            if (al.contains("NSGA-II")) {
                Algorithm<List<DoubleSolution>> algorithm1 = new NSGAIIBuilder<>(problemList.get(i).getProblem(), new SBXCrossover(1.0, 5), new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0)).setMaxEvaluations(maxEvaluations).setPopulationSize(100).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm1, "NSGAII", problemList.get(i).getTag()));
            }
            if (al.contains("GDE3")) {
                Algorithm<List<DoubleSolution>> algorithm3 = new GDE3Builder((DoubleProblem) problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm3, "GDE3", problemList.get(i).getTag()));
            }
            if (al.contains("IBEA")) {
                Algorithm<List<DoubleSolution>> algorithm4 = new IBEABuilder(problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm4, "IBEA", problemList.get(i).getTag()));
            }
            if (al.contains("SMSEMOA")) {
                Algorithm<List<DoubleSolution>> algorithm2 = new SMSEMOABuilder<>(problemList.get(i).getProblem(), new SBXCrossover(1.0, 5), new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0)).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm2, "SMSEMOA", problemList.get(i).getTag()));
            }
            if (al.contains("MOCell")) {
                Algorithm<List<DoubleSolution>> algorithm5 = new MOCellBuilder<>(problemList.get(i).getProblem(),new SBXCrossover(1.0, 5), new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0)).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm5, "MOCell", problemList.get(i).getTag()));
            }
            if (al.contains("MOEAD")) {
                Algorithm<List<DoubleSolution>> algorithm6 = new MOEADBuilder(problemList.get(i).getProblem(),MOEADBuilder.Variant.MOEAD).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm6, "MOEAD", problemList.get(i).getTag()));
            }
            if (al.contains("PAES")) {
                Algorithm<List<DoubleSolution>> algorithm7 = new PAESBuilder<>(problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).setArchiveSize(100).setBiSections(2).setMutationOperator(new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0)).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm7, "PAES", problemList.get(i).getTag()));
            }
            if (al.contains("RandomSearch")) {
                Algorithm<List<DoubleSolution>> algorithm8 = new RandomSearchBuilder<>(problemList.get(i).getProblem()).setMaxEvaluations(maxEvaluations).build();
                algorithms.add(new ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>(algorithm8, "RandomSearch", problemList.get(i).getTag()));
            }
        }
        return algorithms;
    }
}
