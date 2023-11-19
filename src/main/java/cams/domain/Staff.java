package cams.domain;

import cams.camp.Camp;
import cams.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Staff entity class represents a staff member in the system, extending the User class.
 * Staff members have the ability to create and manage camps.
 */
public class Staff extends User {

    /**
     * The set of camps created by the staff member.
     */
    private final Set<Camp> campsCreated;

    /**
     * Constructs a Staff object with the specified details.
     *
     * @param name         The name of the staff member.
     * @param userID       The unique user identifier for the staff member.
     * @param faculty      The faculty to which the staff member belongs.
     * @param passwordHash The hashed password for authentication.
     */
    public Staff(String name, String userID, String faculty, String passwordHash) {
        super(name, userID, faculty, passwordHash);
        this.campsCreated = new HashSet<>();
    }

    /**
     * Returns a list of camps created by the staff member.
     *
     * @return A list of camps created by the staff member.
     */
    public List<Camp> getCamps() {
        return new ArrayList<>(campsCreated);
    }

    /**
     * Adds a camp to the set of camps created by the staff member.
     *
     * @param camp The camp to be added.
     */
    public void addCamp(Camp camp) {
        campsCreated.add(camp);
    }

    public void removeCamp(Camp camp) {
        campsCreated.remove(camp);
    }
;}
