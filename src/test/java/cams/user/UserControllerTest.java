package cams.user;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {
    @BeforeAll
    static void initalizeControllers() {
        AuthController.getInstance();
        UserController.getInstance("test_student_list.xlsx", "test_staff_list.xlsx");
    }

    @AfterAll
    static void closeControllers() {
        UserController.close();
        AuthController.close();
    }

    @Test
    @DisplayName("Adding a user with existing ID throws")
    void addingExistingUserIDThrows() {
        UserController userController = UserController.getInstance();
        userController.addUser(new User(
                "Test User A", "testa", "Testing Faculty", null));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.addUser(new User(
                    "Test User", "TesTa", "Testing Faculty", null));
        });
        assertEquals("User with the same userID already exists!", exception.getMessage());
    }

    @Test
    @DisplayName("Adding a user with unique ID updates the user table")
    void addingUserCorrectlyUpdatesTable() {
        UserController userController = UserController.getInstance();
        userController.addUser(new User(
                "Test User B", "testb", "Testing Faculty", null));
        userController.addUser(new User(
                "Test User C", "testc", "Testing Faculty", null));

        assertNotNull(userController.getUser("testB"));
        assertNotNull(userController.getUser("testC"));
    }
}
