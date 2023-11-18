package cams.camp;

import cams.domain.Staff;
import cams.domain.Student;
import cams.user.UserController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.jupiter.api.Assertions.*;

public class CampControllerTest {
    Staff campStaff = new Staff("Staff A", "staffa", "Testing Faculty", null);

    @BeforeAll
    static void initializeControllers() {
        UserController.getInstance();
        CampController.getInstance();
    }

    @AfterAll
    static void cleanCampTable() {
        CampController campController = CampController.getInstance();

        for (Camp camp : campController.getAllCamps()) {
            for (Student attendee : camp.getAttendees()) {
                camp.removeAttendee(attendee);
            }
            campController.deleteCamp(camp.getCampInfo().getCampName());
        }
    }

    @AfterAll
    static void closeControllers() {
        CampController.close();
        UserController.close();
    }

    @Test
    @DisplayName("Getting camp ignores the camp's name case")
    void getCampIgnoresCase() {
        CampController campController = CampController.getInstance();
        String campName = "Testing Camp 7";
        assertDoesNotThrow(() -> {
            campController.createCamp(
                    campName, "Testing Location", "A random camp to test functionality",
                    LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                    100, true, "Testing Faculty", campStaff);
        });

        Camp createdCamp = campController.getCamp("Testing Camp 7");

        assertEquals(createdCamp, campController.getCamp("testing camp 7"));
        assertEquals(createdCamp, campController.getCamp("TeStING cAMp 7"));
        assertEquals(createdCamp, campController.getCamp("TESTING CAMP 7"));
        assertEquals(createdCamp, campController.getCamp("tESTING cAMP 7"));
    }

    @Test
    @DisplayName("Creating a valid camp adds a camp to camp table")
    void createCampAddsToCampTable() {
        CampController campController = CampController.getInstance();
        String campName = "Testing Camp";
        assertDoesNotThrow(() -> {
            campController.createCamp(
                    campName, "Testing Location", "A random camp to test functionality",
                    LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                    100, true, "Testing Faculty", campStaff);
        });
        Camp addedCamp = campController.getCamp(campName);
        assertNotNull(addedCamp);
        assertEquals(campName, addedCamp.getCampInfo().getCampName());
    }

    @Test
    @DisplayName("Deleting a nonexistent camp throws")
    void deleteNonexistentCampThrows() {
        CampController campController = CampController.getInstance();
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            campController.deleteCamp("Not Testing Camp");
        });
        assertEquals("No camp found with this name!", e.getMessage());
    }

    @Test
    @DisplayName("Deleting a camp with attendees throws")
    void deleteNonemptyCampThrows() {
        CampController campController = CampController.getInstance();
        Student campStudent = new Student("Student T", "studentt", "Testing Faculty", null);
        String campName = "Another Testing Camp";

        campController.createCamp(
                campName, "Testing Location", "A random camp to test functionality",
                LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                100, true, "Testing Faculty", campStaff);
        campController.getCamp(campName).addAttendee(campStudent);

        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            campController.deleteCamp(campName);
        });
        assertEquals("Camp must have no attendees to be deleted!", e.getMessage());
    }

    @Test
    @DisplayName("An empty camp can be deleted")
    void deleteEmptyCampSuccessful() {
        CampController campController = CampController.getInstance();
        String campName = "Testing Camp Z";

        campController.createCamp(
                campName, "Testing Location", "A random camp to test functionality",
                LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                100, true, "Testing Faculty", campStaff);
        assertDoesNotThrow(() -> campController.deleteCamp(campName));
    }

    @Test
    @DisplayName("Attendee list contains all attendees (not committee)")
    void attendeeListHasAllAttendees() {
        CampController campController = CampController.getInstance();
        Student student001 = new Student("Student 1", "student1", "Testing Faculty", null);
        Student student002 = new Student("Student 2", "student2", "Testing Faculty", null);
        Student student003 = new Student("Student 3", "student3", "Testing Faculty", null);
        String campName = "Testing Camp 001";

        campController.createCamp(
                campName, "Testing Location", "A random camp to test functionality",
                LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                100, true, "Testing Faculty", campStaff);

        Camp createdCamp = campController.getCamp(campName);
        createdCamp.addAttendee(student001);
        createdCamp.addAttendee(student002);
        createdCamp.addCommittee(student003);

        List<Student> attendanceList = campController.getAttendanceList(campName);
        assertThat(attendanceList, hasItem(student001));
        assertThat(attendanceList, hasItem(student002));
        assertThat(attendanceList, not(hasItem(student003)));
    }

    @Test
    @DisplayName("Performance report contains camp committee and their points")
    void performanceReportHasCommitteeAndPoints() {
        CampController campController = CampController.getInstance();
        Student student004 = new Student("Student 4", "student4", "Testing Faculty", null);
        Student student005 = new Student("Student 5", "student5", "Testing Faculty", null);
        Student student006 = new Student("Student 6", "student6", "Testing Faculty", null);
        String campName = "Testing Camp 003";

        campController.createCamp(
                campName, "Testing Location", "A random camp to test functionality",
                LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                100, true, "Testing Faculty", campStaff);

        Camp createdCamp = campController.getCamp(campName);
        createdCamp.addAttendee(student004);
        createdCamp.addCommittee(student005);
        createdCamp.addCommittee(student006);

        Map<Student, Integer> performanceReport = campController.getPerformanceReport(campName);
        assertThat(performanceReport, hasEntry(student005, 0));
        assertThat(performanceReport, hasEntry(student006, 0));
        assertThat(performanceReport, not(hasKey(student004)));
    }
}
