package cams.domain;

import cams.camp.Camp;

public class StudentController {
    private static StudentController studentController;
    private Student student;

    private StudentController(Student student) {
        this.student = student;
    }

    public static StudentController getInstance(Student student) {
        if(studentController == null) {
            studentController = new StudentController(student);
        }
        return studentController;
    }

    public void register(Camp camp) {
        //also need to check for conflicting dates
        if(!student.getCamps().contains(camp)) {
            student.addCamp(camp);
        }
    }

    public void withdraw(Camp camp) {
        if(student.getCommitteeFor() != camp && student.getCamps().contains(camp)) {
            student.removeCamp(camp);
        }
    }

}