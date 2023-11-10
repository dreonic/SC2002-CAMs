package cams.domain;

import cams.camp.Camp;

public class StudentController {
    private Student student;

    public StudentController(Student student) {
        this.student = student;
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
