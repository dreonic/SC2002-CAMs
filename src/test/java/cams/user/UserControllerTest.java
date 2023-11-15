package cams.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserControllerTest {
    @BeforeAll
    static void initalizeControllers() {
        AuthController.getInstance();
        UserController.getInstance();
    }

    @Test
    @DisplayName("Adding a user with existing ID throws")
    void addingExistingUserIDThrows() {
        UserController userController = UserController.getInstance();
        userController.addUser(new User(
                "Test User A", "testa", "Testing Faculty", null));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.addUser(new User(
                    "Test User", "testa", "Testing Faculty", null));
        });
        assertEquals("User with the same userID already exists!", exception.getMessage());
    }

    @AfterAll
    static void closeControllers() {
        UserController.close();
        AuthController.close();
    }
}
