package cams.camp;

import java.time.LocalDate;

/**
 * The {@code CampEditor} class provides methods to edit various attributes of a
 * camp.
 *
 * Instances of this class are used to modify the editable properties of a given
 * camp's {@code CampInfo},
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
    private Camp camp;
    private CampController campController = CampController.getInstance();

    /**
     * Constructs a new {@code CampEditor} for the specified camp.
     *
     * @param camp The camp to be edited.
     */
    public CampEditor(Camp camp) {
        this.camp = camp;
    }

    /**
     * Edits the name of the camp.
     *
     * @param name The new name for the camp.
     */
    public void editName(String name) {
        campController.deleteCamp(camp.getCampInfo().getCampName());
        camp.getCampInfo().setCampName(name);
        campController.getCampTable().put(name, camp);

    }

    /**
     * Edits the description of the camp.
     *
     * @param description The new description for the camp.
     */
    public void editDescription(String description) {
        camp.getCampInfo().setDescription(description);
    }

    /**
     * Edits the location of the camp.
     *
     * @param location The new location for the camp.
     */
    public void editLocation(String location) {
        camp.getCampInfo().setLocation(location);
    }

    /**
     * Edits the start date of the camp.
     *
     * @param startDate The new start date for the camp.
     */
    public void editStartDate(LocalDate startDate) {
        camp.getCampDate().setStartDate(startDate);
    }

    /**
     * Edits the end date of the camp.
     *
     * @param endDate The new end date for the camp.
     */
    public void editEndDate(LocalDate endDate) {
        camp.getCampDate().setEndDate(endDate);
    }

    /**
     * Edits the registration deadline of the camp.
     *
     * @param registrationDeadline The new registration deadline for the camp.
     */
    public void editRegistrationDeadline(LocalDate registrationDeadline) {
        camp.getCampDate().setRegistrationDeadline(registrationDeadline);
    }

    /**
     * Edits the total slots available for the camp.
     *
     * @param totalSlots The new total slots for the camp.
     */
    public void editTotalSlots(int totalSlots) {
        camp.getCampInfo().setTotalSlots(totalSlots);
    }

    /**
     * Toggles the visibility of the camp.
     *
     * @param isVisible {@code true} to make the camp visible, {@code false}
     *                  otherwise.
     */
    public void toggleVisibility() {
        if (camp.getCampInfo().getIsVisible() == false) {
            camp.getCampInfo().setIsVisible(true);
        } else {
            camp.getCampInfo().setIsVisible(false);
        }
    }

    /**
     * Retrieves the camp after the edits.
     *
     * @return The edited {@code Camp} object.
     */
    public Camp getEditedCamp() {
        return camp;
    }
}
