package edu.uncc.cci.is.map_colouring.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Assignment {
    private List<Variable> variableList;
    private Hashtable<Variable, Object> variableToValue;
    private int backtrackCount = 0;

    public Assignment() {
        variableList = new ArrayList<>();
        variableToValue = new Hashtable<>();
    }

    public Object getAssignment(Variable var) {
        return variableToValue.get(var);
    }

    public void setAssignment(Variable variable, Object value) {
        if (!variableToValue.containsKey(variable))
            variableList.add(variable);
        variableToValue.put(variable, value);
    }

    public void removeAssignment(Variable variable) {
        if (hasAssignmentFor(variable)) {
            variableList.remove(variable);
            variableToValue.remove(variable);
        }
    }

    public boolean hasAssignmentFor(Variable variable) {
        return variableToValue.get(variable) != null;
    }

    public boolean isConsistent(List<Constraint> constraintList) {
        for (Constraint cons : constraintList)
            if (cons.isSatisfiedWith(this))
                return false;
        return true;
    }

    public boolean isComplete(List<Variable> variableList) {
        for (Variable var : variableList) {
            if (!hasAssignmentFor(var))
                return false;
        }
        return true;
    }

    public void incrementBacktrack() {
        backtrackCount++;
    }

    public int getBacktrackCount() {
        return backtrackCount;
    }

    @Override
    public String toString() {
        boolean comma = false;
        StringBuilder result = new StringBuilder("{");
        for (Variable var : variableList) {
            if (comma)
                result.append(", ");
            result.append(var).append("=").append(variableToValue.get(var));
            comma = true;
        }
        result.append("}");
        return result.toString();
    }
}