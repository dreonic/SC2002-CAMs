package user;

public class User {
    private String userID;
    private String hashedPassword;
    private String faculty;
    private String name;

    public User(String userID, String faculty) {
        this.userID = userID;
        this.faculty = faculty;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
