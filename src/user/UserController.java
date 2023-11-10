package user;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static UserController userController;
    private Map<String, User> userTable;

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
        userTable.put(user.getUserID(), user);
    }
}
