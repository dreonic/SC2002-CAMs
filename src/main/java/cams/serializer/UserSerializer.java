package cams.serializer;

import cams.domain.Staff;
import cams.domain.Student;
import cams.user.User;
import cams.user.UserController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code UserSerializer} class provides methods for serializing and deserializing user information,
 * including both students and staff, into and from Excel files. The class uses the Apache POI library
 * for Excel handling.
 *
 * <p>Users can be of two types: {@code Student} or {@code Staff}. The class supports the serialization and deserialization
 * of these users based on the specified user type.</p>
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class UserSerializer {
    /**
     * Deserializes user information from specified Excel files based on the specified user type.
     * The deserialized user information is then added to the {@link UserController} instance.
     *
     * @param userType    the type of user to deserialize "student" or "staff"
     * @param studentPath the path to the Excel file containing student information
     * @param staffPath   the path to the Excel file containing staff information
     * @return the list of users (students or staff) deserialized from the Excel file
     */
    public static List<User> deserialize(String userType, String studentPath, String staffPath) {
        String path = "student".equals(userType) ? studentPath : staffPath;
        List<User> result = new ArrayList<>();

        try (FileInputStream fileToCheck = new FileInputStream(path)) {
        } catch (FileNotFoundException e) {
            try (InputStream inStream = UserSerializer.class.getClassLoader().getResourceAsStream(path)) {
                Path initialListFile = Paths.get(path);
                Files.copy(Objects.requireNonNull(inStream), initialListFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ignored) {
            }
        } catch (IOException ignored) {
        }

        try (FileInputStream fileIn = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

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

                List<String> args = new ArrayList<>();
                for (Cell cell : row) {
                    args.add(cell.toString());
                }
                User user = ("student".equals(userType))
                        ? new Student(args.get(0), args.get(1).substring(0, args.get(1).indexOf('@')), args.get(2),
                        (args.size() == 4) ? args.get(3) : null)
                        : new Staff(args.get(0), args.get(1).substring(0, args.get(1).indexOf('@')), args.get(2),
                        (args.size() == 4) ? args.get(3) : null);
                result.add(user);
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    /**
     * Serializes user information of the specified type {@code Student} or {@code Staff} into specified Excel files.
     * The generated Excel file includes details such as name, email, faculty, and hashed password.
     *
     * @param userTable   the user table input to serialize into the Excel file
     * @param userType    the type of user to serialize "student" or "staff"
     * @param studentPath the path to the Excel file for storing student information
     * @param staffPath   the path to the Excel file for storing staff information
     */
    public static void serialize(Map<String, User> userTable, String userType, String studentPath, String staffPath) {
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
                    "student".equals(userType) ? studentPath : staffPath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
