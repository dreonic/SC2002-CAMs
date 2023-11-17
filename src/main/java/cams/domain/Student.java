package cams.domain;

import cams.camp.Camp;
import cams.repliable.Enquiry;
import cams.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student extends User {
    private Camp committeeFor;
    private final Set<Camp> campsRegistered;
    private final Set<Enquiry> enquiries;

    public Student(String name, String userID, String faculty, String passwordHash) {
        super(name, userID, faculty, passwordHash);
        this.campsRegistered = new HashSet<>();
        this.enquiries = new HashSet<>();
    }

    public List<Camp> getCamps() {
        return new ArrayList<>(campsRegistered);
    }

    public void addCamp(Camp camp) {
        campsRegistered.add(camp);
    }

    public void removeCamp(Camp camp) {
        campsRegistered.remove(camp);
    }

    public List<Enquiry> getEnquiries() {
        return new ArrayList<>(enquiries);
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