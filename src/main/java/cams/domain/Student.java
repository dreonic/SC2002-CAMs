package cams.domain;

import java.util.ArrayList;
import java.util.Set;

import cams.camp.Camp;
import cams.repliable.Enquiry;
import cams.user.User;

public class Student extends User {
    private Camp committeeFor;
    private Set<Camp> campsRegistered;
    private Set<Enquiry> enquiries;

    public Student(String userID, String faculty) {
        super(userID, faculty);
    }

    public ArrayList<Camp> getCamps() {
        ArrayList<Camp> camps = new ArrayList<Camp>();
        for(Camp camp:campsRegistered) {
            camps.add(camp);
        }
        return camps;
    }

    public void addCamp(Camp camp) {
        campsRegistered.add(camp);
    }

    public void removeCamp(Camp camp) {
        campsRegistered.remove(camp);
    }

    public ArrayList<Enquiry> getEnquiries() {
        ArrayList<Enquiry> enquiriesList = new ArrayList<Enquiry>();
        for(Enquiry enquiry:enquiries) {
            enquiriesList.add(enquiry);
        } 
        return enquiriesList;
    }

    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public Camp getCommitteeFor() {
        return committeeFor;
    }

    public void setCommitteeFor(Camp camp) {
        committeeFor = camp;
    }
}