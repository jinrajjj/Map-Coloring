package edu.uncc.cci.is.map_colouring.data;


import java.util.ArrayList;
import java.util.List;

import edu.uncc.cci.is.map_colouring.model.ConstSatisfactionProb;
import edu.uncc.cci.is.map_colouring.model.Domain;
import edu.uncc.cci.is.map_colouring.model.UnequalConstraint;
import edu.uncc.cci.is.map_colouring.model.Variable;

public class USAMapColoring extends ConstSatisfactionProb {
    private static final String RED = "RED";
    private static final String GREEN = "GREEN";
    private static final String BLUE = "BLUE";
    private static final String YELLOW = "YELLOW";

    //states
    private static final Variable ALABAMA = new Variable("ALABAMA");
    private static final Variable ALASKA = new Variable("ALASKA");
    private static final Variable ARIZONA = new Variable("ARIZONA");
    private static final Variable ARKANSAS = new Variable("ARKANSAS");
    private static final Variable CALIFORNIA = new Variable("CALIFORNIA");
    private static final Variable COLORADO = new Variable("COLORADO");
    private static final Variable CONNECTICUT = new Variable("CONNECTICUT");
    private static final Variable DELAWARE = new Variable("DELAWARE");
    private static final Variable FLORIDA = new Variable("FLORIDA");
    private static final Variable GEORGIA = new Variable("GEORGIA");
    private static final Variable HAWAII = new Variable("HAWAII");
    private static final Variable IDAHO = new Variable("IDAHO");
    private static final Variable ILLINOIS = new Variable("ILLINOIS");
    private static final Variable INDIANA = new Variable("INDIANA");
    private static final Variable IOWA = new Variable("IOWA");
    private static final Variable KANSAS = new Variable("KANSAS");
    private static final Variable KENTUCKY = new Variable("KENTUCKY");
    private static final Variable LOUISIANA = new Variable("LOUISIANA");
    private static final Variable MAINE = new Variable("MAINE");
    private static final Variable MARYLAND = new Variable("MARYLAND");
    private static final Variable MASSACHUSETTS = new Variable("MASSACHUSETTS");
    private static final Variable MICHIGAN = new Variable("MICHIGAN");
    private static final Variable MINNESOTA = new Variable("MINNESOTA");
    private static final Variable MISSISSIPPI = new Variable("MISSISSIPPI");
    private static final Variable MISSOURI = new Variable("MISSOURI");
    private static final Variable MONTANA = new Variable("MONTANA");
    private static final Variable NEBRASKA = new Variable("NEBRASKA");
    private static final Variable NEVADA = new Variable("NEVADA");
    private static final Variable NEW_HAMSHIRE = new Variable("NEW HAMSHIRE");
    private static final Variable NEW_JERSEY = new Variable("NEW JERSEY");
    private static final Variable NEW_MEXICO = new Variable("NEW MEXICO");
    private static final Variable NEW_YORK = new Variable("NEW YORK");
    private static final Variable NORTH_CAROLINA = new Variable("NORTH CAROLINA");
    private static final Variable NORTH_DAKOTA = new Variable("NORTH DAKOTA");
    private static final Variable OHIO = new Variable("OHIO");
    private static final Variable OKLAHOMA = new Variable("OKLAHOMA");
    private static final Variable OREGON = new Variable("OREGON");
    private static final Variable PENNSYLVANIA = new Variable("PENNSYLVANIA");
    private static final Variable RHODE_ISLAND = new Variable("RHODE ISLAND");
    private static final Variable SOUTH_CAROLINA = new Variable("SOUTH CAROLINA");
    private static final Variable SOUTH_DAKOTA = new Variable("SOUTH DAKOTA");
    private static final Variable TENNESSE = new Variable("TENNESSE");
    private static final Variable TEXAS = new Variable("TEXAS");
    private static final Variable UTAH = new Variable("UTAH");
    private static final Variable VERMONT = new Variable("VERMONT");
    private static final Variable VIRGINIA = new Variable("VIRGINIA");
    private static final Variable WASHINGTON = new Variable("WASHINGTON");
    private static final Variable WEST_VIRGINIA = new Variable("WEST VIRGINIA");
    private static final Variable WISCONSIN = new Variable("WISCONSIN");
    private static final Variable WYOMING = new Variable("WYOMING");

