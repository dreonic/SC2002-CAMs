package cams.user;

import java.util.HashMap;

public class UserController {
    private static UserController userController;
    private HashMap<String, User> userTable = new HashMap<>();

    private UserController() {
        userTable = new HashMap<String, User>();
    }

    public static UserController getUserController() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public static void close() {
        UserController.userController = null;
    }

    public void addUser(User user) {
        //after deserialize
        userTable.put(user.getUserID(), user);
    }
}
