package edu.uncc.cci.is.map_colouring.model;

import java.util.List;

public interface Constraint {
    List<Variable> getScope();
    boolean isSatisfiedWith(Assignment assignment);
}


