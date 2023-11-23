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

public class UserControllerTest {

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
    static void initalizeControllers() {
        removeTestFiles();
        AuthController.getInstance();
        UserController.getInstance("test_student_list.xlsx", "test_staff_list.xlsx");
    }

    @AfterAll
    static void closeControllers() {
        UserController.close();
        AuthController.close();
        removeTestFiles();
    }

    @Test
    @DisplayName("Adding a user with existing ID throws")
    void addingExistingUserIDThrows() {
        UserController userController = UserController.getInstance();
        userController.addUser(new Student(
                "Test User A", "testa", "Testing Faculty", null));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.addUser(new Student(
                    "Test User", "TesTa", "Testing Faculty", null));
        });
        assertEquals("User with the same userID already exists!", exception.getMessage());
    }

    @Test
    @DisplayName("Adding a user with unique ID updates the user table")
    void addingUserCorrectlyUpdatesTable() {
        UserController userController = UserController.getInstance();
        userController.addUser(new Student(
                "Test User B", "testb", "Testing Faculty", null));
        userController.addUser(new Student(
                "Test User C", "testc", "Testing Faculty", null));

        assertNotNull(userController.getUser("testB"));
        assertNotNull(userController.getUser("testC"));
    }
}
