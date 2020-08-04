package edu.uncc.cci.is.map_colouring.model;
import java.util.ArrayList;
import java.util.List;

public class UnequalConstraint implements Constraint {
	private Variable variable1;
	private Variable variable2;
	private List<Variable> scope;

	public UnequalConstraint(Variable variable1, Variable variable2) {
		this.variable1 = variable1;
		this.variable2 = variable2;
		scope = new ArrayList<>(2);
		scope.add(variable1);
		scope.add(variable2);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		Object value1 = assignment.getAssignment(variable1);
		return value1 != null && value1.equals(assignment.getAssignment(variable2));
	}
}
