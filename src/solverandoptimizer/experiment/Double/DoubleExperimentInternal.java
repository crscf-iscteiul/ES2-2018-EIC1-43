package solverandoptimizer.experiment.Double;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import solverandoptimizer.problems.DoubleProblemInternal;
import utils.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoubleExperimentInternal {

    private static final int INDEPENDENT_RUNS = 5;
    private static final int maxEvaluations = 500;

    public DoubleExperimentInternal(List<Variable> variables) throws IOException {
        String experimentBaseDirectory = "experimentBaseDirectory";

        List<ExperimentProblem<DoubleSolution>> problemList = new ArrayList<>();
        problemList.add(new ExperimentProblem<>(new DoubleProblemInternal(variables)));

        List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithmList = DoubleExperiment.configureAlgorithmList(problemList, maxEvaluations);

        Experiment<DoubleSolution, List<DoubleSolution>> experiment = new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("DoubleExperimentInternal")
                        .setAlgorithmList(algorithmList)
                        .setProblemList(problemList)
                        .setExperimentBaseDirectory(experimentBaseDirectory)
                        .setOutputParetoFrontFileName("FUN")
                        .setOutputParetoSetFileName("VAR")
                        .setReferenceFrontDirectory(experimentBaseDirectory + "/referenceFronts")
                        .setIndicatorList(Arrays.<GenericIndicator<DoubleSolution>>asList(new PISAHypervolume<DoubleSolution>()))
                        .setIndependentRuns(INDEPENDENT_RUNS)
                        .setNumberOfCores(8)
                        .build();

        new ExecuteAlgorithms<>(experiment).run();
        new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
        new ComputeQualityIndicators<>(experiment).run();
        new GenerateLatexTablesWithStatistics(experiment).run();
        new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run();
    }
}
