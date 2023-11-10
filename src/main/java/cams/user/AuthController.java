package cams.user;

import java.util.HashMap;

public class AuthController {
    private static AuthController authController;
    private final HashMap<String, User> users = new HashMap<>();

    private AuthController() {
    }

    public static AuthController getAuthController() {
        if (authController == null) {
            authController = new AuthController();
        }
        return authController;
    }

    public String hashPassword(String password) {
        // TODO: implement hashing algorithm
        String hashedPassword = "";
        return hashedPassword;
    }

    public User login(String userID, String password) {
        User user = users.get(userID);
        if (user == null || !user.getPasswordHash().equals(hashPassword(password))) {
            // TODO: throw exception instead to notify caller that login failed because user
            // ID or password was incorrect
            return null;
        }

        return user;
    }

    public void logout() {
        // TODO: implement logout logic
    }

    public void changePassword(String userID, String oldPassword, String newPassword) {
        User user = login(userID, oldPassword);
        if (user == null) {
            throw new IllegalArgumentException("Invalid old password");
        }

        user.setPasswordHash(hashPassword(newPassword));
    }
}
