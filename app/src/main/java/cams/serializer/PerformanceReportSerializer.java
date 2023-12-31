package cams.serializer;

import cams.camp.Camp;
import cams.camp.CampDate;
import cams.camp.CampInfo;
import cams.domain.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * The {@code PerformanceReportSerializer} class provides a method for
 * generating a performance report
 * for a specific camp and serializing it to an Excel file. The report includes
 * camp information and
 * committee members' performance details such as name, email, faculty, and
 * points.
 *
 * <p>
 * The Excel file is created with two sections: one for camp information and
 * another for committee
 * members' performance. The file is saved with a name based on the camp's name.
 * </p>
 *
 * <p>
 * This class uses the Apache POI library for Excel handling.
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
public class PerformanceReportSerializer {
    /**
     * Serializes the performance report for the specified camp to an Excel file.
     * The Excel file includes camp information and committee members' performance details.
     *
     * @param camp the {@link Camp} for which the performance report is generated
     */
    public static void serialize(Camp camp) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("outputSheet");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            Row titleRow = sheet.createRow(0);
            Cell cell = titleRow.createCell(0);
            cell.setCellValue("Camp Info");

            Row headerRow = sheet.createRow(1);
            List<String> header = List.of("Name", "Location", "Description", "Start Date",
                    "End Date", "Registration Deadline", "Total Slots",
                    "Is Visible", "User Group", "Staff in Charge");
            for (int i = 0; i < 10; i++) {
                cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }

            Row row = sheet.createRow(2);
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

            titleRow = sheet.createRow(4);
            cell = titleRow.createCell(0);
            cell.setCellValue("Commitee Performance");

            headerRow = sheet.createRow(5);
            header = List.of("Name", "Email", "Faculty", "Points");
            for (int i = 0; i < 4; i++) {
                cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }

            int rowNum = 6;
            for (Map.Entry<Student, Integer> entry : camp.getCommittee().entrySet()) {
                Student student = entry.getKey();
                int points = entry.getValue();
                row = sheet.createRow(rowNum);
                for (int i = 0; i < 4; i++) {
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
                        case 3:
                            cell.setCellValue(points);
                            break;
                        default:
                            break;
                    }
                }
                rowNum++;
            }

            File reportFolder = new File("report");
            if (!reportFolder.exists()) {
                reportFolder.mkdirs();
            }

            try (FileOutputStream fileOut = new FileOutputStream(
                    "report/performance_report_" + camp.getCampInfo().getCampName() + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException ignored) {
        }
    }
}
