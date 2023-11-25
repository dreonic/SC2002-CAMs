package cams.repliable;

import cams.domain.Student;

/**
 * The {@code Repliable} is the base class for {@code Enquiry} and {@code Suggestion}.
 * It provides the basic functionality (methods and attributes) that a repliable should have.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class Repliable {
    /**
     * The question associated with the repliable.
     */
    private Student student;

    /**
     * Constructs a new repliable associated with a student.
     *
     * @param student the student that created the repliable.
     */
    public Repliable(Student student) {
        this.student = student;
    }

    /**
     * Gets the student that created the repliable.
     *
     * @return the student that created the repliable.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the student that created the repliable.
     *
     * @param student the student that created the repliable.
     */
    public void setStudent(Student student) {
        this.student = student;
    }
}
