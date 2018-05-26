package solverandoptimizer.experiment.Binary;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import solverandoptimizer.problems.BinaryProblemExternal;
import utils.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryExperimentExternal {

    private static final int INDEPENDENT_RUNS = 2;
    private static final int maxEvaluations = 250;

    public BinaryExperimentExternal(List<Variable> variables) throws IOException {
        String experimentBaseDirectory = "experimentBaseDirectory";

        List<ExperimentProblem<BinarySolution>> problemList = new ArrayList<>();
        problemList.add(new ExperimentProblem<>(new BinaryProblemExternal(variables)));

        List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> algorithmList = BinaryExperiment.configureAlgorithmList(problemList, maxEvaluations);

        Experiment<BinarySolution, List<BinarySolution>> experiment = new ExperimentBuilder<BinarySolution, List<BinarySolution>>("BinaryExperimentExternal")
                .setAlgorithmList(algorithmList)
                .setProblemList(problemList)
                .setExperimentBaseDirectory(experimentBaseDirectory)
                .setOutputParetoFrontFileName("FUN")
                .setOutputParetoSetFileName("VAR")
                .setReferenceFrontDirectory(experimentBaseDirectory + "/referenceFronts")
                .setIndicatorList(Arrays.<GenericIndicator<BinarySolution>>asList(new PISAHypervolume<BinarySolution>()))
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
