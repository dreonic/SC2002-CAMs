package cams.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthController {
    private static AuthController authController;
    private User currentUser;
    private final BCryptPasswordEncoder passwordEncoder;

    private AuthController() {
        passwordEncoder = new BCryptPasswordEncoder(4);
        currentUser = null;
    }

    public static AuthController getInstance() {
        if (authController == null) {
            authController = new AuthController();
        }
        return authController;
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public User login(String userID, String password) throws IllegalArgumentException {
        UserController userController = UserController.getInstance();
        User user = userController.getUser(userID);
        if (user == null || !passwordEncoder.matches(password, user.getHashedPassword()))
            throw new IllegalArgumentException("eh Incorrect userID or password!");

        currentUser = user;
        return getCurrentUser();
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void changePassword(String userID, String oldPassword, String newPassword)
            throws IllegalArgumentException {
        login(userID, oldPassword).setHashedPassword(passwordEncoder.encode(newPassword));
    }

    public static void close() {
        AuthController.authController = null;
    }

}
