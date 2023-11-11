package cams.user;

import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthController {
    private static AuthController authController;
    BCryptPasswordEncoder passwordEncoder;

    private AuthController() {
        passwordEncoder = new BCryptPasswordEncoder(4);
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

    public User login(String userID, String password) {
        UserController userController = UserController.getInstance();
        User user = userController.getUser(userID);
        if (user == null || !passwordEncoder.matches(password, user.getHashedPassword())) {
            // TODO: throw exception instead to notify caller that login failed because user
            // ID or password was incorrect
            System.out.println("FAIL");
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

        user.setHashedPassword(hashPassword(newPassword));
    }
}
