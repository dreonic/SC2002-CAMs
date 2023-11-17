package cams.repliable;

import cams.camp.Camp;
import cams.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class EnquiryEditor implements RepliableEditorInterface {
    private final Camp camp;

    public EnquiryEditor(Camp camp) {
        this.camp = camp;
    }

    @Override
    public Repliable create(String content, Student student) {
        Enquiry enquiry = new Enquiry(content, student);
        camp.addEnquiry(enquiry);
        return enquiry;
    }

    @Override
    public void edit(Repliable repliable, String newContent) {
        Enquiry enquiry = (Enquiry) repliable;
        if (enquiry.getReply() == null) {
            enquiry.setQuestion(newContent);
        }
    }

    @Override
    public void delete(Repliable repliable) {
        Enquiry enquiry = (Enquiry) repliable;
        camp.removeEnquiry(enquiry);
    }

    @Override
    public void reply(Repliable repliable, Object replyMessage) {
        Enquiry enquiry = (Enquiry) repliable;
        String reply = (String) replyMessage;
        if (camp.getEnquiries().contains(enquiry)) {
            enquiry.setReply(reply);
        }
    }

    @Override
    public List<Repliable> view() {
        return new ArrayList<>(camp.getEnquiries());
    }

}
