package domain;

import java.util.ArrayList;
import java.util.Set;

import camp.Camp;
import user.User;

public class Staff extends User{
    private Set<Camp> campsCreated;
    
    public Staff(String userID, String faculty) {
        super(userID, faculty);
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
