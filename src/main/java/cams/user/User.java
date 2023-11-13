package cams.user;

public class User {
    private String name;
    private String userID;
    private String passwordHash;
    private String faculty;

    public User(String name, String userID, String faculty, String passwordHash) {
        this.name = name;
        this.userID = userID;
        this.faculty = faculty;
        AuthController authController = AuthController.getInstance();
        this.passwordHash = passwordHash == null ? authController.getPasswordEncoder().encode("password") : passwordHash;
    }

    public String getUserID() {
        return userID;
    }

    public String getHashedPassword() {
        return this.passwordHash;
    }

    public void setHashedPassword(String hashedPassword) {
        this.passwordHash = hashedPassword;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
