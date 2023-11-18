package cams.user;

import java.util.Objects;

public class User {
    private final String userID;
    private String name;
    private String passwordHash;
    private String faculty;

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

    public String getUserID() {
        return userID;
    }

    public String getHashedPassword() {
        return this.passwordHash;
    }

    void setHashedPassword(String hashedPassword) {
        this.passwordHash = hashedPassword;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = Objects.requireNonNull(faculty).toUpperCase();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }
}
