package cams.user;

import java.util.Map;
import java.util.HashMap;

import cams.serializer.UserSerializer;

public class UserController {
    private static UserController userController;
    private Map<String, User> userTable;

    private UserController() {
        userTable = new HashMap<String, User>();
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
            UserSerializer.deserialize("student");
            UserSerializer.deserialize("staff");
        }
        return userController;
    }

    public static void close() {
        UserSerializer.serialize("student");
        UserSerializer.serialize("staff");
        userController = null;
    }

    public void addUser(User user) {
        userTable.put(user.getUserID(), user);
    }

    public User getUser(String userID) {
        return userTable.get(userID.toUpperCase());
    }

    public Map<String, User> getUserTable() {
        return userTable;
    }
}
