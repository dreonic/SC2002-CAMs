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

    public String getFaculty() {
        return this.faculty;
    }

    public String getName() {
        return this.name;
    }
}
