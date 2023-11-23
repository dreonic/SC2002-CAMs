package cams.user;

import java.util.Objects;

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

  * @param name
  * @param userID
  * @param faculty
  * @param passwordHash
  */

public class User {
    private final String userID;
    private String name;
    private String passwordHash;
    private String faculty;

    public User(String name, String userID, String faculty, String passwordHash) {
        /**
         * Class constructor for {@code User} class, initiating and formatting
         * {@code User} attributes.
         */
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

    public String getUserID() {
        /**
         * Gets userID of the current {@code User}
         * 
         * @return the UserID string of associated with a {@code User}
         */
        return userID;
    }

    public String getHashedPassword() {
        /**
         * Gets the hashed password of the current {@code User}
         * 
         * @return the hashed password associated with a {@code User}
         */
        return this.passwordHash;
    }

    void setHashedPassword(String hashedPassword) {
        /**
         * Sets the password of the current {@code User} hashed with BCrypt
         */
        this.passwordHash = hashedPassword;
    }

    public String getFaculty() {
        /**
         * Gets the faculty of the current {@code User}
         * 
         * @return the faculty associated with a {@code User}
         */
        return this.faculty;
    }

    public void setFaculty(String faculty) {
        /**
         * Sets the faculty of the current {@code User}
         */
        this.faculty = Objects.requireNonNull(faculty).toUpperCase();
    }

    public String getName() {
        /**
         * Gets the name of the current {@code User}
         * 
         * @return the name associated with a {@code User}
         */
        return this.name;
    }

    public void setName(String name) {
        /**
         * Sets the name of the current {@code User}
         */
        this.name = Objects.requireNonNull(name);
    }
}
