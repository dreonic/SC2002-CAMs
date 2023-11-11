package cams.repliable;

import java.util.ArrayList;

import cams.camp.Camp;
import cams.domain.Student;

public class EnquiryEditor implements RepliableEditorInterface {
    private Camp camp;

    public EnquiryEditor(Camp camp) {
        this.camp = camp;
    }

    @Override
    public void create(String content, Student student) {
        Enquiry enquiry = new Enquiry(content, student);
        camp.addEnquiry(enquiry);
    }

    @Override
    public void edit(Repliable repliable, String newContent) {
        Enquiry enquiry = (Enquiry) repliable;
        if(enquiry.getReply() == "") {
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
        if(camp.getEnquiries().contains(enquiry)) {
            enquiry.setReply(reply);
        }
    }

    @Override
    public ArrayList<Repliable> view() {
        ArrayList<Repliable> enquiryList = new ArrayList<Repliable>();
        for(Enquiry enquiry:camp.getEnquiries()) {
            enquiryList.add(enquiry);
        }
        return enquiryList;
    }
}
