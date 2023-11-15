package cams.camp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cams.domain.Staff;
import cams.domain.Student;
import cams.repliable.Enquiry;
import cams.repliable.Suggestion;

public class CampTest {
    Staff campStaff = new Staff("Staff A", "staffa", "Testing Faculty", null);
    Camp testCamp = new Camp(
            "Testing Camp", "Testing Location", "A random camp to test functionality",
            LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
            100, true, "Testing Faculty", campStaff);

    @Test
    @DisplayName("Set of camp enquiries is empty initially")
    void enquiriesSetIsEmptyInitially() {
        Set<Enquiry> enquiries = testCamp.getEnquiries();
        assertNotNull(enquiries);
        assertTrue(enquiries.isEmpty());

    }

    @Test
    @DisplayName("Set of camp suggestions is empty initially")
    void suggestionsSetIsEmptyInitially() {
        Set<Suggestion> suggestions = testCamp.getSuggestions();
        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    @DisplayName("List of camp attendees is empty initially")
    void attendeesListIsEmptyInitially() {
        List<Student> attendees = testCamp.getAttendees();
        assertNotNull(attendees);
        assertTrue(attendees.isEmpty());
    }

    @Test
    @DisplayName("Map of camp committee and their points is empty initially")
    void committeeMapIsEmptyInitially() {
        Map<Student, Integer> committee = testCamp.getCommittee();
        assertNotNull(committee);
        assertTrue(committee.isEmpty());
    }
}
