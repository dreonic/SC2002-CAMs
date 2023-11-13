package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;

public class FilterByName implements FilterStrategy {
    private String name;
    ArrayList<Camp> campByName = new ArrayList<Camp>();

    public void setCriteria(Object object) {
        String name = (String) object;
        this.name = name;
    }

    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampInfo().getCampName().equalsIgnoreCase(name)) {
                campByName.add(camp);
            }
        }
        return campByName;
    }
}