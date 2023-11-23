package cams.domain;

import cams.camp.Camp;
import cams.repliable.Enquiry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The StudentController control class manages the interaction between the system and a student user.
 * It provides methods for registering, withdrawing from camps, and handling the current student user.
 * It follows the Singleton pattern to ensure a single instance throughout the application.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class StudentController {
    /**
     * The sole instance of the StaffController class.
     */
    private static StudentController studentController;

    /**
     * The current student associated with the controller.
     */
    private Student student;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private StudentController() {
        student = null;
    }

    /**
     * Gets the singleton instance of StudentController.
     *
     * @return the singleton instance of StudentController
     */
    public static StudentController getInstance() {
        if (studentController == null) {
            studentController = new StudentController();
        }
        return studentController;
    }

    /**
     * Gets the singleton instance of StudentController with the specified student.
     *
     * @param student the student to associate with the controller
     * @return the singleton instance of StudentController
     */
    @SuppressWarnings("UnusedReturnValue")
    public static StudentController getInstance(Student student) {
        if (studentController == null) {
            studentController = new StudentController();
        }
        studentController.setCurrentStudent(student);
        return studentController;
    }

    /**
     * Closes the StudentController, releasing the current student association.
     */
    public static void close() {
        studentController = null;
    }

    /**
     * Gets the current student associated with the controller.
     *
     * @return the current student
     */
    public Student getCurrentStudent() {
        return student;
    }

    /**
     * Sets the current student associated with the controller.
     *
     * @param student the student to set as the current student
     */
    public void setCurrentStudent(Student student) {
        this.student = student;
    }

    /**
     * Registers the student for a camp, optionally as a committee member.
     *
     * @param camp        the camp to register for
     * @param isCommittee a boolean indicating whether the student registers as a committee member
     * @throws RuntimeException if there is a scheduling conflict or other registration issues
     */
    public void register(Camp camp, Boolean isCommittee) throws RuntimeException {
        for (Camp registeredCamp : student.getCamps()) {
            String registeredCampName = registeredCamp.getCampInfo().getCampName();
            LocalDate startDate1 = registeredCamp.getCampDate().getStartDate(),
                    startDate2 = camp.getCampDate().getStartDate();
            LocalDate endDate1 = registeredCamp.getCampDate().getEndDate(), endDate2 = camp.getCampDate().getEndDate();
            if (!(startDate1.isAfter(endDate2) || startDate2.isAfter(endDate1)) || startDate1.isEqual(endDate2) || startDate2.isEqual(endDate1)) {
                throw new RuntimeException("Failed to register, conflicting camp schedule with " + registeredCampName + "!");
            }
        }

        if (camp.getAttendees().contains(student)) {
            throw new RuntimeException("Failed to register, current student already registered for this camp!");
        }

        if(camp.getBlacklist().contains(student)) {
            throw new RuntimeException("Failed to register, current student already withdrew from this camp!");
        }

        if (isCommittee && student.getCommitteeFor() != null) {
            throw new RuntimeException(
                    "Cannot register as committee! Current student is already a committee for another camp!");
        }

        student.addCamp(camp);

        if (isCommittee) {
            if(camp.getCommittee().size() == 10) {
                throw new RuntimeException("There are no remaining committee slots!");
            }
            else {
                List<Enquiry> enquiriesList = new ArrayList<Enquiry>(camp.getEnquiries());
                for(Enquiry enquiry:enquiriesList) {
                    if(enquiry.getStudent() == student) {
                        camp.removeEnquiry(enquiry);
                        student.removeEnquiry(enquiry);
                    }
                }
                student.setCommitteeFor(camp);
                camp.addCommittee(student);
            }
        } 
        else {
            camp.addAttendee(student);
        }
    }

    /**
     * Withdraws the student from a camp.
     *
     * @param camp the camp to withdraw from
     * @throws RuntimeException if the student is a committee member or not registered for the camp
     */
    public void withdraw(Camp camp) {
        if (student.getCommitteeFor() == camp) {
            throw new RuntimeException("Committee members are not allowed to withdraw!");
        }
        if (!student.getCamps().contains(camp)) {
            throw new RuntimeException("Cannot withdraw! Student is not registered for this camp!");
        }
        camp.removeAttendee(student);
        student.removeCamp(camp);
    }
}