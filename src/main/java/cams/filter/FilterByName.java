package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;

public class FilterByName implements FilterStrategy {
    ArrayList<Camp> campByName = new ArrayList<Camp>();

    public ArrayList<Camp> filter(ArrayList<Camp> camps, Object object) {
        String name = (String) object;
        for (Camp camp : camps) {
            if (camp.getCampInfo().getCampName().equalsIgnoreCase(name)) {
                campByName.add(camp);
            }
        }
        return campByName;
    }
}
