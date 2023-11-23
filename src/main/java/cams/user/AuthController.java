package cams.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cams.filter.CampFilterController;

/**
 * The AuthController control is responsible for facilitating {@code User} login, logout
 * and change password. It follows the Singleton pattern to ensure a single instance
 * throughout the application.
 * 
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class AuthController {
    /**
     * The sole instance of the AuthController class
     */
    private static AuthController authController;

    /**
     * The password encoder used to hash user passwords
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * The current user associated with the controller
     */
    private User currentUser;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private AuthController() {
        passwordEncoder = new BCryptPasswordEncoder(4);
        currentUser = null;
    }

    /**
     * Gets the singleton isntance of AuthController
     * 
     * @return the singleton instace of the AuthController
     */
    public static AuthController getInstance() {
        if (authController == null) {
            authController = new AuthController();
        }
        return authController;
    }

    /**
     * Closes the AuthController, releasing the current user association.
     */
    public static void close() {
        AuthController.authController = null;
    }

    /**
     * Gets the password encoder to be used to hash user passwords
     * 
     * @return the password encoder
     */
    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * Validates the user attempting to access the system.
     * 
     * @param userID the userID associated with a {@code User}
     * @param password the password associated with a {@code User}
     * @return the {@code User} accessing the system
     * @throws IllegalArgumentException when entered userID or password is incorrect
     */
    public User login(String userID, String password) throws IllegalArgumentException {
        UserController userController = UserController.getInstance();
        User user = userController.getUser(userID);
        if (user == null || !passwordEncoder.matches(password, user.getHashedPassword()))
            throw new IllegalArgumentException("Incorrect userID or password!");

        currentUser = user;
        return getCurrentUser();
    }

    /**
     * Sets the currentUser to null and exits the system.
     */
    public void logout() {
        currentUser = null;
        CampFilterController.close();
    }

    /**
     * Gets the current user associated with the controller.
     * 
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Changes the password associated with the current user.
     * 
     * @param userID the userID of the current User
     * @param oldPassword the old password to be changed
     * @param newPassword the new password to be set
     * @throws IllegalArgumentException when the "old password" entered is incorrect
     */
    public void changePassword(String userID, String oldPassword, String newPassword)
            throws IllegalArgumentException {
        login(userID, oldPassword).setHashedPassword(passwordEncoder.encode(newPassword));
    }

}
