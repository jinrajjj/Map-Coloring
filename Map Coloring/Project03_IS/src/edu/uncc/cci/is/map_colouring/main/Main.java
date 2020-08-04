package edu.uncc.cci.is.map_colouring.main;

import java.util.HashMap;
import java.util.Map;

import edu.uncc.cci.is.map_colouring.data.AustraliaMapColoring;
import edu.uncc.cci.is.map_colouring.data.USAMapColoring;
import edu.uncc.cci.is.map_colouring.model.Assignment;
import edu.uncc.cci.is.map_colouring.model.ConstSatisfactionProb;
import edu.uncc.cci.is.map_colouring.strategy.BacktrackStrategy;

public class Main {
    public static final int TOTAL_RUNS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("*** Run started ***");
        System.out.println("#########################################################");
        System.out.println("Australia Map Coloring");
        System.out.println("#########################################################");
        AustraliaMapColoring australiaMapColoring = new AustraliaMapColoring();
        solveForMap("Australia", australiaMapColoring);
        System.out.println("#########################################################");
        System.out.println("USA Map Coloring");
        System.out.println("#########################################################");
        USAMapColoring usaMapColoring = new USAMapColoring();
        solveForMap("USA", usaMapColoring);
        System.out.println("\n*** Run complete ***");
    }

    private static void solveForMap(final String name, final ConstSatisfactionProb mapConstSatisfactionProb) {
        Map<String, Output> outputMap = new HashMap<>();
        BacktrackStrategy backtrackingSt = new BacktrackStrategy();
        // Case 1
        System.out.println("No Heuristics  used ");
        outputMap.put("No Heuristics", execute(backtrackingSt, mapConstSatisfactionProb, "NONE", false));
        // Case 2
        System.out.println("No Heuristics & Forward Checking");
        outputMap.put("No Heuristics & Forward Checking", execute(backtrackingSt, mapConstSatisfactionProb, "NONE", true));
        // Case 3
        System.out.println("No Heuristics & Forward Checking with propagation through singleton domains");
        outputMap.put("No Heuristics & Forward Checking & propagation through singleton domains", BacktrackStrategy.execSkeleton(backtrackingSt, mapConstSatisfactionProb, "NONE"));
        // Case 4
        System.out.println("MRV");
        outputMap.put("MRV", execute(backtrackingSt, mapConstSatisfactionProb, "MRV", false));
        // Case 5
        System.out.println("MRV & Forward Checking");
        outputMap.put("MRV & Forward Checking", execute(backtrackingSt, mapConstSatisfactionProb, "MRV", true));
        // Case 6
        System.out.println("MRV & Forward Checking with propagation through singleton domains");
        outputMap.put("MRV & Forward Checking & propagation through singleton domains", BacktrackStrategy.execSkeleton(backtrackingSt, mapConstSatisfactionProb, "MRV"));
        // Case 7
        System.out.println("Degree Constraint");
        outputMap.put("Degree Constraint", execute(backtrackingSt, mapConstSatisfactionProb, "DEG", false));
        // Case 8
        System.out.println("Degree Constraint & Forward Checking");
        outputMap.put("Degree Constraint & Forward Checking", execute(backtrackingSt, mapConstSatisfactionProb, "DEG", true));
        // Case 9
        System.out.println("Degree Constraint & Forward Checking with propagation through singleton domains");
        outputMap.put("Degree Constraint & Forward Checking & propagation through singleton domains", BacktrackStrategy.execSkeleton(backtrackingSt, mapConstSatisfactionProb, "DEG"));
        // Case 10
        System.out.println("Least Constraining Value");
        outputMap.put("LCV", execute(backtrackingSt, mapConstSatisfactionProb, "LCV", false));
        // Case 11
        System.out.println("Least Constraining Value & Forward Checking");
        outputMap.put("LCV & Forward Checking", execute(backtrackingSt, mapConstSatisfactionProb, "LCV", true));
        // Case 12
        System.out.println("Least Constraining Value & Forward Checking with propagation through singleton domains");
        outputMap.put("LCV & Forward Checking & propagation through singleton domains", BacktrackStrategy.execSkeleton(backtrackingSt, mapConstSatisfactionProb, "LCV"));

        System.out.println("Number of Runs for " + name + " = " + TOTAL_RUNS_COUNT);
        for (String key : outputMap.keySet()) {
            Output output = outputMap.get(key);
            System.out.printf("\n%s: \nAvg No of Backtracks = %f\nAvg Time in Milliseconds = %f ms\n", key, output.getAvgBacktrackCount(), output.getAvgTimeMsec());
        }
    }

    private static Output execute(BacktrackStrategy bst, ConstSatisfactionProb mapConstSatisfactionProb, String heuristic, boolean forwardChecking) {
        System.out.println("---------------------------------------------------------");
        Output output = new Output();
        double backtrackCount = 0;
        double executionTime = 0;
        for (int iteration = 0; iteration < TOTAL_RUNS_COUNT; iteration++) {
            double startTime = System.currentTimeMillis();
            Assignment assignment = bst.solve(mapConstSatisfactionProb, heuristic, forwardChecking);
            double endTime = System.currentTimeMillis();
            backtrackCount += assignment.getBacktrackCount();
            executionTime += (endTime - startTime);
            output.addAssignment(assignment);
            System.out.println("An iteration completed");
        }
        output.setAvgBacktrackCount(backtrackCount / TOTAL_RUNS_COUNT);
        output.setAvgTimeMsec(executionTime / TOTAL_RUNS_COUNT);
        System.out.println("Total number of backtracks = " + output.getAvgBacktrackCount());
        System.out.println("Time taken = " + output.getAvgTimeMsec() + " ms");
        System.out.println("#########################################################");
        return output;
    }
}
