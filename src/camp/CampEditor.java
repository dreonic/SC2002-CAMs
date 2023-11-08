package camp;

import java.time.LocalDate;

public class CampEditor {
    private Camp camp;

    public CampEditor(Camp camp) {
        this.camp = camp;
    }

    public void editName(String name) {
        camp.getCampInfo().setCampName(name);
    }

    public void editDescription(String description) {
        camp.getCampInfo().setDescription(description);
    }

    public void editLocation(String location) {
        camp.getCampInfo().setLocation(location);
    }

    public void editStartDate(LocalDate startDate) {
        camp.getCampDate().setStartDate(startDate);
    }

    public void editEndDate(LocalDate endDate) {
        camp.getCampDate().setEndDate(endDate);
    }

    public void editRegistrationDeadline(LocalDate registrationDeadline) {
        camp.getCampDate().setRegistrationDeadline(registrationDeadline);
    }

    public void editTotalSlots(int totalSlots) {
        camp.getCampInfo().setTotalSlots(totalSlots);
    }

    public void toggleVisibility(boolean isVisible) {
        camp.getCampInfo().setIsVisible(isVisible);
    }

    public Camp getEditedCamp() {
        return camp;
    }
}

