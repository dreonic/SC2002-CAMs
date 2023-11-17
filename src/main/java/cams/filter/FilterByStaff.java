package cams.filter;

import cams.camp.Camp;
import cams.domain.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterByStaff implements FilterStrategy {
    private Staff staff;

    public FilterByStaff() {
        staff = null;
    }

    public FilterByStaff(Staff staff) {
        this.staff = Objects.requireNonNull(staff);
    }


    public void setCriteria(Object object) {
        this.staff = (Staff) object;
    }

    public List<Camp> filter(List<Camp> camps) {
        List<Camp> campsByStaff = new ArrayList<>();

        for (Camp camp : camps) {
            if (camp.getStaffInCharge().equals(staff)) {
                campsByStaff.add(camp);
            }
        }
        return campsByStaff;
    }

    @Override
    public String toString() {
        return "Staff: " + staff.getName();
    }
}
