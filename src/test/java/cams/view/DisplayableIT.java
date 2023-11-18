package cams.view;

import cams.camp.CampController;
import cams.serializer.RepliableSerializer;
import cams.user.AuthController;
import cams.user.UserController;
import cams.view.base.CommonElements;
import cams.view.components.WelcomeMenu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class DisplayableIT {
    static final PrintStream stdout = System.out;
    static final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    void provideInput(String data) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inStream);
    }

    @BeforeAll
    static void redirectSystemInOut() {
        System.setOut(new PrintStream(outStream));
    }

    @BeforeAll
    static void initializeControllers() {
        AuthController.getInstance();
        UserController.getInstance("test_student_list.xlsx", "test_staff_list.xlsx");
        CampController.getInstance("test_camp_list.xlsx");
        RepliableSerializer.deserialize("enquiry", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
        RepliableSerializer.deserialize("suggestion", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
    }

    @Test
    @DisplayName("Welcome menu displays properly")
    void welcomeMenuDisplaysProperly() {
        provideInput("2\n");

        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();

        assertThat(outStream.toString(), containsString(CommonElements.getHeader()));
        assertThat(outStream.toString(), containsString("Welcome to Camp Application and Management System (CAMs)"));
        assertThat(outStream.toString(), containsString("(1) Login"));
        assertThat(outStream.toString(), containsString("(2) Exit"));
    }

    @Test
    @DisplayName("Valid user can successfully login and logout")
    void validUserCanLoginLogout() {
        provideInput("1\nUPAM\npassword\n5\n\n2\n");

        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();

        assertThat(outStream.toString(), containsString(CommonElements.getHeader()));
        assertThat(outStream.toString(), containsString("Welcome to Camp Application and Management System (CAMs)"));
        assertThat(outStream.toString(), containsString("(1) Login"));
        assertThat(outStream.toString(), containsString("(2) Exit"));
    }

    @AfterAll
    static void resetSystemInOut() {
        System.setOut(stdout);
    }

    @AfterAll
    static void closeControllers() {
        RepliableSerializer.serialize("suggestion", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
        RepliableSerializer.serialize("enquiry", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
        CampController.close();
        UserController.close();
        AuthController.close();
    }
}
