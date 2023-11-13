package cams.camp;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import cams.domain.Staff;
import cams.domain.Student;
import cams.repliable.Enquiry;
import cams.repliable.Suggestion;

/**
 * Main entity class representing a camp.
 *
 * This class encapsulates information about a camp, date info
 * is encapsulated in campDate, while editable info is encapsulated in campInfo
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class Camp {
    private Staff staffInCharge;
    private Set<Enquiry> enquiries;
    private Set<Suggestion> suggestions;
    private Set<Student> attendees;
    private Map<Student, Integer> committee;
    private CampInfo campInfo;
    private CampDate campDate;
    private String userGroup;

    /**
     * Constructs a new camp with the specified details.
     *
     * @param campName             The name of the camp.
     * @param location             The location of the camp.
     * @param description          The description of the camp.
     * @param startDate            The start date of the camp.
     * @param endDate              The end date of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     * @param totalSlots           The total available slots for the camp.
     * @param isVisible            Indicates whether the camp is visible.
     * @param userGroup            The user group associated with the camp.
     * @param staffInCharge
     */
    public Camp(String campName, String location, String description, LocalDate startDate,
            LocalDate endDate, LocalDate registrationDeadline, int totalSlots,
            boolean isVisible, String userGroup, Staff staffInCharge) {
        this.campInfo = new CampInfo(campName, location, description, totalSlots, 10, isVisible);
        this.campDate = new CampDate(startDate, endDate, registrationDeadline);
        this.userGroup = userGroup;
        this.staffInCharge = staffInCharge;
    }

    public Staff getStaffInCharge() {
        return staffInCharge;
    }

    public void setStaffInCharge(Staff staff) {
        this.staffInCharge = staff;
    }

    /**
     * Retrieves the information about the camp.
     *
     * @return The {@code CampInfo} object containing details about the camp.
     */
    public CampInfo getCampInfo() {
        return campInfo;
    }

    /**
     * Retrieves the dates associated with the camp.
     *
     * @return The {@code CampDate} object containing start date, end date, and
     *         registration deadline.
     */
    public CampDate getCampDate() {
        return campDate;
    }

    /**
     * Retrieves the user group associated with the camp.
     *
     * @return The user group name.
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * Retrieves the set of enquiries associated with the camp.
     *
     * @return A {@code Set} containing the enquiries for the camp.
     */
    public Set<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * Retrieves the set of suggestions associated with the camp.
     *
     * @return A {@code Set} containing the suggestions for the camp.
     */
    public Set<Suggestion> getSuggestions() {
        return suggestions;
    }

    /**
     * Adds an enquiry to the set of enquiries for the camp.
     *
     * @param enquiry The {@code Enquiry} to be added.
     */
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    /**
     * Removes an enquiry from the set of enquiries for the camp.
     *
     * @param enquiry The {@code Enquiry} to be removed.
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    /**
     * Retrieves an array containing all the enquiries for the camp.
     *
     * @return An array of {@code Enquiry} objects.
     */
    public List<Enquiry> getEnquiriesArray() {
        List<Enquiry> enquiryList = new ArrayList<Enquiry>();
        for (Enquiry enquiry : enquiries) {
            enquiryList.add(enquiry);
        }
        return enquiryList;
    }

    /**
     * Adds a suggestion to the set of suggestions for the camp.
     *
     * @param suggestion The {@code Suggestion} to be added.
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    /**
     * Removes a suggestion from the set of suggestions for the camp.
     *
     * @param suggestion The {@code Suggestion} to be removed.
     */
    public void removeSuggestion(Suggestion suggestion) {
        suggestions.remove(suggestion);
    }

    /**
     * Retrieves an array containing all the suggestions for the camp.
     *
     * @return An array of {@code Suggestion} objects.
     */
    public List<Suggestion> getSuggestionsArray() {
        List<Suggestion> suggestionList = new ArrayList<Suggestion>();
        for (Suggestion suggestion : suggestions) {
            suggestionList.add(suggestion);
        }
        return suggestionList;
    }

    public void addCommittee(Student student) {
        committee.put(student, 0);
    }

    public Map<Student, Integer> getCommittee() {
        return committee;
    }

    public List<Student> getAttendees() {
        List<Student> attendeeList = new ArrayList<Student>();
        if (attendees == null)
            return attendeeList;
        else {
            for (Student attendee : attendees) {
                attendeeList.add(attendee);
            }
            return attendeeList;
        }
    }

    public void incrementCommitteePoint(Student student) {
        committee.put(student, committee.get(student) + 1);
    }
}