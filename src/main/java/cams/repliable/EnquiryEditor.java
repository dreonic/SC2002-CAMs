package cams.repliable;

import cams.camp.Camp;
import cams.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EnquiryEditor} class edits the respective enquiries of the {@code Camp} passed to it.
 * It implements {@code RepliableEditorInterface} interface, implementing methods to edit a repliable.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class EnquiryEditor implements RepliableEditorInterface {
    /**
     * The camp associated with the enquiries.
     */
    private final Camp camp;

    /**
     * Constructs a new enquiry editor for the specified camp.
     *
     * @param camp the camp where the enquiries are found.
     */
    public EnquiryEditor(Camp camp) {
        this.camp = camp;
    }

    /**
     * Creates a new {@code Enquiry} for the specified camp associated with the editor.
     *
     * @param content the content of the enquiry being created.
     * @param student the student that created the enquiry.
     */
    @Override
    public Repliable create(String content, Student student) {
        Enquiry enquiry = new Enquiry(content, student);
        camp.addEnquiry(enquiry);
        student.addEnquiry(enquiry);
        return enquiry;
    }

    /**
     * Edits an {@code Enquiry} with new content.
     *
     * @param repliable the enquiry being edited.
     * @param newContent the new content to replace the old content of the enquiry.
     */
    @Override
    public void edit(Repliable repliable, String newContent) {
        Enquiry enquiry = (Enquiry) repliable;
        if (enquiry.getReply().isBlank()) {
            enquiry.setQuestion(newContent);
        }
    }

    /**
     * Deletes an {@code Enquiry}.
     *
     * @param repliable the enquiry being deleted.
     */
    @Override
    public void delete(Repliable repliable) {
        Enquiry enquiry = (Enquiry) repliable;
        camp.removeEnquiry(enquiry);
    }

    /**
     * Answers an {@code Enquiry} with an answer.
     *
     * @param repliable the enquiry being replied to.
     * @param replyMessage the message which answers the enquiry.
     */
    @Override
    public void reply(Repliable repliable, Object replyMessage) {
        Enquiry enquiry = (Enquiry) repliable;
        String reply = (String) replyMessage;
        if (camp.getEnquiries().contains(enquiry)) {
            enquiry.setReply(reply);
        }
    }

    /**
     * Returns all the {@code Enquiry} in the specified camp.
     * 
     *  @return list of all enquiries in the camp.
     */
    @Override
    public List<Repliable> view() {
        return new ArrayList<>(camp.getEnquiries());
    }

}
