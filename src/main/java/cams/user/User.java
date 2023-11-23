package cams.user;

import java.util.List;
import java.util.Objects;

import cams.camp.Camp;

 /**
  * {@code User} entity is the base class for {@code Student} and
  * {@code Staff}, facilitating the storing of general {@code User}
  * attributes {@code userID}, {@code name}, {@code passwordHash},
  * and {@code faculty}.
  * 
  * @author Gillbert Susilo Wong
  * @author Juan Frederick
  * @author Karl Devlin Chau
  * @author Pascalis Pandey
  * @author Trang Nguyen
  * @version 1.0
  * @since 2023-11-23

  * @param name the name associated with a {@code User}
  * @param userID the user ID associated with a {@code User}
  * @param faculty the faculty associated with a {@code User}
  * @param passwordHash the password hashed with BCrypt associated with a {@code User}
  */

public abstract class User {
    private final String userID;
    private String name;
    private String passwordHash;
    private String faculty;

    /**
     * Class constructor for {@code User} class, initiating and formatting {@code User} attributes.
     * 
     * @param name
     * @param userID
     * @param faculty
     * @param passwordHash
     */
    public User(String name, String userID, String faculty, String passwordHash) {
        this.name = Objects.requireNonNull(name);
        if (userID.isBlank()) {
            throw new IllegalArgumentException("User ID must not be blank!");
        }
        this.userID = userID.toUpperCase();
        this.faculty = Objects.requireNonNull(faculty).toUpperCase();
        AuthController authController = AuthController.getInstance();
        this.passwordHash = passwordHash == null ? authController.getPasswordEncoder().encode(
                "password") : passwordHash;
    }

    /**
     * Gets a list of camps
     *
     * @return a list of camps
     */
    public abstract List<Camp> getCamps();

    /**
     * Adds a camp to the camps list
     * 
     * @param camp list of camps
     */
    public abstract void addCamp(Camp camp);
    
    /**
     * Removes a camp from the set of camps
     *
     * @param camp the camp to be added
     */
    public abstract void removeCamp(Camp camp);

    /**
     * Gets userID of the current {@code User}
     * 
     * @return the {@code userID} string of associated with a {@code User}
     */
    public String getUserID() {
        return userID;
    }
    
    /**
     * Gets the hashed password of the current {@code User}
     * 
     * @return the hashed password associated with a {@code User}
     */
    public String getHashedPassword() {
        return this.passwordHash;
    }
    
    /**
     * Sets the password of the current {@code User} hashed with BCrypt
     * 
     * @param hashedPassword the updated password associated with a {@code User}
     * hashed with BCrypt
     */
    void setHashedPassword(String hashedPassword) {
        this.passwordHash = hashedPassword;
    }

    /**
     * Gets the faculty of the current {@code User}
     * 
     * @return the faculty associated with a {@code User}
     */
    public String getFaculty() {
        return this.faculty;
    }

    /**
     * Sets the {@code faculty} of the current {@code User}
     * 
     * @param faculty the updated {@code faculty} associated with a {@code User}
     */
    public void setFaculty(String faculty) {
        this.faculty = Objects.requireNonNull(faculty).toUpperCase();
    }

    /**
     * Gets the {@code name} of the current {@code User}
     * 
     * @return the {@code name} associated with a {@code User}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the {@code name} of the current {@code User}
     * 
     * @param name the updated {@code name} associated with a {@code User}
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }
}
