package cams.serializer;

import cams.camp.Camp;
import cams.camp.CampDate;
import cams.camp.CampInfo;
import cams.domain.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * The {@code StudentListSerializer} class provides a method to serialize the information of
 * students associated with a camp, including committee members and attendees, into an Excel file.
 * The generated Excel file includes details such as camp information, committee list, and attendee list.
 *
 * <p>This class uses the Apache POI library for Excel handling.</p>
 */
public class StudentListSerializer {
    /**
     * Serializes the information of students associated with a camp, including committee members
     * and attendees, into an Excel file. The generated Excel file includes details such as camp information,
     * committee list, and attendee list. The specific information to include or exclude is determined by
     * the value of the {@code removeTable} parameter, which can be set to "committee" to exclude committee
     * information, "attendee" to exclude attendee information, or "none" to include both.
     *
     * @param camp        The camp for which student information is to be serialized.
     * @param removeTable A string indicating which table(s) to exclude from the output.
     *                    Valid values are "committee", "attendee", or "none" for both.
     */
    public static void serialize(Camp camp, String removeTable) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("outputSheet");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            int rowNum = 0;

            Row titleRow = sheet.createRow(rowNum);
            Cell cell = titleRow.createCell(rowNum);
            cell.setCellValue("Camp Info");
            rowNum++;

            Row headerRow = sheet.createRow(rowNum);
            List<String> header = List.of("Name", "Location", "Description", "Start Date",
                    "End Date", "Registration Deadline", "Total Slots",
                    "Is Visible", "User Group", "Staff in Charge");
            for (int i = 0; i < 10; i++) {
                cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }
            rowNum++;

            Row row = sheet.createRow(rowNum);
            for (int i = 0; i < 10; i++) {
                cell = row.createCell(i);
                CampInfo campInfo = camp.getCampInfo();
                CampDate campDate = camp.getCampDate();
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
                    default:
                        break;
                }
            }
            rowNum += 2;

            if (!removeTable.equals("committee")) {
                titleRow = sheet.createRow(rowNum);
                cell = titleRow.createCell(0);
                cell.setCellValue("Committee List");
                rowNum++;

                headerRow = sheet.createRow(rowNum);
                header = List.of("Name", "Email", "Faculty");
                for (int i = 0; i < 3; i++) {
                    cell = headerRow.createCell(i);
                    cell.setCellValue(header.get(i));
                }
                rowNum++;

                for (Map.Entry<Student, Integer> entry : camp.getCommittee().entrySet()) {
                    Student student = entry.getKey();
                    row = sheet.createRow(rowNum);
                    for (int i = 0; i < 3; i++) {
                        cell = row.createCell(i);
                        switch (i) {
                            case 0:
                                cell.setCellValue(student.getName());
                                break;
                            case 1:
                                cell.setCellValue(student.getUserID().concat("@e.ntu.edu.sg"));
                                break;
                            case 2:
                                cell.setCellValue(student.getFaculty());
                                break;
                            default:
                                break;
                        }
                    }
                    rowNum++;
                }
                rowNum++;
            }

            if (!removeTable.equals("attendee")) {
                titleRow = sheet.createRow(rowNum);
                cell = titleRow.createCell(0);
                cell.setCellValue("Attendee List");
                rowNum++;

                headerRow = sheet.createRow(rowNum);
                header = List.of("Name", "Email", "Faculty");
                for (int i = 0; i < 3; i++) {
                    cell = headerRow.createCell(i);
                    cell.setCellValue(header.get(i));
                }
                rowNum++;

                for (Student student : camp.getAttendees()) {
                    row = sheet.createRow(rowNum);
                    for (int i = 0; i < 3; i++) {
                        cell = row.createCell(i);
                        switch (i) {
                            case 0:
                                cell.setCellValue(student.getName());
                                break;
                            case 1:
                                cell.setCellValue(student.getUserID().concat("@e.ntu.edu.sg"));
                                break;
                            case 2:
                                cell.setCellValue(student.getFaculty());
                                break;
                            default:
                                break;
                        }
                    }
                    rowNum++;
                }
                rowNum++;
            }

            try (FileOutputStream fileOut = new FileOutputStream(
                    "student_list_" + camp.getCampInfo().getCampName() + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
