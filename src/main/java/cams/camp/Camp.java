package cams.camp;

import cams.domain.Staff;
import cams.domain.Student;
import cams.repliable.Enquiry;
import cams.repliable.Suggestion;

import java.time.LocalDate;
import java.util.*;

/**
 * Main entity class representing a camp.
 * <p>
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
    private final CampInfo campInfo;
    private final CampDate campDate;
    private final String userGroup;
    private final Staff staffInCharge;
    private final Set<Enquiry> enquiries;
    private final Set<Suggestion> suggestions;
    private final Set<Student> attendees;
    private final Map<Student, Integer> committee;

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
     * @param staffInCharge        The staff in charge of this camp.
     */
    public Camp(
            String campName, String location, String description,
            LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline,
            int totalSlots, boolean isVisible, String userGroup, Staff staffInCharge)
            throws IllegalArgumentException {
        this.campInfo = new CampInfo(campName, location, description, totalSlots, 10, isVisible);
        this.campDate = new CampDate(startDate, endDate, registrationDeadline);
        if (userGroup.isBlank()) {
            throw new IllegalArgumentException("User group must not be blank!");
        }
        this.userGroup = userGroup.toUpperCase();
        this.staffInCharge = Objects.requireNonNull(staffInCharge, "A staff must be in charge of this camp!");
        this.enquiries = new HashSet<>();
        this.suggestions = new HashSet<>();
        this.attendees = new HashSet<>();
        this.committee = new HashMap<>();
    }

    public Staff getStaffInCharge() {
        return staffInCharge;
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
     * registration deadline.
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
        return new HashSet<>(enquiries);
    }

    /**
     * Retrieves the set of suggestions associated with the camp.
     *
     * @return A {@code Set} containing the suggestions for the camp.
     */
    public Set<Suggestion> getSuggestions() {
        return new HashSet<>(suggestions);
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

    public void addCommittee(Student student) {
        committee.put(student, 0);
    }

    public Map<Student, Integer> getCommittee() {
        return new HashMap<>(committee);
    }

    public List<Student> getAttendees() {
        if(attendees == null)
            return new ArrayList<Student>();
        return new ArrayList<Student>(attendees);
    }

    public void incrementCommitteePoint(Student student) throws IllegalArgumentException {
        if (committee.get(student) == null)
            throw new IllegalArgumentException("Student is not a committee for this camp!");
        committee.put(student, committee.get(student) + 1);
    }

    public void addAttendee(Student student) {
        attendees.add(student);
    }

    public void removeAttendee(Student student) {
        attendees.remove(student);
    }
}