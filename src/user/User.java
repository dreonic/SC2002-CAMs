package user;

public class User {
    private String userID;
    private String passwordHash;
    private String faculty;
    private String name;

    public User(String userID, String faculty) {
        this.userID = userID;
        this.faculty = faculty;
        // TODO: set default passwordHash to the hash value of "password"
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
