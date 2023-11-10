package camp;

import java.time.LocalDate;

import domain.Staff;

public class Camp {
    private Staff staffInCharge;
    private CampInfo campInfo;
    private CampDate campDate;
    private String userGroup;

    public Camp(String campName, String location, String description, LocalDate startDate, LocalDate endDate,
            LocalDate registrationDeadline, int totalSlots, boolean isVisible, String userGroup) {
        this.campInfo = new CampInfo(campName, location, description, totalSlots, 10, true); 
        this.campDate = new CampDate(startDate, endDate, registrationDeadline);
        this.userGroup = userGroup;
    }

    public Staff getStaffInCharge() {
        return staffInCharge;
    }

    public void setStaffInCharge(Staff staff) {
        this.staffInCharge = staff;
    }

    public CampInfo getCampInfo() {
        return campInfo;
    }

    public CampDate getCampDate() {
        return campDate;
    }

    public String getUserGroup() {
        return userGroup;
    }
}
