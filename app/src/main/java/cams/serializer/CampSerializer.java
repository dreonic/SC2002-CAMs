package cams.serializer;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampDate;
import cams.camp.CampInfo;
import cams.domain.Staff;
import cams.domain.Student;
import cams.user.UserController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code CampSerializer} class provides methods for serializing and
 * deserializing
 * camp information to and from an Excel file. It uses Apache POI library for
 * Excel handling.
 * This is for storing persistent data across different program sessions (after
 * exiting the program).
 *
 * <p>
 * The serialization process converts a collection of {@link Camp} objects into
 * an Excel
 * file, while deserialization reads data from an Excel file and creates
 * corresponding
 * {@code Camp} objects and establishes the required associations with
 * {@link Staff}
 * and {@link Student}.
 * </p>
 *
 * <p>
 * Serialized data includes camp details such as name, location, description,
 * start date,
 * end date, registration deadline, total slots, visibility, user group, staff
 * in charge,
 * committee members, and attendees.
 * </p>
 *
 * <p>
 * The Excel file has a specific structure with headers and rows containing
 * the respective camp information.
 * </p>
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class CampSerializer {
    /**
     * Deserializes camp information from the specified Excel file path and updates
     * the {@link CampController} accordingly.
     *
     * @param path the file path of the Excel file containing camp information
     * @return list of camps deserialized from the Excel file
     */
    public static List<Camp> deserialize(String path) throws RuntimeException {
        List<Camp> result = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            UserController userController = UserController.getInstance();

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
                for (int i = 0; i < 13; i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null || cell.getCellType() == CellType.BLANK) {
                        args.add("");
                    } else {
                        args.add(cell.toString());
                    }
                }

                Camp newCamp = new Camp(
                        args.get(0), args.get(1), args.get(2),
                        LocalDate.parse(args.get(3), formatter),
                        LocalDate.parse(args.get(4), formatter),
                        LocalDate.parse(args.get(5), formatter),
                        (int) Double.parseDouble(args.get(6)),
                        Boolean.parseBoolean(args.get(7)), args.get(8),
                        (Staff) UserController.getInstance().getUser(args.get(9)));

                String[] committees = args.get(10).split(", ");
                String[] attendees = args.get(11).split(", ");
                String[] blacklist = args.get(12).split(", ");

                for (String committeePoint : committees) {
                    if (committeePoint.isBlank())
                        continue;
                    String[] committeePointArr = committeePoint.split(": ");
                    String committee = committeePointArr[0];
                    int points = Integer.parseInt(committeePointArr[1]);
                    Student student = (Student) userController.getUser(committee);
                    if (student == null)
                        throw new RuntimeException("Student not found in user table!");
                    student.setCommitteeFor(newCamp);
                    student.addCamp(newCamp);
                    newCamp.addCommittee(student, points);
                }
                for (String attendee : attendees) {
                    if (attendee.isBlank())
                        continue;
                    Student student = (Student) userController.getUser(attendee);
                    if (student == null)
                        throw new RuntimeException("Student not found in user table!");
                    student.addCamp(newCamp);
                    newCamp.addAttendee(student);
                }
                for (String blacklistedStudent : blacklist) {
                    if (blacklistedStudent.isBlank())
                        continue;
                    Student student = (Student) userController.getUser(blacklistedStudent);
                    if (student == null)
                        throw new RuntimeException("Student not found in user table!");
                    newCamp.addBlacklist(student);
                }
                result.add(newCamp);
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    /**
     * Serializes camp information to the specified Excel file path based on the
     * current state of the {@code CampController}.
     *
     * @param campTable the camp table input to be serialized into the Excel file
     * @param path      the file path where the camp information should be serialized to
     */
    public static void serialize(Map<String, Camp> campTable, String path) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("outputSheet");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            Row headerRow = sheet.createRow(0);
            List<String> header = List.of("Name", "Location", "Description", "Start Date",
                    "End Date", "Registration Deadline", "Total Slots",
                    "Is Visible", "User Group", "Staff in Charge", "Committee Members", "Attendees", "Blacklist");
            for (int i = 0; i < 13; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }

            int rowNum = 1;
            for (Map.Entry<String, Camp> entry : campTable.entrySet()) {
                Camp camp = entry.getValue();
                Row row = sheet.createRow(rowNum);
                for (int i = 0; i < 13; i++) {
                    Cell cell = row.createCell(i);
                    CampInfo campInfo = camp.getCampInfo();
                    CampDate campDate = camp.getCampDate();
                    String commaSeparatedCommittee = String.join(", ",
                            new ArrayList<>(
                                    camp.getCommittee().entrySet().stream().map(
                                                    entryLambda -> entryLambda.getKey().getUserID() + ": " + entryLambda.getValue())
                                            .collect(Collectors.toSet())));
                    String commaSeparatedAttendee = String.join(", ",
                            new ArrayList<>(
                                    camp.getAttendees().stream().map(Student::getUserID).collect(Collectors.toSet())));
                    String commaSeparatedBlacklist = String.join(", ",
                            new ArrayList<>(
                                    camp.getBlacklist().stream().map(Student::getUserID).collect(Collectors.toSet())));
                    switch (i) {
                        case 0:
                            cell.setCellValue(campInfo.getCampName());
                            break;
                        case 1:
                            cell.setCellValue(campInfo.getLocation());
                            break;
                        case 2:
                            cell.setCellValue(campInfo.getDescription());
                            break;
                        case 3:
                            cell.setCellValue(campDate.getStartDate().format(formatter));
                            break;
                        case 4:
                            cell.setCellValue(campDate.getEndDate().format(formatter));
                            break;
                        case 5:
                            cell.setCellValue(campDate.getRegistrationDeadline().format(formatter));
                            break;
                        case 6:
                            cell.setCellValue(campInfo.getTotalSlots());
                            break;
                        case 7:
                            cell.setCellValue(String.valueOf(campInfo.getIsVisible()));
                            break;
                        case 8:
                            cell.setCellValue(camp.getUserGroup());
                            break;
                        case 9:
                            cell.setCellValue(camp.getStaffInCharge().getUserID());
                            break;
                        case 10:
                            cell.setCellValue(commaSeparatedCommittee);
                            break;
                        case 11:
                            cell.setCellValue(commaSeparatedAttendee);
                            break;
                        case 12:
                            cell.setCellValue(commaSeparatedBlacklist);
                            break;
                        default:
                            break;
                    }
                }
                rowNum++;
            }

            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                workbook.write(fileOut);
            }
        } catch (IOException ignored) {
        }
    }
}
