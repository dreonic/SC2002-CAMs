package cams.repliable;

import cams.domain.Student;

import java.util.List;

/**
 * The {@code RepliableEditorInterface} is the interface for {@code EnquiryEditor} and {@code SuggestionEditor}.
 * It provides the basic functionality (methods and attributes) that a repliable editor should have.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public interface RepliableEditorInterface {
    /**
     * Creates a new repliable.
     *
     * @param content the content of the repliable being created
     * @param student the student that created the repliable
     * @return the created repliable
     */
    Repliable create(String content, Student student);

    /**
     * Edits a repliable.
     *
     * @param repliable  the repliable that is being edited.
     * @param newContent the new content that replaces the repliable's old content.
     */
    void edit(Repliable repliable, String newContent);

    /**
     * Deletes a repliable.
     *
     * @param repliable the repliable that is being deleted.
     */
    void delete(Repliable repliable);

    /**
     * Replies to a repliable with a reply message.
     *
     * @param repliable    the repliable that is being replied to.
     * @param replyMessage the reply message.
     */
    void reply(Repliable repliable, Object replyMessage);

    /**
     * Views a list of repliable.
     *
     * @return list of enquiries.
     */
    List<Repliable> view();
}
