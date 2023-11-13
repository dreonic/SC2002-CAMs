package cams.user;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cams.domain.Staff;
import cams.domain.Student;

public class UserController {
    private static UserController userController;
    private Map<String, User> userTable;

    private UserController() {
        userTable = new HashMap<String, User>();
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
            userController.deserialize("student", "src/data/student_list.xlsx");
            userController.deserialize("staff", "src/data/staff_list.xlsx");
        }
        return userController;
    }

    public static void close() {
        UserController.userController = null;
    }

    private void addUser(User user) {
        userTable.put(user.getUserID(), user);
    }

    public User getUser(String userID) {
        return userTable.get(userID);
    }

    private void deserialize(String userType, String file) {
        try (FileInputStream fileIn = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // Skip header
                if (row.getRowNum() == 0) {
                    continue;
                }

                // End if meet empty row
                boolean isRowEmpty = true;
                for (Cell cell : row) {
                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        isRowEmpty = false;
                        break;
                    }
                }
                if (isRowEmpty) {
                    break;
                }

                List<String> args = new ArrayList<String>();
                for (Cell cell : row) {
                    args.add(cell.toString());
                }
                User user = ("student".equals(userType))
                        ? new Student(args.get(0), args.get(1).substring(0, args.get(1).indexOf('@')), args.get(2),
                                (args.size() == 4) ? args.get(3) : null)
                        : new Staff(args.get(0), args.get(1).substring(0, args.get(1).indexOf('@')), args.get(2),
                                (args.size() == 4) ? args.get(3) : null);
                addUser(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
