package cams.user;

import cams.serializer.UserSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The UserController control is responsible for facilitating access to {@link User}
 * credentials. It stores user table and staff table.
 * <p>
 * It follows the Singleton pattern to ensure a single instance throughout the
 * application.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */

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

    /**
     * Gets the singleton instance of {@code UserController} and initiates student and staff list
     * from list file
     *
     * @return the singleton instance of the {@code UserController}
     */
    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController("student_list.xlsx", "staff_list.xlsx");
        }
        return userController;
    }

    /**
     * Gets the singleton instance of {@code UserController} and initiates student and staff list
     * from passed parameters
     *
     * @param studentPath the path to the Excel file containing student information
     * @param staffPath   the path to the Excel file containing staff information
     * @return the singleton instance of the {@code UserController}
     */
    public static UserController getInstance(String studentPath, String staffPath) {
        if (userController == null) {
            userController = new UserController(studentPath, staffPath);
        }
        return userController;
    }

    /**
     * Closes the {@code UserController}, updating the user and staff list file
     */
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

    /**
     * Adds a {@code User} object to the corresponding table
     *
     * @param user the user object associated with a {@code User}
     * @throws IllegalArgumentException when a {@code User} with the same {@code userID}
     *                                  already exists
     */
    public void addUser(User user) throws IllegalArgumentException {
        if (userTable.putIfAbsent(user.getUserID(), user) != null)
            throw new IllegalArgumentException("User with the same userID already exists!");
    }

    /**
     * Gets the {@code User} object corresponding to a {@code userID}
     *
     * @param userID the userID associated with a {@code User}
     * @return the {@code User} object corresponding to a {@code userID}
     */
    public User getUser(String userID) {
        return userTable.get(userID.toUpperCase());
    }

    /**
     * Gets the {@code User} table containing hash map of users
     *
     * @return {@code User} table containing hash map of users
     */
    public Map<String, User> getUserTable() {
        return new HashMap<>(userTable);
    }
}