    private static List<Variable> collectVariables() {
        List<Variable> variables = new ArrayList<>();
        variables.add(ALABAMA);
        variables.add(ALASKA);
        variables.add(ARIZONA);
        variables.add(ARKANSAS);
        variables.add(CALIFORNIA);
        variables.add(COLORADO);
        variables.add(CONNECTICUT);
        variables.add(DELAWARE);
        variables.add(FLORIDA);
        variables.add(GEORGIA);
        variables.add(HAWAII);
        variables.add(IDAHO);
        variables.add(ILLINOIS);
        variables.add(INDIANA);
        variables.add(IOWA);
        variables.add(KANSAS);
        variables.add(KENTUCKY);
        variables.add(LOUISIANA);
        variables.add(MAINE);
        variables.add(MARYLAND);
        variables.add(MASSACHUSETTS);
        variables.add(MICHIGAN);
        variables.add(MINNESOTA);
        variables.add(MISSISSIPPI);
        variables.add(MISSOURI);
        variables.add(MONTANA);
        variables.add(NEBRASKA);
        variables.add(NEVADA);
        variables.add(NEW_HAMSHIRE);
        variables.add(NEW_JERSEY);
        variables.add(NEW_MEXICO);
        variables.add(NEW_YORK);
        variables.add(NORTH_CAROLINA);
        variables.add(NORTH_DAKOTA);
        variables.add(OHIO);
        variables.add(OKLAHOMA);
        variables.add(OREGON);
        variables.add(PENNSYLVANIA);
        variables.add(RHODE_ISLAND);
        variables.add(SOUTH_CAROLINA);
        variables.add(SOUTH_DAKOTA);
        variables.add(TENNESSE);
        variables.add(TEXAS);
        variables.add(UTAH);
        variables.add(VERMONT);
        variables.add(VIRGINIA);
        variables.add(WASHINGTON);
        variables.add(WEST_VIRGINIA);
        variables.add(WISCONSIN);
        variables.add(WYOMING);
        return variables;
    }

