package cams.domain;

import java.util.HashSet;
import java.util.Set;

import cams.camp.Camp;
import cams.user.User;

public class Staff extends User {
    private Set<Camp> campsCreated;

    public Staff(String userID, String faculty) {
        super(userID, faculty);
    }

    public Set<Camp> getCamps() {
        return new HashSet<Camp>(campsCreated);
    }

    public void addCamp(Camp camp) {
        // TODO
    }
}