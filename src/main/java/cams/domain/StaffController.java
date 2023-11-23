package cams.domain;

/**
 * The StaffController controller class is responsible for managing the current staff user
 * in the system. It follows the Singleton pattern to ensure a single instance
 * throughout the application.
 */
@SuppressWarnings("UnusedReturnValue")
public class StaffController {
    /**
     * The sole instance of the StaffController class.
     */
    private static StaffController staffController;

    /**
     * The currently logged-in staff user.
     */
    private Staff staff;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private StaffController() {
        staff = null;
    }

    /**
     * Retrieves the instance of the StaffController. If it does not exist,
     * a new instance is created.
     *
     * @return the StaffController instance.
     */
    public static StaffController getInstance() {
        if (staffController == null) {
            staffController = new StaffController();
        }
        return staffController;
    }

    /**
     * Retrieves the instance of the StaffController and sets the current staff user.
     * If the instance does not exist, a new instance is created.
     *
     * @param staff the staff user to set as the current staff.
     * @return the StaffController instance.
     */
    public static StaffController getInstance(Staff staff) {
        if (staffController == null) {
            staffController = new StaffController();
        }
        staffController.setCurrentStaff(staff);
        return staffController;
    }

    /**
     * Closes the StaffController instance by setting it to null.
     */
    public static void close() {
        staffController = null;
    }

    /**
     * Gets the currently logged-in staff user.
     *
     * @return The currently logged-in staff user, or null if no user is logged in.
     */
    public Staff getCurrentStaff() {
        return staff;
    }

    /**
     * Sets the current staff user.
     *
     * @param staff The staff user to set as the current staff.
     */
    public void setCurrentStaff(Staff staff) {
        this.staff = staff;
    }
}