    public USAMapColoring() {
        super(collectVariables());

        Domain colors = new Domain(new Object[]{RED, GREEN, BLUE, YELLOW});

        for (Variable variable : getVariableList()) {
            setDomain(variable, colors);
        }

        //Set constraints
        // ALABAMA
        addConstraint(new UnequalConstraint(ALABAMA, MISSISSIPPI));
        addConstraint(new UnequalConstraint(ALABAMA, GEORGIA));
        addConstraint(new UnequalConstraint(ALABAMA, TENNESSE));
        addConstraint(new UnequalConstraint(ALABAMA, FLORIDA));

        // ARIZONA
        addConstraint(new UnequalConstraint(ARIZONA, CALIFORNIA));
        addConstraint(new UnequalConstraint(ARIZONA, NEVADA));
        addConstraint(new UnequalConstraint(ARIZONA, NEW_MEXICO));
        addConstraint(new UnequalConstraint(ARIZONA, UTAH));
        addConstraint(new UnequalConstraint(ARIZONA, COLORADO));

        // ARKANSAS
        addConstraint(new UnequalConstraint(ARKANSAS, OKLAHOMA));
        addConstraint(new UnequalConstraint(ARKANSAS, TEXAS));
        addConstraint(new UnequalConstraint(ARKANSAS, LOUISIANA));
        addConstraint(new UnequalConstraint(ARKANSAS, MISSISSIPPI));
        addConstraint(new UnequalConstraint(ARKANSAS, TENNESSE));
        addConstraint(new UnequalConstraint(ARKANSAS, MISSOURI));

        // CALIFORNIA
        addConstraint(new UnequalConstraint(CALIFORNIA, OREGON));
        addConstraint(new UnequalConstraint(CALIFORNIA, NEVADA));

        // COLORADO
        addConstraint(new UnequalConstraint(COLORADO, UTAH));
        addConstraint(new UnequalConstraint(COLORADO, WYOMING));
        addConstraint(new UnequalConstraint(COLORADO, NEBRASKA));
        addConstraint(new UnequalConstraint(COLORADO, KANSAS));
        addConstraint(new UnequalConstraint(COLORADO, OKLAHOMA));
        addConstraint(new UnequalConstraint(COLORADO, NEW_MEXICO));

        // CONNECTICUT
        addConstraint(new UnequalConstraint(CONNECTICUT, NEW_YORK));
        addConstraint(new UnequalConstraint(CONNECTICUT, MASSACHUSETTS));
        addConstraint(new UnequalConstraint(CONNECTICUT, RHODE_ISLAND));

        // DELAWARE
        addConstraint(new UnequalConstraint(DELAWARE, PENNSYLVANIA));
        addConstraint(new UnequalConstraint(DELAWARE, NEW_JERSEY));
        addConstraint(new UnequalConstraint(DELAWARE, MARYLAND));

        // FLORIDA
        addConstraint(new UnequalConstraint(FLORIDA, GEORGIA));
        // GEORGIA
        addConstraint(new UnequalConstraint(GEORGIA, TENNESSE));
        addConstraint(new UnequalConstraint(GEORGIA, NORTH_CAROLINA));
        addConstraint(new UnequalConstraint(GEORGIA, SOUTH_CAROLINA));

        // HAWAII

        // IDAHO
        addConstraint(new UnequalConstraint(IDAHO, WASHINGTON));
        addConstraint(new UnequalConstraint(IDAHO, OREGON));
        addConstraint(new UnequalConstraint(IDAHO, NEVADA));
        addConstraint(new UnequalConstraint(IDAHO, UTAH));
        addConstraint(new UnequalConstraint(IDAHO, WYOMING));
        addConstraint(new UnequalConstraint(IDAHO, MONTANA));

        // ILLINOIS
        addConstraint(new UnequalConstraint(ILLINOIS, WISCONSIN));
        addConstraint(new UnequalConstraint(ILLINOIS, INDIANA));
        addConstraint(new UnequalConstraint(ILLINOIS, IOWA));
        addConstraint(new UnequalConstraint(ILLINOIS, MISSOURI));
        addConstraint(new UnequalConstraint(ILLINOIS, MICHIGAN));
        addConstraint(new UnequalConstraint(ILLINOIS, KENTUCKY));

        // INDIANA
        addConstraint(new UnequalConstraint(INDIANA, MICHIGAN));
        addConstraint(new UnequalConstraint(INDIANA, OHIO));
        addConstraint(new UnequalConstraint(INDIANA, KENTUCKY));

        // IOWA
        addConstraint(new UnequalConstraint(IOWA, MINNESOTA));
        addConstraint(new UnequalConstraint(IOWA, SOUTH_DAKOTA));
        addConstraint(new UnequalConstraint(IOWA, NEBRASKA));
        addConstraint(new UnequalConstraint(IOWA, MISSOURI));
        addConstraint(new UnequalConstraint(IOWA, WISCONSIN));

        // KANSAS
        addConstraint(new UnequalConstraint(KANSAS, OKLAHOMA));
        addConstraint(new UnequalConstraint(KANSAS, NEBRASKA));
        addConstraint(new UnequalConstraint(KANSAS, MISSOURI));

        // KENTUCKY
        addConstraint(new UnequalConstraint(KENTUCKY, TENNESSE));
        addConstraint(new UnequalConstraint(KENTUCKY, VIRGINIA));
        addConstraint(new UnequalConstraint(KENTUCKY, WEST_VIRGINIA));
        addConstraint(new UnequalConstraint(KENTUCKY, OHIO));
        addConstraint(new UnequalConstraint(KENTUCKY, MISSOURI));

        // LOUISIANA
        addConstraint(new UnequalConstraint(LOUISIANA, TEXAS));
        addConstraint(new UnequalConstraint(LOUISIANA, MISSISSIPPI));

        // MAINE
        addConstraint(new UnequalConstraint(MAINE, NEW_HAMSHIRE));

        // MARYLAND
        addConstraint(new UnequalConstraint(MARYLAND, WEST_VIRGINIA));
        addConstraint(new UnequalConstraint(MARYLAND, PENNSYLVANIA));
        addConstraint(new UnequalConstraint(MARYLAND, VIRGINIA));

        // MASSACHUSETTS
        addConstraint(new UnequalConstraint(MASSACHUSETTS, VERMONT));
        addConstraint(new UnequalConstraint(MASSACHUSETTS, NEW_HAMSHIRE));
        addConstraint(new UnequalConstraint(MASSACHUSETTS, RHODE_ISLAND));
        addConstraint(new UnequalConstraint(MASSACHUSETTS, NEW_YORK));

        // MICHIGAN
        addConstraint(new UnequalConstraint(MICHIGAN, WISCONSIN));
        addConstraint(new UnequalConstraint(MICHIGAN, OHIO));
        // MINNESOTA
        addConstraint(new UnequalConstraint(MINNESOTA, NORTH_DAKOTA));
        addConstraint(new UnequalConstraint(MINNESOTA, SOUTH_DAKOTA));
        addConstraint(new UnequalConstraint(MINNESOTA, WISCONSIN));

        // MISSISSIPPI
        addConstraint(new UnequalConstraint(MISSISSIPPI, TENNESSE));

        // MISSOURI
        addConstraint(new UnequalConstraint(MISSOURI, NEBRASKA));
        addConstraint(new UnequalConstraint(MISSOURI, OKLAHOMA));
        addConstraint(new UnequalConstraint(MISSOURI, TENNESSE));

        // MONTANA
        addConstraint(new UnequalConstraint(MONTANA, NORTH_DAKOTA));
        addConstraint(new UnequalConstraint(MONTANA, SOUTH_DAKOTA));
        addConstraint(new UnequalConstraint(MONTANA, WYOMING));

        // NEBRASKA
        addConstraint(new UnequalConstraint(NEBRASKA, SOUTH_DAKOTA));
        addConstraint(new UnequalConstraint(NEBRASKA, WYOMING));

        // NEVADA
        addConstraint(new UnequalConstraint(NEVADA, OREGON));
        addConstraint(new UnequalConstraint(NEVADA, UTAH));

        // NEW_HAMSHIRE
        addConstraint(new UnequalConstraint(NEW_HAMSHIRE, VERMONT));

        // NEW_JERSEY
        addConstraint(new UnequalConstraint(NEW_JERSEY, PENNSYLVANIA));
        addConstraint(new UnequalConstraint(NEW_JERSEY, NEW_YORK));

        // NEW_MEXICO
        addConstraint(new UnequalConstraint(NEW_MEXICO, TEXAS));
        addConstraint(new UnequalConstraint(NEW_MEXICO, OKLAHOMA));
        addConstraint(new UnequalConstraint(NEW_MEXICO, UTAH));

        // NEW_YORK
        addConstraint(new UnequalConstraint(NEW_YORK, VERMONT));
        addConstraint(new UnequalConstraint(NEW_YORK, PENNSYLVANIA));

        // NORTH_CAROLINA
        addConstraint(new UnequalConstraint(NORTH_CAROLINA, VIRGINIA));
        addConstraint(new UnequalConstraint(NORTH_CAROLINA, TENNESSE));
        addConstraint(new UnequalConstraint(NORTH_CAROLINA, SOUTH_CAROLINA));

        // NORTH_DAKOTA
        addConstraint(new UnequalConstraint(NORTH_DAKOTA, SOUTH_DAKOTA));

        // OHIO
        addConstraint(new UnequalConstraint(OHIO, WEST_VIRGINIA));
        addConstraint(new UnequalConstraint(OHIO, PENNSYLVANIA));

        // OKLAHOMA
        addConstraint(new UnequalConstraint(OKLAHOMA, TEXAS));

        // OREGON
        addConstraint(new UnequalConstraint(OREGON, WASHINGTON));

        // PENNSYLVANIA
        addConstraint(new UnequalConstraint(PENNSYLVANIA, WEST_VIRGINIA));

        // RHODE_ISLAND - all covered

        // SOUTH_CAROLINA - all covered

        // SOUTH_DAKOTA
        addConstraint(new UnequalConstraint(SOUTH_DAKOTA, WYOMING));

        // TENNESSE
        addConstraint(new UnequalConstraint(TENNESSE, VIRGINIA));

        // TEXAS - all covered

        // UTAH
        addConstraint(new UnequalConstraint(UTAH, WYOMING));

        // VERMONT - all covered

        // VIRGINIA
        addConstraint(new UnequalConstraint(VIRGINIA, WEST_VIRGINIA));

        // WASHINGTON - all covered

        // WEST_VIRGINIA - all covered

        // WISCONSIN - all covered

        // WYOMING - all covered
    }
}