package cams.repliable;

import cams.domain.Student;

/**
 * The {@code Enquiry} class represents an inquiry made by a student.
 * It extends the {@code Repliable} class, inheriting functionality related to replies.
 * Each enquiry has a question and can have a corresponding reply.
 * The student making the inquiry is associated with this enquiry.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class Enquiry extends Repliable {
    /**
     * The question associated with this enquiry.
     */
    private String question;

    /**
     * The reply to the enquiry.
     */
    private String reply;

    /**
     * Constructs a new Enquiry object with the specified question and student.
     *
     * @param question The question associated with this enquiry.
     * @param student  The student making the enquiry.
     */
    public Enquiry(String question, Student student) {
        super(student);
        this.question = question;
        this.reply = "";
    }

    /**
     * Gets the question associated with this enquiry.
     *
     * @return The question of this enquiry.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question associated with this enquiry.
     *
     * @param question The new question for this enquiry.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the reply to this enquiry.
     *
     * @return The reply to this enquiry.
     */
    public String getReply() {
        return reply;
    }

    /**
     * Sets the reply to this enquiry.
     *
     * @param reply The new reply for this enquiry.
     */
    public void setReply(String reply) {
        this.reply = reply;
    }
}
