package cams.user;

import java.util.HashMap;

public class UserController {
    private static UserController userController;
    private HashMap<String, User> userTable = new HashMap<>();

    private UserController() {
        userTable = new HashMap<String, User>();
        userTable.put("gillbert001", new User("gillbert001", "SCSE"));
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public static void close() {
        UserController.userController = null;
    }

    public void addUser(User user) {
        // after deserialize
        userTable.put(user.getUserID(), user);
    }

    User getUser(String userID) {
        return userTable.get(userID);
    }
}
