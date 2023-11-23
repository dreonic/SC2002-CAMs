package cams.domain;

/**
 * The StaffController control class is responsible for managing the current staff user
 * in the system. It follows the Singleton pattern to ensure a single instance
 * throughout the application.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
@SuppressWarnings("UnusedReturnValue")
public class StaffController {
    /**
     * The sole instance of the StaffController class.
     */
    private static StaffController staffController;

    /**
     * The current staff associated with the controller.
     */
    private Staff staff;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private StaffController() {
        staff = null;
    }

    /**
     * Gets the singleton instance of StaffController.
     *
     * @return the singleton instance of StaffController
     */
    public static StaffController getInstance() {
        if (staffController == null) {
            staffController = new StaffController();
        }
        return staffController;
    }

    /**
     * Gets the singleton instance of StaffController with the specified staff.
     *
     * @param staff the staff to associate with the controller
     * @return the singleton instance of StaffController
     */
    public static StaffController getInstance(Staff staff) {
        if (staffController == null) {
            staffController = new StaffController();
        }
        staffController.setCurrentStaff(staff);
        return staffController;
    }

    /**
     * Closes the StaffController, releasing the current staff association.
     */
    public static void close() {
        staffController = null;
    }

    /**
     * Gets the current staff associated with the controller.
     *
     * @return the current staff
     */
    public Staff getCurrentStaff() {
        return staff;
    }

    /**
     * Sets the current staff associated with the controller.
     *
     * @param staff the staff to be set as the current staff
     */
    public void setCurrentStaff(Staff staff) {
        this.staff = staff;
    }
}
