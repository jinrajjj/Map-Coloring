package edu.uncc.cci.is.map_colouring.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.uncc.cci.is.map_colouring.utility.Pair;

public class DomainRestore {
    private List<Pair<Variable, Domain>> savedDomains;
    private HashSet<Variable> affectedVariables;
    private boolean emptyDomainObserved;

    public DomainRestore() {
        savedDomains = new ArrayList<>();
        affectedVariables = new HashSet<>();
    }

    public void storeDomainFor(Variable var, Domain domain) {
        if (!affectedVariables.contains(var)) {
            savedDomains.add(new Pair<Variable, Domain>(var, domain));
            affectedVariables.add(var);
        }
    }

    public void setEmptyDomainFound(boolean b) {
        emptyDomainObserved = b;
    }

    public DomainRestore compactify() {
        affectedVariables = null;
        return this;
    }

    public boolean isEmptyDomainFound() {
        return emptyDomainObserved;
    }

    public List<Pair<Variable, Domain>> getSavedDomains() {
        return savedDomains;
    }

    public void restoreDomains(ConstSatisfactionProb constSatisfactionProb) {
        for (Pair<Variable, Domain> pair : getSavedDomains())
            constSatisfactionProb.setDomain(pair.getFirst(), pair.getSecond());
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Pair<Variable, Domain> pair : savedDomains)
            result.append(pair.getFirst()).append("=").append(pair.getSecond()).append(" ");
        if (emptyDomainObserved)
            result.append("!");
        return result.toString();
    }
}
