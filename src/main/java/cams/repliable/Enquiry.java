package cams.repliable;

import cams.domain.Student;

public class Enquiry extends Repliable {
    private String question;
    private String reply;

    public Enquiry(String question, Student student) {
        super(student);
        this.question = question;
        reply = null;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
