package cams.domain;

import cams.camp.Camp;

import java.time.LocalDate;

/**
 * The StudentController class manages the interaction between the system and a student user.
 * It provides methods for registering, withdrawing from camps, and handling the current student user.
 */
public class StudentController {
    private static StudentController studentController;
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
     * @return The singleton instance of StudentController.
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
     * @param student The student to associate with the controller.
     * @return The singleton instance of StudentController.
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
     * @return The current student.
     */
    public Student getCurrentStudent() {
        return student;
    }

    /**
     * Sets the current student associated with the controller.
     *
     * @param student The student to set as the current student.
     */
    public void setCurrentStudent(Student student) {
        this.student = student;
    }

    /**
     * Registers the student for a camp, optionally as a committee member.
     *
     * @param camp        The camp to register for.
     * @param isCommittee A boolean indicating whether the student registers as a committee member.
     * @throws RuntimeException If there is a scheduling conflict or other registration issues.
     */
    public void register(Camp camp, Boolean isCommittee) throws RuntimeException {
        for (Camp registeredCamp : student.getCamps()) {
            String registeredCampName = registeredCamp.getCampInfo().getCampName();
            LocalDate startDate1 = registeredCamp.getCampDate().getStartDate(),
                    startDate2 = camp.getCampDate().getStartDate();
            LocalDate endDate1 = registeredCamp.getCampDate().getEndDate(), endDate2 = camp.getCampDate().getEndDate();
            if (!(startDate1.isAfter(endDate2) || startDate2.isAfter(endDate1)) || startDate1.isEqual(endDate2) || startDate2.isEqual(endDate1)) {
                throw new RuntimeException("Conflicting camp schedule with " + registeredCampName + "!");
            }
        }

        if (student.getCamps().contains(camp)) {
            throw new RuntimeException("Current student already registered for this camp!");
        }

        if (isCommittee && student.getCommitteeFor() != null) {
            throw new RuntimeException(
                    "Cannot register as committee! Current student is already a committee for another camp!");
        }

        student.addCamp(camp);
        if (isCommittee) {
            student.setCommitteeFor(camp);
            camp.addCommittee(student);
        } else {
            camp.addAttendee(student);
        }
    }

    /**
     * Withdraws the student from a camp.
     *
     * @param camp The camp to withdraw from.
     * @throws RuntimeException If the student is a committee member or not registered for the camp.
     */
    public void withdraw(Camp camp) {
        if (student.getCommitteeFor() == camp) {
            throw new RuntimeException("Student committees are not allowed to withdraw!");
        }
        if (!student.getCamps().contains(camp)) {
            throw new RuntimeException("Cannot withdraw! Student is not registered for this camp!");
        }
        student.removeCamp(camp);
    }
}