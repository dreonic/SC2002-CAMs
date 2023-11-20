package cams.view;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.repliable.Enquiry;
import cams.repliable.Suggestion;
import cams.serializer.RepliableSerializer;
import cams.user.AuthController;
import cams.user.User;
import cams.user.UserController;
import cams.view.base.CommonElements;
import cams.view.components.WelcomeMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class DisplayableIT {
    static final PrintStream stdout = System.out;
    static final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    void provideInput(String data) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inStream);
    }

    void assertEndCorrectly() {
        assertThat(outStream.toString(), containsString(CommonElements.getHeader()));
        assertThat(outStream.toString(), containsString("Welcome to Camp Application and Management System (CAMs)"));
        assertThat(outStream.toString(), containsString("(1) Login"));
        assertThat(outStream.toString(), containsString("(2) Exit"));
    }

    void runApp() {
        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();
    }

    void redirectSystemInOut() {
        System.setOut(new PrintStream(outStream));
    }

    void removeTestFiles() {
        try {
            Files.delete(Paths.get("test_camp_list.xlsx"));
            Files.delete(Paths.get("test_enquiry_list.xlsx"));
            Files.delete(Paths.get("test_staff_list.xlsx"));
            Files.delete(Paths.get("test_student_list.xlsx"));
            Files.delete(Paths.get("test_suggestion_list.xlsx"));
        } catch (IOException ignored) {
        }
    }

    @BeforeEach
    void initializeControllers() {
        removeTestFiles();
        AuthController.getInstance();
        UserController.getInstance("test_student_list.xlsx", "test_staff_list.xlsx");
        CampController.getInstance("test_camp_list.xlsx");
        RepliableSerializer.deserialize("enquiry", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
        RepliableSerializer.deserialize("suggestion", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
        redirectSystemInOut();
    }

    @Test
    @DisplayName("Welcome menu displays properly")
    void welcomeMenuDisplaysProperly() {
        provideInput("2\n");
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();
    }

    @Test
    @DisplayName("Valid user can successfully login and logout, ignores user ID case")
    void validUserCanLoginLogout() {
        provideInput("1\nUpam\npassword\n5\n\n2\n");
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();
    }

    @Test
    @DisplayName("Cannot login with incorrect user ID or password")
    void cannotLoginWithIncorrectUserIDOrPassword() {
        provideInput("1\nnonexistent\npassword\n\nUPAM\nwrongpassword\n\nUPAM\npassword\n5\n\n2\n");
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();
    }

    @Test
    @DisplayName("Valid user can change password")
    void validUserCanChangePassword() {
        provideInput("1\nUPAM\npassword\n1\npassword\n12345678\n\n5\n\n2\n");
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        BCryptPasswordEncoder passwordEncoder = AuthController.getInstance().getPasswordEncoder();
        User user = UserController.getInstance().getUser("UPAM");
        assertTrue(passwordEncoder.matches("12345678", user.getHashedPassword()));

        provideInput("1\nUPAM\n\n12345678\n1\n12345678\npassword\n\n5\n\n2\n");
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();
    }

    @Test
    @DisplayName("Cannot change password without correct old password")
    void cannotChangePasswordWoCorrectOldPassword() {
        provideInput("1\nUPAM\npassword\n1\n12345678\nabcdefgh\n\npassword\npassword\n\n5\n\n2\n");
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        BCryptPasswordEncoder passwordEncoder = AuthController.getInstance().getPasswordEncoder();
        User user = UserController.getInstance().getUser("UPAM");
        assertTrue(passwordEncoder.matches("password", user.getHashedPassword()));
    }

    @Test
    @DisplayName("Staff can create camp")
    void staffCanCreateCamp() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        assertEquals("A Test Camp", campController.getCamp("A Test Camp").getCampInfo().getCampName());
    }

    @Test
    @DisplayName("Student can register for camp as an attendee")
    void studentCanRegisterCampAsAttendee() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n1\n\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        List<Camp> campsRegistered = ((Student) userController.getUser("KOH1")).getCamps();

        assertThat(campsRegistered, hasItem(campController.getCamp("A Test Camp")));
    }

    @Test
    @DisplayName("Student can register for camp as a committee")
    void studentCanRegisterCampAsCommittee() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        Student student = (Student) userController.getUser("KOH1");
        Camp camp = campController.getCamp("A Test Camp");
        List<Camp> campsRegistered = student.getCamps();

        assertThat(campsRegistered, hasItem(camp));
        assertEquals(student.getCommitteeFor(), camp);
    }


    @Test
    @DisplayName("Student can submit enquiry for camp when he is not a committee")
    void studentNonCommitteeCanSubmitEnquiryForCamp() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        List<Enquiry> studentEnquiries = ((Student) userController.getUser("KOH1")).getEnquiries();
        Set<Enquiry> enquiriesMade = campController.getCamp("A Test Camp").getEnquiries();

        assertThat(enquiriesMade, hasItem(studentEnquiries.get(0)));
    }

    @Test
    @DisplayName("Student can submit enquiry for camp when he is not a committee but an attendee")
    void studentNonAttendeeAttendeeCanSubmitEnquiryForCamp() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n1\n\n");
        testInput.append("3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        List<Enquiry> studentEnquiries = ((Student) userController.getUser("KOH1")).getEnquiries();
        Set<Enquiry> enquiriesMade = campController.getCamp("A Test Camp").getEnquiries();

        assertThat(enquiriesMade, hasItem(studentEnquiries.get(0)));
    }

    @Test
    @DisplayName("Student attendee can withdraw from registered camp")
    void studentAttendeeCanWithdrawFromRegisteredCamp() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n1\n\n");
        testInput.append("3\n1\n1\n\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        Student student = (Student) userController.getUser("KOH1");
        Camp camp = campController.getCamp("A Test Camp");

        assertThat(student.getCamps(), not(hasItem(camp)));
        assertThat(camp.getAttendees(), not(hasItem(student)));
    }

    @Test
    @DisplayName("Student cannot register if camp is full")
    void studentCannotRegisterIfCampFull() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n3\nNTU\nY\n");
        testInput.append("5\n\n");
        testInput.append("1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n1\n\n");
        testInput.append("5\n\n");
        testInput.append("1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n1\n\n");
        testInput.append("5\n\n");
        testInput.append("1\nYCHERN\npassword\n");
        testInput.append("2\n3\n1\n1\n1\n\n");
        testInput.append("5\n\n");
        testInput.append("1\nLE51\npassword\n");
        testInput.append("2\n3\n1\n1\n1\n\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        Camp camp = campController.getCamp("A Test Camp");

        assertEquals(3, camp.getAttendees().size());
        assertThat(camp.getAttendees(), hasItem((Student) userController.getUser("KOH1")));
        assertThat(camp.getAttendees(), hasItem((Student) userController.getUser("SL22")));
        assertThat(camp.getAttendees(), hasItem((Student) userController.getUser("YCHERN")));
        assertThat(camp.getAttendees(), not(hasItem((Student) userController.getUser("LE51"))));
    }

    @Test
    @DisplayName("Student cannot register as committee if there are no remaining committee slots")
    void studentCannotRegisterAsCommitteeIfNoRemainingCommitteeSlots() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n11\nNTU\nY\n");
        testInput.append("5\n\n");
        testInput.append("1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nYCHERN\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nYCN019\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nBR015\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nELI34\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nDL007\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nCT113\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nAKY013\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nDON84\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n");
        testInput.append("1\nLE51\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        Camp camp = campController.getCamp("A Test Camp");

        assertEquals(10, camp.getCommittee().size());
        assertEquals(0, camp.getAttendees().size());
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("KOH1")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("SL22")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("YCHERN")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("YCN019")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("BR015")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("ELI34")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("DL007")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("CT113")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("AKY013")));
        assertThat(camp.getCommittee().keySet(), hasItem((Student) userController.getUser("DON84")));
        assertThat(camp.getCommittee().keySet(), not(hasItem((Student) userController.getUser("LE51"))));
    }

    @Test
    @DisplayName("Student can edit enquiry")
    void studentCanEditEnquiry() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("4\n1\n1\nEdited student's enquiry for testing\n\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();


        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        Student student = (Student) userController.getUser("KOH1");
        Enquiry enquiry = student.getEnquiries().get(0);

        assertThat(campController.getCamp("A Test Camp").getEnquiries(), hasItem(enquiry));
        assertEquals("Edited student's enquiry for testing", enquiry.getQuestion());
    }

    @Test
    @DisplayName("Student can delete enquiry")
    void studentCanDeleteEnquiry() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("4\n1\n2\n\n1\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();


        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();

        assertEquals(0, campController.getCamp("A Test Camp").getEnquiries().size());
        assertEquals(0, ((Student) userController.getUser("KOH1")).getEnquiries().size());
    }

    @Test
    @DisplayName("Camp committee can submit suggestion")
    void campCommitteeCanSubmitSuggestion() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n1\nCommittee's testing suggestion\n5\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        Camp camp = campController.getCamp("A Test Camp");
        List<Suggestion> suggestions = new ArrayList<>(camp.getSuggestions());

        assertEquals(1, camp.getSuggestions().size());
        assertEquals("Committee's testing suggestion", suggestions.get(0).getContent());
    }


    @Test
    @DisplayName("Camp committee can edit suggestion")
    void campCommitteeCanEditSuggestion() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n1\nCommittee's testing suggestion\n2\n1\n1\nEdited committee's testing suggestion\n\n2\n5\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        Camp camp = campController.getCamp("A Test Camp");
        List<Suggestion> suggestions = new ArrayList<>(camp.getSuggestions());

        assertEquals(1, camp.getSuggestions().size());
        assertEquals("Edited committee's testing suggestion", suggestions.get(0).getContent());
    }

    @Test
    @DisplayName("Camp committee can delete suggestion")
    void campCommitteeCanDeleteSuggestion() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n1\nCommittee's testing suggestion\n2\n1\n2\n\n1\n5\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        Camp camp = campController.getCamp("A Test Camp");

        assertEquals(0, camp.getSuggestions().size());
    }

    @Test
    @DisplayName("Staff can reply enquiry")
    void staffCanReplyEnquiry() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nUPAM\npassword\n");
        testInput.append("3\n1\n2\n1\n1\nReplied enquiry\n\n2\n8\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        List<Enquiry> studentEnquiries = ((Student) userController.getUser("KOH1")).getEnquiries();
        Set<Enquiry> enquiriesMade = campController.getCamp("A Test Camp").getEnquiries();

        assertThat(enquiriesMade, hasItem(studentEnquiries.get(0)));
        assertEquals("Replied enquiry", studentEnquiries.get(0).getReply());
    }


    @Test
    @DisplayName("Camp committee can reply enquiry and earn 1 point")
    void campCommitteeCanReplyEnquiryAndEarn1Point() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n3\n1\n1\nReplied enquiry by committee\n\n2\n5\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        Camp camp = campController.getCamp("A Test Camp");
        List<Enquiry> studentEnquiries = ((Student) userController.getUser("KOH1")).getEnquiries();
        Set<Enquiry> enquiriesMade = campController.getCamp("A Test Camp").getEnquiries();

        assertThat(enquiriesMade, hasItem(studentEnquiries.get(0)));
        assertEquals("Replied enquiry by committee", studentEnquiries.get(0).getReply());
        assertEquals(1, camp.getCommittee().get((Student) userController.getUser("SL22")));
    }


    @Test
    @DisplayName("Staff can approve suggestion")
    void staffCanApproveSuggestion() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n1\nCommittee's testing suggestion\n5\n");
        testInput.append("6\n\n1\nUPAM\npassword\n");
        testInput.append("3\n1\n3\n1\nY\n\n2\n8\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        UserController userController = UserController.getInstance();
        Camp camp = campController.getCamp("A Test Camp");
        List<Suggestion> suggestions = new ArrayList<>(camp.getSuggestions());

        assertEquals(1, camp.getSuggestions().size());
        assertEquals("Committee's testing suggestion", suggestions.get(0).getContent());
        assertTrue(suggestions.get(0).getIsApproved());
        assertEquals(1, camp.getCommittee().get((Student) userController.getUser("KOH1")));
    }

    @Test
    @DisplayName("Staff can delete camp")
    void staffCanDeleteCamp() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("3\n1\n5\n\n1\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        assertTrue(campController.getCampTable().isEmpty());
    }


    @Test
    @DisplayName("Staff can edit camp")
    void staffCanEditCamp() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nA Test Camp\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("3\n1\n4\n1\nAn Edited Test Camp\n\n");
        testInput.append("2\nEdited Testing Location\n\n");
        testInput.append("3\nAn edited camp test description.\n\n");
        testInput.append("4\n\n");
        testInput.append("5\n8\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        CampController campController = CampController.getInstance();
        assertEquals("An Edited Test Camp", campController.getCamp("An Edited Test Camp").getCampInfo().getCampName());
        Camp camp = campController.getCamp("An Edited Test Camp");
        assertEquals("Edited Testing Location", camp.getCampInfo().getLocation());
        assertEquals("An edited camp test description.", camp.getCampInfo().getDescription());
        assertFalse(camp.getCampInfo().getIsVisible());
    }


    @Test
    @DisplayName("Camp committee can generate attendee list")
    void campCommitteeCanGenerateAttendeeList() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nTest\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n4\n1\n\n5\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        Path path = Paths.get("student_list_Test.xlsx");
        assertTrue(Files.exists(path));
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }


    @Test
    @DisplayName("Camp committee can generate committee list")
    void campCommitteeCanGenerateCommitteeList() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nTest\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n4\n2\n\n5\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        Path path = Paths.get("student_list_Test.xlsx");
        assertTrue(Files.exists(path));
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }


    @Test
    @DisplayName("Camp committee can generate combined student list")
    void campCommitteeCanGenerateCombinedStudentList() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nTest\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n4\n3\n\n5\n");
        testInput.append("6\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        Path path = Paths.get("student_list_Test.xlsx");
        assertTrue(Files.exists(path));
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }


    @Test
    @DisplayName("Staff can generate attendee list")
    void staffCanGenerateAttendeeList() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nTest\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n1\nUPAM\npassword\n");
        testInput.append("3\n1\n6\n1\n\n8\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        Path path = Paths.get("student_list_Test.xlsx");
        assertTrue(Files.exists(path));
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }


    @Test
    @DisplayName("Staff can generate committee list")
    void staffCanGenerateCommitteeList() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nTest\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n1\nUPAM\npassword\n");
        testInput.append("3\n1\n6\n2\n\n8\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        Path path = Paths.get("student_list_Test.xlsx");
        assertTrue(Files.exists(path));
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }

    @Test
    @DisplayName("Staff can generate combined student list")
    void staffCanGenerateCombinedStudentList() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nTest\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n2\nStudent's enquiry for testing\n");
        testInput.append("5\n\n1\nSL22\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("6\n\n1\nUPAM\npassword\n");
        testInput.append("3\n1\n6\n3\n\n8\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        Path path = Paths.get("student_list_Test.xlsx");
        assertTrue(Files.exists(path));
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }

    @Test
    @DisplayName("Staff can generate performance report")
    void staffCanGeneratePerformanceReport() {
        StringBuilder testInput = new StringBuilder("1\nUPAM\npassword\n");
        testInput.append("2\nTest\nTesting Location\nA camp used for testing purposes.\n19-11-2023\n21-11-2023\n10-11-2023\n15\nNTU\nY\n");
        testInput.append("5\n\n1\nKOH1\npassword\n");
        testInput.append("2\n3\n1\n1\n2\n\n");
        testInput.append("5\n1\nCommittee's testing suggestion\n5\n");
        testInput.append("6\n\n1\nUPAM\npassword\n");
        testInput.append("3\n1\n3\n1\nY\n\n2\n7\n\n8\n2\n");
        testInput.append("5\n\n2\n");
        provideInput(testInput.toString());
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::runApp);
        assertEndCorrectly();

        Path path = Paths.get("performance_report_Test.xlsx");
        assertTrue(Files.exists(path));
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }

    void resetSystemOut() {
        System.setOut(stdout);
    }

    void closeApp() {
        DisplayController.close();
    }

    @AfterEach
    void closeControllers() {
        closeApp();
        resetSystemOut();
        RepliableSerializer.serialize("suggestion", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
        RepliableSerializer.serialize("enquiry", "test_enquiry_list.xlsx", "test_suggestion_list.xlsx");
        CampController.close();
        UserController.close();
        AuthController.close();
        removeTestFiles();
    }
}
