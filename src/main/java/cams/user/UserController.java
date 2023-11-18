package cams.user;

import cams.serializer.UserSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private static UserController userController;
    private final Map<String, User> userTable;
    private final String studentPath;
    private final String staffPath;

    private UserController(String studentPath, String staffPath) {
        userTable = new HashMap<>();
        this.studentPath = studentPath;
        this.staffPath = staffPath;
        initializeUserTable();
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController("student_list.xlsx", "staff_list.xlsx");
        }
        return userController;
    }

    public static UserController getInstance(String studentPath, String staffPath) {
        if (userController == null) {
            userController = new UserController(studentPath, staffPath);
        }
        return userController;
    }

    public static void close() {
        UserSerializer.serialize(userController.getUserTable(), "student", userController.studentPath, userController.staffPath);
        UserSerializer.serialize(userController.getUserTable(), "staff", userController.studentPath, userController.staffPath);
        userController = null;
    }

    private void initializeUserTable() {
        List<User> students = UserSerializer.deserialize("student", studentPath, staffPath);
        List<User> staffs = UserSerializer.deserialize("staff", studentPath, staffPath);

        for (User user : students)
            addUser(user);
        for (User user : staffs)
            addUser(user);
    }

    public void addUser(User user) throws IllegalArgumentException {
        if (userTable.putIfAbsent(user.getUserID(), user) != null)
            throw new IllegalArgumentException("User with the same userID already exists!");
    }

    public User getUser(String userID) {
        return userTable.get(userID.toUpperCase());
    }

    public Map<String, User> getUserTable() {
        return new HashMap<>(userTable);
    }
}
