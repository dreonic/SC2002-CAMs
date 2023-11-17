package cams.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserTest {
    BCryptPasswordEncoder passwordEncoder;

    @BeforeAll
    static void initializeControllers() {
        AuthController.getInstance();
    }

    @BeforeEach
    void initializePasswordEncoder() {
        AuthController authController = AuthController.getInstance();
        passwordEncoder = authController.getPasswordEncoder();
    }

    @Test
    @DisplayName("Default user password is \"password\"")
    void defaultUserPasswordIsPassword() {
        User user = new User("Test User", "test001", "Testing Faculty", null);
        assertTrue(passwordEncoder.matches("password", user.getHashedPassword()));
    }

    @Test
    @DisplayName("Password is hashed correctly if specified")
    void passwordHashedCorrectlyIfSpecified() {
        String password = "12345678";
        AuthController authController = AuthController.getInstance();
        User user = new User("Test User", "test001", "Testing Faculty", authController.hashPassword(password));
        assertTrue(passwordEncoder.matches(password, user.getHashedPassword()));
    }

    @Test
    @DisplayName("User ID is capitalized upon initialization")
    void userIDIsCapitalizedUponInitialization() {
        String userID = "tEst001";
        User user = new User("Test User", userID, "Testing Faculty", null);
        assertEquals(userID.toUpperCase(), user.getUserID());
    }

    @AfterAll
    static void closeControllers() {
        AuthController.close();
    }
}
