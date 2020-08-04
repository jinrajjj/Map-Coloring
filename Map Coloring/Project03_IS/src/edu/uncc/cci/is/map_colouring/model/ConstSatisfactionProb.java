package edu.uncc.cci.is.map_colouring.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class ConstSatisfactionProb {
    private List<Variable> variableList;
    private List<Domain> domainList;
    private List<Constraint> constraintList;
    private Hashtable<Variable, Integer> variableIntegerHashtable;
    private Hashtable<Variable, List<Constraint>> listHashtable;

    public ConstSatisfactionProb(List<Variable> variableList) {
        this.variableList = new ArrayList<>(variableList.size());
        domainList = new ArrayList<>(variableList.size());
        constraintList = new ArrayList<>();
        variableIntegerHashtable = new Hashtable<>();
        listHashtable = new Hashtable<>();
        Domain emptyDomain = new Domain(new ArrayList<>(0));
        int index = 0;
        for (Variable variable : variableList) {
            this.variableList.add(variable);
            domainList.add(emptyDomain);
            variableIntegerHashtable.put(variable, index++);
            listHashtable.put(variable, new ArrayList<>());
        }
    }

    public List<Variable> getVariableList() {
        return Collections.unmodifiableList(variableList);
    }

    public int indexOf(Variable variable) {
        return variableIntegerHashtable.get(variable);
    }

    public Domain getDomain(Variable variable) {
        return domainList.get(variableIntegerHashtable.get(variable));
    }

    public void setDomain(Variable var, Domain domain) {
        domainList.set(indexOf(var), domain);
    }

    public void removeValueFromDomain(Variable var, Object value) {
        Domain currDomain = getDomain(var);
        List<Object> values = new ArrayList<Object>(currDomain.size());
        for (Object v : currDomain)
            if (!v.equals(value))
                values.add(v);
        setDomain(var, new Domain(values));
    }

    public List<Constraint> getConstraintList() {
        return constraintList;
    }

    public List<Constraint> getConstraints(Variable variable) {
        return listHashtable.get(variable);
    }

    public void addConstraint(Constraint constraint) {
        constraintList.add(constraint);
        for (Variable var : constraint.getScope())
            listHashtable.get(var).add(constraint);
    }

    public Variable getNeighbor(Variable variable, Constraint constraint) {
        List<Variable> scope = constraint.getScope();
        if (scope.size() == 2) {
            if (variable == scope.get(0))
                return scope.get(1);
            else if (variable == scope.get(1))
                return scope.get(0);
        }
        return null;
    }
}
