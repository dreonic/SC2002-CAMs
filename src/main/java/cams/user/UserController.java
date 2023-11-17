package cams.user;

import cams.serializer.UserSerializer;

import java.util.HashMap;
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
