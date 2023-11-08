package user;

import java.util.HashMap;

public class AuthController {
    private static AuthController authController;
    private final HashMap<String, User> users = new HashMap<>();

    private AuthController() {}

    public static AuthController getAuthController() {
        if (authController == null) {
            authController = new AuthController();
        }
        return authController;
    }

    public String hashPassword(String password) {
        // implement hashing algorithm
        return password;
    }

    public User login(String userID, String candidatePassword) {
        User user = users.get(userID);
        if (user == null) {
            return null;
        }

        String hashedPassword = user.getHashedPassword();
        if (!hashedPassword.equals(hashPassword(candidatePassword))) {
            return null;
        }

        return user;
    }

    public void logout() {
        // implement logout logic
    }

    public void changePassword(String userID, String oldPassword, String newPassword) {
        User user = login(userID, oldPassword);
        if (user == null) {
            throw new IllegalArgumentException("Invalid old password");
        }

        user.setHashedPassword(hashPassword(newPassword));
    }
}
