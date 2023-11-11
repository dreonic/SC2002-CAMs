package cams.user;

public class User {
    private String userID;
    private String passwordHash;
    private String faculty;
    private String email;

    public User(String userID, String email, String faculty, String passwordHash) {
        this.userID = userID;
        this.faculty = faculty;
        this.email = email;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
