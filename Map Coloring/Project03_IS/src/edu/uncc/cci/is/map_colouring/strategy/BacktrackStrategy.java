package edu.uncc.cci.is.map_colouring.strategy;

import edu.uncc.cci.is.map_colouring.main.Output;
import edu.uncc.cci.is.map_colouring.model.Assignment;
import edu.uncc.cci.is.map_colouring.model.ConstSatisfactionProb;
import edu.uncc.cci.is.map_colouring.model.DomainRestore;
import edu.uncc.cci.is.map_colouring.model.Variable;

public class BacktrackStrategy extends SolutionStrategy {
    private static final String MRV = "MRV";
    private static final String DEG = "DEG";
    private static final String LCV = "LCV";

    public Assignment solve(ConstSatisfactionProb constSatisfactionProb, String enableHeuristic, boolean enableForwardChecking) {
        return recursiveBacktrack(constSatisfactionProb, new Assignment(), enableHeuristic, enableForwardChecking);
    }

    private Assignment recursiveBacktrack(ConstSatisfactionProb constSatisfactionProb, Assignment assignment, String enableHeuristic, boolean enableForwardChecking) {
        Assignment result = null;
        if (assignment.isComplete(constSatisfactionProb.getVariableList())) {
            result = assignment;
        } else {
            boolean enableMRV = false;
            boolean enableDeg = false;
            boolean enableLCV = false;
            boolean noHeuristic = false;
            if (enableHeuristic.equalsIgnoreCase(MRV)) {
                enableMRV = true;
            } else if (enableHeuristic.equalsIgnoreCase(DEG)) {
                enableDeg = true;
                enableMRV = true;
            } else if (enableHeuristic.equalsIgnoreCase(LCV)) {
                enableLCV = true;
            } else {
                noHeuristic = true;
            }
            Variable variable;
            ImprovedBacktrackStrategy imprBackStrategy = null;
            if (noHeuristic) {
                variable = selectUnassignedVariable(assignment, constSatisfactionProb);
            } else {
                imprBackStrategy = new ImprovedBacktrackStrategy(enableMRV, enableDeg, enableLCV, enableForwardChecking);
                variable = imprBackStrategy.selectUnassignedVariable(assignment, constSatisfactionProb);
            }
            for (Object value : orderDomainValues(variable, assignment, constSatisfactionProb)) {
                assignment.setAssignment(variable, value);
                if (assignment.isConsistent(constSatisfactionProb.getConstraints(variable))) {
                    DomainRestore info;
                    if (noHeuristic) {
                        info = inference(variable, assignment, constSatisfactionProb);
                    } else {
                        info = imprBackStrategy.inference(variable, assignment, constSatisfactionProb);
                    }
                    if (!info.isEmptyDomainFound()) {
                        result = recursiveBacktrack(constSatisfactionProb, assignment, enableHeuristic, enableForwardChecking);
                        if (result != null)
                            break;
                    }
                    info.restoreDomains(constSatisfactionProb);
                }
                assignment.incrementBacktrack();
                assignment.removeAssignment(variable);
            }
        }
        return result;
    }

    protected Variable selectUnassignedVariable(Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        for (Variable variable : constSatisfactionProb.getVariableList()) {
            if (!(assignment.hasAssignmentFor(variable)))
                return variable;
        }
        return null;
    }

    public static Output execSkeleton(BacktrackStrategy bst, ConstSatisfactionProb mapConstSatisfactionProb, String heuristic) {
        return ImprovedBacktrackStrategy.useSkeleton(bst, mapConstSatisfactionProb, heuristic);
    }

    protected Iterable<?> orderDomainValues(Variable variable, Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        return constSatisfactionProb.getDomain(variable);
    }

    protected DomainRestore inference(Variable variable, Assignment assignment, ConstSatisfactionProb constSatisfactionProb) {
        return new DomainRestore().compactify();
    }

    // @Override
    public Assignment solve(ConstSatisfactionProb constSatisfactionProb) {
        // TODO Auto-generated method stub
        return null;
    }
}
