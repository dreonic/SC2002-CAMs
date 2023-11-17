package cams.domain;

import cams.camp.Camp;
import cams.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Staff extends User {
    private final Set<Camp> campsCreated;

    public Staff(String name, String userID, String faculty, String passwordHash) {
        super(name, userID, faculty, passwordHash);
        this.campsCreated = new HashSet<>();
    }

    public List<Camp> getCamps() {
        return new ArrayList<>(campsCreated);
    }

    public void addCamp(Camp camp) {
        campsCreated.add(camp);
    }
}