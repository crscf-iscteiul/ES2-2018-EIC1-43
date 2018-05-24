package solverandoptimizer.experiment;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import solverandoptimizer.problems.IntegerProblemExternal;
import utils.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerExperimentExternal {

    private static final int INDEPENDENT_RUNS = 2;
    private static final int maxEvaluations = 250;

    public IntegerExperimentExternal(Variable[] variables) throws IOException {

        String experimentBaseDirectory = "experimentBaseDirectory";

        List<ExperimentProblem<IntegerSolution>> problemList = new ArrayList<>();
        problemList.add(new ExperimentProblem<>(new IntegerProblemExternal(variables)));

        List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithmList =
                IntegerExperiment.configureAlgorithmList(problemList, maxEvaluations);

        Experiment<IntegerSolution, List<IntegerSolution>> experiment = new ExperimentBuilder<IntegerSolution, List<IntegerSolution>>("IntegerExperimentExternal")
                .setAlgorithmList(algorithmList)
                .setProblemList(problemList)
                .setExperimentBaseDirectory(experimentBaseDirectory)
                .setOutputParetoFrontFileName("FUN")
                .setOutputParetoSetFileName("VAR")
                .setReferenceFrontDirectory(experimentBaseDirectory + "/referenceFronts")
                .setIndicatorList(Arrays.<GenericIndicator<IntegerSolution>>asList(new PISAHypervolume<IntegerSolution>()))
                .setIndependentRuns(INDEPENDENT_RUNS)
                .setNumberOfCores(8)
                .build();

        new ExecuteAlgorithms<>(experiment).run();
        new GenerateReferenceParetoFront(experiment).run();
        new ComputeQualityIndicators<>(experiment).run();
        new GenerateLatexTablesWithStatistics(experiment).run();
        new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run();
    }


}
