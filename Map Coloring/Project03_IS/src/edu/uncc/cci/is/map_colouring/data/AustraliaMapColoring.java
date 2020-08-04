package edu.uncc.cci.is.map_colouring.data;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.map_colouring.model.ConstSatisfactionProb;
import edu.uncc.cci.is.map_colouring.model.Domain;
import edu.uncc.cci.is.map_colouring.model.UnequalConstraint;
import edu.uncc.cci.is.map_colouring.model.Variable;

public class AustraliaMapColoring extends ConstSatisfactionProb {
    private static final Variable NSW = new Variable("NSW");
    private static final Variable NT = new Variable("NT");
    private static final Variable Q = new Variable("Q");
    private static final Variable SA = new Variable("SA");
    private static final Variable T = new Variable("T");
    private static final Variable V = new Variable("V");
    private static final Variable WA = new Variable("WA");
    private static final String RED = "RED";
    private static final String GREEN = "GREEN";
    private static final String BLUE = "BLUE";

    private static List<Variable> collectVariables() {
        List<Variable> variables = new ArrayList<Variable>();
        variables.add(NSW);
        variables.add(WA);
        variables.add(NT);
        variables.add(Q);
        variables.add(SA);
        variables.add(V);
        variables.add(T);
        return variables;
    }

    public AustraliaMapColoring() {
        super(collectVariables());
        Domain colors = new Domain(new Object[]{RED, GREEN, BLUE});
        for (Variable variable : getVariableList()) {
            setDomain(variable, colors);
        }
        addConstraint(new UnequalConstraint(WA, NT));
        addConstraint(new UnequalConstraint(WA, SA));
        addConstraint(new UnequalConstraint(NT, SA));
        addConstraint(new UnequalConstraint(NT, Q));
        addConstraint(new UnequalConstraint(SA, Q));
        addConstraint(new UnequalConstraint(SA, NSW));
        addConstraint(new UnequalConstraint(SA, V));
        addConstraint(new UnequalConstraint(Q, NSW));
        addConstraint(new UnequalConstraint(NSW, V));
    }
}