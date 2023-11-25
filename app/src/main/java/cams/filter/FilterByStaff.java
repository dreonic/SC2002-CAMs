package cams.filter;

import cams.camp.Camp;
import cams.domain.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The camp staff in charge visibility filter class implementation of the {@link FilterStrategy} interface.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByStaff implements FilterStrategy {
    private Staff staff;

    /**
     * Constructs the camp staff in charge filter with the criteria set to null.
     */
    public FilterByStaff() {
        staff = null;
    }

    /**
     * Constructs the camp staff filter with the provided criteria.
     *
     * @param staff the camp staff filter criteria
     */
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
        return "Staff: " + (staff != null ? staff.getName() : "");
    }
}
