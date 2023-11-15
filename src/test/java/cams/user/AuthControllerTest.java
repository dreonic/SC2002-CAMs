package cams.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthControllerTest {
    @BeforeAll
    static void initializeControllers() {
        AuthController.getInstance();
        UserController.getInstance();
    }

    @BeforeAll
    static void initializeUserTable() {
        UserController userController = UserController.getInstance();
        userController.addUser(new User(
                "Test User A", "testa", "Testing Faculty", null));
        userController.addUser(new User(
                "Test User B", "testb", "Testing Faculty", null));
        userController.addUser(new User(
                "Test User C", "testc", "Testing Faculty", null));
    }

    @Test
    @DisplayName("Logging in with wrong/nonexistent user ID throws")
    void wrongUserIDLoginThrows() {
        AuthController authController = AuthController.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            authController.login("test", "password");
        });
    }

    @Test
    @DisplayName("Logging in with correct user ID but wrong password throws")
    void wrongPasswordLoginThrows() {
        AuthController authController = AuthController.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            authController.login("testa", "12345678");
        });
    }

    @Test
    @DisplayName("Logging in with the correct user ID and password returns the correct User")
    void successfulLoginReturnsCorrectUser() {
        AuthController authController = AuthController.getInstance();
        UserController userController = UserController.getInstance();
        String userID = "testa", pw = "password";

        User expectedUser = userController.getUser(userID);
        assertNotNull(expectedUser);

        User returnedUser = authController.login(userID, pw);
        assertEquals(expectedUser, returnedUser);
    }

    @Test
    @DisplayName("Changing password using wrong old password throws")
    void wrongOldPasswordWhenChangingPasswordThrows() {
        AuthController authController = AuthController.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            authController.changePassword("testb", "12345678", "12345678");
        });
    }

    @Test
    @DisplayName("Changing password using the correct old password changes user's password")
    void correctOldPasswordChangingPasswordSuccess() {
        AuthController authController = AuthController.getInstance();
        UserController userController = UserController.getInstance();

        String userID = "testb", oldPw = "password", newPw = "12345678";
        assertDoesNotThrow(() -> {
            authController.changePassword(userID, oldPw, newPw);
        });

        User expectedUser = userController.getUser(userID);
        assertNotNull(expectedUser);

        User returnedUser = authController.login(userID, newPw);
        assertEquals(expectedUser, returnedUser);
    }

    @AfterAll
    static void closeControllers() {
        UserController.close();
        AuthController.close();
    }
}
