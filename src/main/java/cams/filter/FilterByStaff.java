package cams.filter;

import cams.camp.Camp;
import cams.domain.Staff;

import java.util.List;
import java.util.ArrayList;

public class FilterByStaff implements FilterStrategy {
    private Staff staff;
    List<Camp> campByName = new ArrayList<Camp>();

    public void setCriteria(Object object) {
        Staff staff = (Staff) object;
        this.staff = staff;
    }

    public List<Camp> filter(List<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampInfo().getCampName().equalsIgnoreCase(staff.getUserID())) {
                campByName.add(camp);
            }
        }
        return campByName;
    }
}
