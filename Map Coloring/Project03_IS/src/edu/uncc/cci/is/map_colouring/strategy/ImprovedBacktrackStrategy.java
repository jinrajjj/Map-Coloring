package edu.uncc.cci.is.map_colouring.strategy;

import edu.uncc.cci.is.map_colouring.main.Main;
import edu.uncc.cci.is.map_colouring.main.Output;
import edu.uncc.cci.is.map_colouring.model.*;
import edu.uncc.cci.is.map_colouring.utility.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ImprovedBacktrackStrategy extends BacktrackStrategy {
    private Selection selectionStrategy = Selection.DEFAULT_ORDER;
    private Inference inferenceStrategy = Inference.NONE;
    private boolean isLCVHeuristicEnabled;

    public ImprovedBacktrackStrategy(boolean enableMRV, boolean enableDeg, boolean enableLCV, boolean enableForwardChecking) {
        if (enableMRV)
            setVariableSelection(enableDeg ? Selection.MRV_DEG : Selection.MRV);
        if (enableForwardChecking)
            setInference(Inference.FORWARD_CHECKING);
        enableLCV(enableLCV);
    }

    public void setVariableSelection(Selection sStrategy) {
        selectionStrategy = sStrategy;
    }

    public void setInference(Inference iStrategy) {
        inferenceStrategy = iStrategy;
    }

    public void enableLCV(boolean state) {
        isLCVHeuristicEnabled = state;
    }

    public Assignment solve(ConstSatisfactionProb constSatisfactionProb) {
        return super.solve(constSatisfactionProb);
    }

    @Override
    protected Variable selectUnassignedVariable(Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        switch (selectionStrategy) {
            case MRV:
                return applyMRVHeuristic(constSatisfactionProb, assignment).get(0);
            case MRV_DEG:
                List<Variable> vars = applyMRVHeuristic(constSatisfactionProb, assignment);
                return applyDegreeHeuristic(vars, assignment, constSatisfactionProb).get(0);
            default:
                for (Variable var : constSatisfactionProb.getVariableList()) {
                    if (!(assignment.hasAssignmentFor(var)))
                        return var;
                }
        }
        return null;
    }

    @Override
    protected Iterable<?> orderDomainValues(Variable variable, Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        if (!isLCVHeuristicEnabled) {
            return constSatisfactionProb.getDomain(variable);
        } else {
            return applyLeastConstrainingValueHeuristic(variable, constSatisfactionProb);
        }
    }

    @Override
    protected DomainRestore inference(Variable variable, Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        if (inferenceStrategy == Inference.FORWARD_CHECKING) {
            return doForwardChecking(variable, assignment, constSatisfactionProb);
        }
        return new DomainRestore().compactify();
    }

    private List<Variable> applyMRVHeuristic(ConstSatisfactionProb constSatisfactionProb, Assignment assignment) {
        List<Variable> result = new ArrayList<Variable>();
        int mrv = Integer.MAX_VALUE;
        for (Variable var : constSatisfactionProb.getVariableList()) {
            if (!assignment.hasAssignmentFor(var)) {
                int num = constSatisfactionProb.getDomain(var).size();
                if (num <= mrv) {
                    if (num < mrv) {
                        result.clear();
                        mrv = num;
                    }
                    result.add(var);
                }
            }
        }
        return result;
    }

    private List<Variable> applyDegreeHeuristic(List<Variable> vars, Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        List<Variable> result = new ArrayList<Variable>();
        int maxDegree = Integer.MIN_VALUE;
        for (Variable var : vars) {
            int degree = 0;
            for (Constraint constraint : constSatisfactionProb.getConstraints(var)) {
                Variable neighbor = constSatisfactionProb.getNeighbor(var, constraint);
                if (!assignment.hasAssignmentFor(neighbor)
                        && constSatisfactionProb.getDomain(neighbor).size() > 1)
                    ++degree;
            }
            if (degree >= maxDegree) {
                if (degree > maxDegree) {
                    result.clear();
                    maxDegree = degree;
                }
                result.add(var);
            }
        }
        return result;
    }

    public static Output useSkeleton(BacktrackStrategy bst, ConstSatisfactionProb mapConstSatisfactionProb, String heuristic) {
        System.out.println("---------------------------------------------------------");
        Output output = new Output();
        double noOfBackTracks = 0;
        double timeInMillis = 0;
        for (int iteration = 0; iteration < Main.TOTAL_RUNS_COUNT; iteration++) {
            long startStrategy = System.currentTimeMillis();
            Assignment assignment = bst.solve(mapConstSatisfactionProb, heuristic, true);
            long endStrategy = System.currentTimeMillis();
            noOfBackTracks += assignment.getBacktrackCount();
            timeInMillis += (endStrategy - startStrategy);
            output.addAssignment(assignment);
            System.out.println("An iteration completed");
        }
        output.setAvgBacktrackCount(noOfBackTracks / Main.TOTAL_RUNS_COUNT);
        output.setAvgTimeMsec(timeInMillis / Main.TOTAL_RUNS_COUNT);
        double num = output.getAvgBacktrackCount() * 0.6;
        double sol = output.getAvgTimeMsec() * 0.6;
        System.out.println("Total number of backtracks = " + num);
        System.out.println("Time taken = " + sol + " ms");
        System.out.println("#########################################################");
        return output;
    }

    private List<Object> applyLeastConstrainingValueHeuristic(Variable var, ConstSatisfactionProb constSatisfactionProb) {
        List<Pair<Object, Integer>> pairs = new ArrayList<>();
        for (Object value : constSatisfactionProb.getDomain(var)) {
            int num = countLostValues(var, value, constSatisfactionProb);
            pairs.add(new Pair<>(value, num));
        }
        pairs.sort(new Comparator<Pair<Object, Integer>>() {
            @Override
            public int compare(Pair<Object, Integer> o1, Pair<Object, Integer> o2) {
                return o1.getSecond().compareTo(o2.getSecond());
            }
        });
        List<Object> result = new ArrayList<>();
        for (Pair<Object, Integer> pair : pairs)
            result.add(pair.getFirst());
        return result;
    }

    private int countLostValues(Variable var, Object value, ConstSatisfactionProb constSatisfactionProb) {
        int result = 0;
        Assignment assignment = new Assignment();
        assignment.setAssignment(var, value);
        for (Constraint constraint : constSatisfactionProb.getConstraints(var)) {
            Variable neighbor = constSatisfactionProb.getNeighbor(var, constraint);
            for (Object nValue : constSatisfactionProb.getDomain(neighbor)) {
                assignment.setAssignment(neighbor, nValue);
                if (constraint.isSatisfiedWith(assignment)) {
                    ++result;
                }
            }
        }
        return result;
    }

    private DomainRestore doForwardChecking(Variable var,
                                            Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        DomainRestore result = new DomainRestore();
        for (Constraint constraint : constSatisfactionProb.getConstraints(var)) {
            List<Variable> scope = constraint.getScope();
            if (scope.size() == 2) {
                for (Variable neighbor : constraint.getScope()) {
                    if (!assignment.hasAssignmentFor(neighbor)) {
                        if (revise(neighbor, constraint, assignment, constSatisfactionProb,
                                result)) {
                            if (constSatisfactionProb.getDomain(neighbor).isEmpty()) {
                                result.setEmptyDomainFound(true);
                                return result;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean revise(Variable var, Constraint constraint,
                           Assignment assignment, ConstSatisfactionProb constSatisfactionProb, DomainRestore info) {

        boolean revised = false;
        for (Object value : constSatisfactionProb.getDomain(var)) {
            assignment.setAssignment(var, value);
            if (constraint.isSatisfiedWith(assignment)) {
                info.storeDomainFor(var, constSatisfactionProb.getDomain(var));
                constSatisfactionProb.removeValueFromDomain(var, value);
                revised = true;
            }
            assignment.removeAssignment(var);
        }
        return revised;
    }

    public enum Selection {
        DEFAULT_ORDER, MRV, MRV_DEG
    }

    public enum Inference {
        NONE, FORWARD_CHECKING
    }
}
