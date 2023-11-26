package cams.domain;

import cams.camp.Camp;
import cams.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code Staff} entity class represents a staff member in the system, extending the {@link User}
 * class. Staff members have the ability to create and manage camps.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class Staff extends User {

    /**
     * The set of camps created by the staff member.
     */
    private final Set<Camp> campsCreated;

    /**
     * Constructs a Staff object with the specified details.
     *
     * @param name         the name of the staff member
     * @param userID       the unique user identifier for the staff member
     * @param faculty      the faculty to which the staff member belongs
     * @param passwordHash the hashed password for authentication
     */
    public Staff(String name, String userID, String faculty, String passwordHash) {
        super(name, userID, faculty, passwordHash);
        this.campsCreated = new HashSet<>();
    }

    /**
     * Gets a list of camps created by the staff member.
     *
     * @return a list of camps created by the staff member
     */
    public List<Camp> getCamps() {
        return new ArrayList<>(campsCreated);
    }

    /**
     * Adds a camp to the set of camps created by the staff member.
     *
     * @param camp the camp to be added
     */
    public void addCamp(Camp camp) {
        campsCreated.add(camp);
    }

    /**
     * Removes a camp from the set of camps created by the staff member.
     *
     * @param camp the camp to be removed
     */
    public void removeCamp(Camp camp) {
        campsCreated.remove(camp);
    }

    ;
}
