package cams.serializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cams.domain.Staff;
import cams.domain.Student;
import cams.user.User;
import cams.user.UserController;

public class UserSerializer {
    public static void deserialize(Map<String, User> userTable, String userType) {
        try (FileInputStream fileIn = new FileInputStream(
                "student".equals(userType) ? "src/data/student_list.xlsx" : "src/data/staff_list.xlsx");
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
                UserController.getInstance().addUser(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void serialize(Map<String, User> userTable, String userType) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("outputSheet");

            Row headerRow = sheet.createRow(0);
            List<String> header = List.of("Name", "Email", "Faculty", "Password");
            for (int i = 0; i < 4; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }

            int rowNum = 1;
            for (Map.Entry<String, User> entry : userTable.entrySet()) {
                User user = entry.getValue();

                if (("student".equals(userType) && user instanceof Student)
                        || ("staff".equals(userType) && user instanceof Staff)) {
                    Row row = sheet.createRow(rowNum);
                    for (int i = 0; i < 4; i++) {
                        Cell cell = row.createCell(i);
                        switch (i) {
                            case 0:
                                cell.setCellValue(user.getName());
                                break;
                            case 1:
                                cell.setCellValue(user.getUserID().concat("@e.ntu.edu.sg"));
                                break;
                            case 2:
                                cell.setCellValue(user.getFaculty());
                                break;
                            case 3:
                                cell.setCellValue(user.getHashedPassword());
                                break;
                            default:
                                break;
                        }
                    }
                    rowNum++;
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(
                    "student".equals(userType) ? "src/data/student_list.xlsx" : "src/data/staff_list.xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
