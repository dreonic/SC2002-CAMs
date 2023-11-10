package repliable;

import domain.Student;

public class Suggestion extends Repliable {
    private String content;
    private boolean isApproved;

    public Suggestion(String content, Student student) {
        super(student);
        this.content = content;
        isApproved = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
