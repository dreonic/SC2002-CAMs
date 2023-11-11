package cams.domain;

import java.util.ArrayList;
import java.util.Set;

import cams.camp.Camp;
import cams.user.User;

public class Staff extends User{
    private Set<Camp> campsCreated;
    
    public Staff(String userID, String email, String faculty, String passwordHash) {
        super(userID, email, faculty, passwordHash);
    }

    public ArrayList<Camp> getCamps() {
        ArrayList<Camp> camps = new ArrayList<Camp>();
        for(Camp camp:campsCreated) {
            camps.add(camp);
        } 
        return camps;
    }

    public void addCamp(Camp camp) {
        campsCreated.add(camp);
    }
}