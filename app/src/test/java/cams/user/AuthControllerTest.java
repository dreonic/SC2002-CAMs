package cams.user;

import cams.domain.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {
    static void removeTestFiles() {
        try {
            Files.delete(Paths.get("test_staff_list.xlsx"));
            Files.delete(Paths.get("test_student_list.xlsx"));
            Files.delete(Paths.get("test_camp_list.xlsx"));
            Files.delete(Paths.get("test_enquiry_list.xlsx"));
            Files.delete(Paths.get("test_suggestion_list.xlsx"));
        } catch (IOException ignored) {
        }
    }

    @BeforeAll
    static void initializeControllers() {
        removeTestFiles();
        AuthController.getInstance();
        UserController.getInstance("test_student_list.xlsx", "test_staff_list.xlsx");
        initializeUserTable();
    }

    static void initializeUserTable() {
        UserController userController = UserController.getInstance();
        userController.addUser(new Student(
                "Test User A", "testa", "Testing Faculty", null));
        userController.addUser(new Student(
                "Test User B", "testb", "Testing Faculty", null));
        userController.addUser(new Student(
                "Test User C", "testc", "Testing Faculty", null));
    }

    @AfterAll
    static void closeControllers() {
        UserController.close();
        AuthController.close();
        removeTestFiles();
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

    @Test
    @DisplayName("Logging out sets current user to null")
    void logoutSetsCurrentUserToNull() {
        AuthController authController = AuthController.getInstance();
        String userID = "testc", password = "password";
        assertDoesNotThrow(() -> {
            authController.login(userID, password);
        });
        authController.logout();
        assertNull(authController.getCurrentUser());
    }
}
