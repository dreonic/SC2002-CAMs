package cams.user;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    BCryptPasswordEncoder passwordEncoder;

    @BeforeAll
    static void initializeControllers() {
        AuthController.getInstance();
    }

    @AfterAll
    static void closeControllers() {
        AuthController.close();
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
        User user = new User("Test User", "test001", "Testing Faculty", passwordEncoder.encode(password));
        assertTrue(passwordEncoder.matches(password, user.getHashedPassword()));
    }

    @Test
    @DisplayName("User ID is capitalized upon initialization")
    void userIDIsCapitalizedUponInitialization() {
        String userID = "tEst001";
        User user = new User("Test User", userID, "Testing Faculty", null);
        assertEquals(userID.toUpperCase(), user.getUserID());
    }
}
