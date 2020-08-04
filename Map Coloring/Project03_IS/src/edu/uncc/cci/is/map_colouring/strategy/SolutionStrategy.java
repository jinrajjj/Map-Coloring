package edu.uncc.cci.is.map_colouring.strategy;

import edu.uncc.cci.is.map_colouring.model.ConstSatisfactionProb;
import edu.uncc.cci.is.map_colouring.model.Assignment;

public abstract class SolutionStrategy {
    public abstract Assignment solve(ConstSatisfactionProb constSatisfactionProb);
}
