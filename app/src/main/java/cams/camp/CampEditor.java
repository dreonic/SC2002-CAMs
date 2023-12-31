package cams.camp;

import cams.domain.Staff;

import java.time.LocalDate;

/**
 * The {@code CampEditor} class provides methods to edit various attributes of a
 * camp.
 * <p>
 * Instances of this class are used to modify the editable properties of a given
 * camp's {@link CampInfo},
 * such as its name, description, location, dates, and visibility.
 * The changes made through the editor can be retrieved using the
 * {@code getEditedCamp} method.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class CampEditor {
    private final Camp camp;
    private final CampController campController;

    /**
     * Constructs a new {@code CampEditor} for the specified camp.
     *
     * @param camp the camp to be edited
     */
    public CampEditor(Camp camp) {
        this.camp = camp;
        campController = CampController.getInstance();
    }

    /**
     * Edits the name of the camp.
     *
     * @param name  the new name for the camp
     * @param staff staff who created the camp
     */
    public void editName(String name, Staff staff) {
        campController.deleteCamp(camp.getCampInfo().getCampName());
        camp.getCampInfo().setCampName(name);
        campController.getCampTable().put(name.toLowerCase(), camp);
        staff.addCamp(camp);
    }

    /**
     * Edits the description of the camp.
     *
     * @param description the new description for the camp
     */
    public void editDescription(String description) {
        camp.getCampInfo().setDescription(description);
    }

    /**
     * Edits the location of the camp.
     *
     * @param location the new location for the camp
     */
    public void editLocation(String location) {
        camp.getCampInfo().setLocation(location);
    }

    /**
     * Edits the registration deadline, start and end date of the camp.
     *
     * @param startDate            the new start date for the camp
     * @param endDate              the new end date for the camp
     * @param registrationDeadline the new registration deadline for the camp
     * @throws IllegalArgumentException when registration deadline is after start
     *                                  date or start date is after end date
     */
    public void editDates(LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline)
            throws IllegalArgumentException {
        camp.getCampDate().setDates(startDate, endDate, registrationDeadline);
    }

    /**
     * Edits the total slots available for the camp.
     *
     * @param totalSlots the new total slots for the camp
     */
    public void editTotalSlots(int totalSlots) {
        camp.getCampInfo().setTotalSlots(totalSlots);
    }

    /**
     * Toggles the visibility of the camp.
     */
    public void toggleVisibility() {
        camp.getCampInfo().setIsVisible(!camp.getCampInfo().getIsVisible());
    }
}

 
