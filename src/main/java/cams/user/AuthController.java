package cams.user;

import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthController {
    private static AuthController authController;
    private User currentUser;
    BCryptPasswordEncoder passwordEncoder;

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

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User login(String userID, String password) throws IllegalArgumentException {
        UserController userController = UserController.getInstance();
        User user = userController.getUser(userID.toUpperCase());
        if (user == null || !passwordEncoder.matches(password, user.getHashedPassword()))
            throw new IllegalArgumentException("Incorrect userID or password!");

        currentUser = user;
        return getCurrentUser();
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void changePassword(String userID, String oldPassword, String newPassword) throws IllegalArgumentException {
        login(userID, oldPassword).setHashedPassword(hashPassword(newPassword));
    }

    public void close() {
        AuthController.authController = null;
    }

}
