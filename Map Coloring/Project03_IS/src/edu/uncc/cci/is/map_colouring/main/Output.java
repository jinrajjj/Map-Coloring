package edu.uncc.cci.is.map_colouring.main;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.map_colouring.model.Assignment;

public class Output {
    private double avgTimeMsec;
    private double avgBacktrackCount;
    private int runsCount;
    private List<Assignment> assignmentList = new ArrayList<>();

    public double getAvgTimeMsec() {
        return avgTimeMsec;
    }

    public void setAvgTimeMsec(final double avgTimeMsec) {
        this.avgTimeMsec = avgTimeMsec;
    }

    public double getAvgBacktrackCount() {
        return avgBacktrackCount;
    }

    public void setAvgBacktrackCount(final double avgBacktrackCount) {
        this.avgBacktrackCount = avgBacktrackCount;
    }

    public int getRunsCount() {
        return runsCount;
    }

    public void setRunsCount(final int runsCount) {
        this.runsCount = runsCount;
    }

    public List<Assignment> getAssignment() {
        return assignmentList;
    }

    public void addAssignment(final Assignment assignment) {
        this.assignmentList.add(assignment);
    }
}
