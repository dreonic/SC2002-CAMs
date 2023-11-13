package cams.domain;

import java.util.ArrayList;
import java.util.HashSet;

import cams.camp.Camp;
import cams.user.User;

public class Staff extends User{
    private HashSet<Camp> campsCreated;
    
    public Staff(String name, String userID, String faculty, String passwordHash) {
        super(name, userID, faculty, passwordHash);
        this.campsCreated = new HashSet<Camp>();
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