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

    public Student getCurrentStudent() {
        return student;
    }

    public void setCurrentStudent(Student student) {
        this.student = student;
    }

    public void register(Camp camp, Boolean isCommittee) {
        //also need to check for conflicting dates
        if(!student.getCamps().contains(camp) && isCommittee == false) {
            student.addCamp(camp);
        }
        else if(!student.getCamps().contains(camp) && isCommittee == true && student.getCommitteeFor() == null) {
            student.addCamp(camp);
            student.setCommitteeFor(camp);
        }
    }

    public void withdraw(Camp camp) {
        if(student.getCommitteeFor() != camp && student.getCamps().contains(camp)) {
            student.removeCamp(camp);
        }
    }

}