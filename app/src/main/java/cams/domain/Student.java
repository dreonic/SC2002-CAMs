package cams.domain;

import cams.camp.Camp;
import cams.repliable.Enquiry;
import cams.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code Student} entity class represents a student user in the system. It extends the {@link User }
 * class and includes additional attributes related to camp registration and enquiries.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class Student extends User {
    /**
     * The set of camps registered by the student.
     */
    private final Set<Camp> campsRegistered;
    /**
     * The set of enquiries made by the student.
     */
    private final Set<Enquiry> enquiries;
    /**
     * The camp for which the student serves as a committee member.
     */
    private Camp committeeFor;

    /**
     * Constructs a new Student object with the specified attributes.
     *
     * @param name         the name of the student
     * @param userID       the unique user ID of the student
     * @param faculty      the faculty to which the student belongs
     * @param passwordHash the hashed password of the student
     */
    public Student(String name, String userID, String faculty, String passwordHash) {
        super(name, userID, faculty, passwordHash);
        this.campsRegistered = new HashSet<>();
        this.enquiries = new HashSet<>();
    }

    /**
     * Gets a list of camps registered by the student.
     *
     * @return a list of camps registered by the student
     */
    public List<Camp> getCamps() {
        return new ArrayList<>(campsRegistered);
    }

    /**
     * Adds a camp to the set of camps registered by the student.
     *
     * @param camp the camp to be added
     */
    public void addCamp(Camp camp) {
        campsRegistered.add(camp);
    }

    /**
     * Removes a camp from the set of camps registered by the student.
     *
     * @param camp the camp to be removed
     */
    public void removeCamp(Camp camp) {
        campsRegistered.remove(camp);
    }

    /**
     * Gets a list of enquiries made by the student.
     *
     * @return a list of enquiries made by the student
     */
    public List<Enquiry> getEnquiries() {
        return new ArrayList<>(enquiries);
    }

    /**
     * Adds an enquiry to the set of enquiries made by the student.
     *
     * @param enquiry the enquiry to be added
     */
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    /**
     * Removes an enquiry made by the student.
     *
     * @param enquiry the enquiry to be removed
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    /**
     * Gets the camp for which the student serves as a committee member.
     *
     * @return the camp for which the student serves as a committee member
     */
    public Camp getCommitteeFor() {
        return committeeFor;
    }

    /**
     * Sets the camp for which the student serves as a committee member.
     *
     * @param camp the camp for which the student serves as a committee member
     */
    public void setCommitteeFor(Camp camp) {
        committeeFor = camp;
    }
}
