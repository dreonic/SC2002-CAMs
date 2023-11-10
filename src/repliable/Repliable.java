package repliable;

import domain.Student;

public class Repliable {
    private Student student;

    public Repliable(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
