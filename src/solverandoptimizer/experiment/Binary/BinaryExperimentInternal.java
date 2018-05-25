package solverandoptimizer.experiment.Binary;

import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import solverandoptimizer.problems.BinaryProblemInternal;
import utils.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryExperimentInternal {

    private static final int INDEPENDENT_RUNS = 5;
    private static final int maxEvaluations = 500;

    public BinaryExperimentInternal(List<Variable> variables) throws IOException {
        String experimentBaseDirectory = "experimentBaseDirectory";

        ArrayList<ExperimentProblem<BinarySolution>> problemList = new ArrayList<>();
        problemList.add(new ExperimentProblem<>(new BinaryProblemInternal(variables)));

        List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> algorithmList = BinaryExperiment.configureAlgorithmList(problemList, maxEvaluations);

        Experiment<BinarySolution, List<BinarySolution>> experiment = new ExperimentBuilder<BinarySolution, List<BinarySolution>>("ExperimentsBinary")
                        .setAlgorithmList(algorithmList)
                        .setProblemList(problemList)
                        .setExperimentBaseDirectory(experimentBaseDirectory)
                        .setOutputParetoFrontFileName("FUN")
                        .setOutputParetoSetFileName("VAR")
                        .setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
                        .setIndicatorList(Arrays.<GenericIndicator<BinarySolution>>asList(new PISAHypervolume<BinarySolution>()))
                        .setIndependentRuns(INDEPENDENT_RUNS)
                        .setNumberOfCores(8)
                        .build();

        new ExecuteAlgorithms<>(experiment).run();
        new GenerateReferenceParetoFront(experiment).run();
        new ComputeQualityIndicators<>(experiment).run() ;
        new GenerateLatexTablesWithStatistics(experiment).run() ;
        new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run() ;
    }
}
