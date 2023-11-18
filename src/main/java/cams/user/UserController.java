package cams.user;

import cams.serializer.UserSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private static UserController userController;
    private final Map<String, User> userTable;

    private UserController() {
        userTable = new HashMap<>();
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
            userController.initializeUserTable();
        }
        return userController;
    }

    private void initializeUserTable() {
        List<User> students = UserSerializer.deserialize("student", "student_list.xlsx", "staff_list.xlsx");
        List<User> staffs = UserSerializer.deserialize("staff", "student_list.xlsx", "staff_list.xlsx");

        for (User user : students)
            addUser(user);
        for (User user : staffs)
            addUser(user);
    }

    public static void close() {
        UserSerializer.serialize(userController.getUserTable(), "student", "student_list.xlsx", "staff_list.xlsx");
        UserSerializer.serialize(userController.getUserTable(), "staff", "student_list.xlsx", "staff_list.xlsx");
        userController = null;
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
