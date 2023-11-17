package cams.domain;

import java.time.LocalDate;

import cams.camp.Camp;

public class StudentController {
    private static StudentController studentController;
    private Student student;

    private StudentController() {
        student = null;
    }

    public static StudentController getInstance() {
        if (studentController == null) {
            studentController = new StudentController();
        }
        return studentController;
    }

    public static StudentController getInstance(Student student) {
        if (studentController == null) {
            studentController = new StudentController();
        }
        studentController.setCurrentStudent(student);
        return studentController;
    }

    public Student getCurrentStudent() {
        return student;
    }

    public void setCurrentStudent(Student student) {
        this.student = student;
    }

    public void register(Camp camp, Boolean isCommittee) throws RuntimeException {
        for (Camp registeredCamp : student.getCamps()) {
            String registeredCampName = registeredCamp.getCampInfo().getCampName();
            LocalDate startDate1 = registeredCamp.getCampDate().getStartDate(),
                    startDate2 = camp.getCampDate().getStartDate();
            LocalDate endDate1 = registeredCamp.getCampDate().getEndDate(), endDate2 = camp.getCampDate().getEndDate();
            if (!(startDate1.isAfter(endDate2) || startDate2.isAfter(endDate1))) {
                throw new RuntimeException("Conflicting camp schedule with" + registeredCampName + "!");
            }
        }

        if (student.getCamps().contains(camp)) {
            throw new RuntimeException("Current student already registered for this camp!");
        }

        student.addCamp(camp);
        if (!isCommittee)
            return;

        if (student.getCommitteeFor() != null) {
            throw new RuntimeException(
                    "Cannot register as committee! Current student is already a committee for another camp!");
        }
        student.setCommitteeFor(camp);
    }

    public void withdraw(Camp camp) {
        if (student.getCommitteeFor() == camp) {
            throw new RuntimeException("Student committees are not allowed to withdraw!");
        }
        if (!student.getCamps().contains(camp)) {
            throw new RuntimeException("Cannot withdraw! Student is not registered for this camp!");
        }
        student.removeCamp(camp);
    }

    public static void close() {
        studentController = null;
    }
}