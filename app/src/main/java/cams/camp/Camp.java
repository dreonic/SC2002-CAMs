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
 * This class encapsulates information about a camp. Date information is encapsulated in
 * {@code campDate}, while editable info is encapsulated in {@code campInfo}.
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
    private final Set<Student> blacklist;
    private final Map<Student, Integer> committee;

    /**
     * Constructs a new camp with the specified details.
     *
     * @param campName             the name of the camp
     * @param location             the location of the camp
     * @param description          the description of the camp
     * @param startDate            the start date of the camp
     * @param endDate              the end date of the camp
     * @param registrationDeadline the registration deadline for the camp
     * @param totalSlots           the total available slots for the camp
     * @param isVisible            indicates whether the camp is visible
     * @param userGroup            the user group associated with the camp
     * @param staffInCharge        the staff in charge of this camp
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
        this.blacklist = new HashSet<>();
        this.committee = new HashMap<>();
    }

    /**
     * Retrieves the staff in charge of this camp.
     *
     * @return the staff in charge of this camp
     */
    public Staff getStaffInCharge() {
        return staffInCharge;
    }

    /**
     * Retrieves the information about the camp.
     *
     * @return the {@link CampInfo} object containing details about the camp
     */
    public CampInfo getCampInfo() {
        return campInfo;
    }

    /**
     * Retrieves the dates associated with the camp.
     *
     * @return the {@link CampDate} object containing start date, end date, and registration deadline
     */
    public CampDate getCampDate() {
        return campDate;
    }

    /**
     * Retrieves the user group associated with the camp.
     *
     * @return the user group name
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * Retrieves the set of enquiries associated with the camp.
     *
     * @return a {@code Set} containing the enquiries for the camp
     */
    public Set<Enquiry> getEnquiries() {
        return new HashSet<>(enquiries);
    }

    /**
     * Retrieves the set of suggestions associated with the camp.
     *
     * @return a {@code Set} containing the suggestions for the camp
     */
    public Set<Suggestion> getSuggestions() {
        return new HashSet<>(suggestions);
    }

    /**
     * Adds an enquiry to the set of enquiries for the camp.
     *
     * @param enquiry the {@link Enquiry} to be added
     */
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    /**
     * Removes an enquiry from the set of enquiries for the camp.
     *
     * @param enquiry the {@code Enquiry} to be removed
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    /**
     * Adds a suggestion to the set of suggestions for the camp.
     *
     * @param suggestion the {@link Suggestion} to be added
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    /**
     * Removes a suggestion from the set of suggestions for the camp.
     *
     * @param suggestion the {@code Suggestion} to be removed
     */
    public void removeSuggestion(Suggestion suggestion) {
        suggestions.remove(suggestion);
    }

    /**
     * Adds a new committee member for the camp.
     *
     * @param student the {@link Student} to be added as committee
     */
    public void addCommittee(Student student) {
        committee.put(student, 0);
    }

    /**
     * Adds a new committee member for the camp with a given starting point.
     *
     * @param student the {@code Student} to be added as committee
     * @param points  the {@code Student} the base points given to the committee
     */
    public void addCommittee(Student student, int points) {
        committee.put(student, points);
    }

    /**
     * Gets the committee list of the camp.
     *
     * @return the committee list of the camp
     */
    public Map<Student, Integer> getCommittee() {
        return this.committee;
    }

    /**
     * Gets the list of attendees for this camp.
     *
     * @return the list of attendees, or an empty list if there are none
     */
    public List<Student> getAttendees() {
        if (attendees == null)
            return new ArrayList<>();
        return new ArrayList<>(attendees);
    }

    /**
     * Increments the committee point for the specified student.
     *
     * @param student the student whose committee point needs to be incremented
     * @throws IllegalArgumentException if the student is not a committee member for
     *                                  this camp
     */
    public void incrementCommitteePoint(Student student) throws IllegalArgumentException {
        if (committee.get(student) == null)
            throw new IllegalArgumentException("Student is not a committee for this camp!");
        committee.put(student, committee.get(student) + 1);
    }

    /**
     * Adds a student to the list of attendees for this camp.
     *
     * @param student the student to be added to the attendees list
     */
    public void addAttendee(Student student) {
        attendees.add(student);
    }

    /**
     * Removes a student from the list of attendees for this camp and adds them to
     * the blacklist.
     *
     * @param student the student to be removed from the attendees list and added to
     *                the blacklist
     */
    public void removeAttendee(Student student) {
        attendees.remove(student);
        blacklist.add(student);
    }

    /**
     * Gets the list of blacklisted students for this camp.
     *
     * @return a list of blacklisted students, or an empty list if there are none
     */
    public List<Student> getBlacklist() {
        if (blacklist == null)
            return new ArrayList<>();
        return new ArrayList<>(blacklist);
    }

    /**
     * Adds a student to the blacklist for this camp.
     *
     * @param student the student to be added to the blacklist
     */
    public void addBlacklist(Student student) {
        blacklist.add(student);
    }

}