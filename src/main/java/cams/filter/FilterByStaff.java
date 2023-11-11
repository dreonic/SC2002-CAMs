package cams.filter;

import cams.camp.Camp;
import cams.domain.Staff;

import java.util.ArrayList;

public class FilterByStaff implements FilterStrategy {
    private Staff staff;
    ArrayList<Camp> campByName = new ArrayList<Camp>();

    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampInfo().getCampName().equalsIgnoreCase(staff.getName())) {
                campByName.add(camp);
            }
        }
        return campByName;
    }
}
