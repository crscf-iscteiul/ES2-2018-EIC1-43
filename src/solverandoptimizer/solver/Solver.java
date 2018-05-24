package solverandoptimizer.solver;

import solverandoptimizer.experiment.IntegerExperimentExternal;
import solverandoptimizer.experiment.IntegerExperimentInternal;
import utils.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    public static void solve(Variable[] variables, List<String> algorithms){

        ArrayList<Variable> optimized_variables = new ArrayList<>();
        ArrayList<Variable> not_optimized_variables = new ArrayList<>();

        for(Variable v: variables) {
            if(v.isOptimized()){
                optimized_variables.add(v);
            } else {
                not_optimized_variables.add(v);
            }
        }

        try {
            switch (variables[0].getType()) {
                case(0):
                    if(!not_optimized_variables.isEmpty())
                        new IntegerExperimentInternal(not_optimized_variables.toArray(new Variable[not_optimized_variables.size()]));
                    if(!optimized_variables.isEmpty())
                        new IntegerExperimentExternal(optimized_variables.toArray(new Variable[optimized_variables.size()]));
                    break;
                case(1):
                    break;
                case(2):
                    break;
            }

/* Deverão ser comentadas ou retiradas de comentário as linhas
   correspondentes às simulações que se pretendem executar */
//            ExperimentsDouble.main(null);
//			  Ex1perimentsInteger.main(null);
//			  ExperimentsBinary.main(null);

/* As simulações com ExternalViaJAR no nome tem as funções de avaliação
   implementadas em .JAR externos que são invocados no método evaluate()
   As simulações que executam .jar externos são muito mais demoradas,
   maxEvaluations e INDEPENDENT_RUNS tem por isso valores mais baixos */
//            ExperimentsDoubleExternalViaJAR(null);

//			  ExperimentsBinaryExternal(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
