package cams.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserTest {
    @BeforeAll
    static void initalizeControllers() {
        AuthController.getInstance();
        UserController.getInstance();
    }

    @Test
    @DisplayName("Default user password is \"password\"")
    void defaultUserPasswordIsPassword() {
        AuthController authController = AuthController.getInstance();
        BCryptPasswordEncoder passwordEncoder = authController.getPasswordEncoder();

        User user = new User("Test User", "test001", "Testing Faculty", null);
        assertTrue(passwordEncoder.matches("password", user.getHashedPassword()));
    }

    @AfterAll
    static void closeControllers() {
        UserController.close();
        AuthController.close();
    }
}
