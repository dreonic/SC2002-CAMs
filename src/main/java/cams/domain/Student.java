package cams.domain;

import java.util.ArrayList;
import java.util.HashSet;

import cams.camp.Camp;
import cams.repliable.Enquiry;
import cams.user.User;

public class Student extends User {
    private Camp committeeFor;
    private HashSet<Camp> campsRegistered;
    private HashSet<Enquiry> enquiries;

    public Student(String name, String userID, String faculty, String passwordHash) {
        super(name, userID, faculty, passwordHash);
        this.campsRegistered = new HashSet<Camp>();
        this.enquiries = new HashSet<Enquiry>();
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