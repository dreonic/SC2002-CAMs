package cams.repliable;

import cams.domain.Student;

/**
 * The {@code Suggestion} represents a suggestion made by a camp committee member.
 * It inherits the basic functionalities of {@link Repliable}.
 * Each suggestion has a suggestion content and can be approved by the staff in charge of the camp.
 * The camp committee member is associated with this suggestion.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class Suggestion extends Repliable {
    /**
     * The content of the suggestion.
     */
    private String content;

    /**
     * Indicates if the suggestion is approved or not.
     */
    private boolean isApproved;

    /**
     * Constructs a new suggestion.
     *
     * @param content the content of the suggestion
     * @param student the camp committee member that created the suggestion
     */
    public Suggestion(String content, Student student) {
        super(student);
        this.content = content;
        isApproved = false;
    }

    /**
     * Gets the content of the suggestion.
     *
     * @return content of suggestion
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the suggestion.
     *
     * @param content the new content of the suggestion
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the approval of the suggestion.
     *
     * @return approval status of the suggestion
     */
    public boolean getIsApproved() {
        return isApproved;
    }

    /**
     * Sets the approval status of the suggestion.
     *
     * @param isApproved the new approval status of the suggestion
     */
    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
