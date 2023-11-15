package cams.serializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampDate;
import cams.camp.CampInfo;
import cams.domain.Staff;
import cams.domain.Student;
import cams.user.UserController;

public class CampSerializer {
    public static void deserialize() {
        deserialize("src/data/camp_list.xslx");
    }

    public static void deserialize(String path) {
        try (FileInputStream fileIn = new FileInputStream(path);
                Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            CampController campController = CampController.getInstance();
            UserController userController = UserController.getInstance();

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
                campController.createCamp(args.get(0), args.get(1), args.get(2),
                        LocalDate.parse(args.get(3), formatter),
                        LocalDate.parse(args.get(4), formatter),
                        LocalDate.parse(args.get(5), formatter),
                        Integer.parseInt(args.get(6)),
                        Boolean.parseBoolean(args.get(7)), args.get(8),
                        (Staff) UserController.getInstance().getUser(args.get(9)));

                Camp newCamp = campController.getCamp(args.get(0));
                String[] commitees = args.get(10).split(",");
                String[] attendees = args.get(11).split(",");

                for (String commitee : commitees) {
                    Student student = (Student) userController.getUser(commitee);
                    student.setCommitteeFor(newCamp);
                    newCamp.addCommittee(student);
                }
                for (String attendee : attendees) {
                    Student student = (Student) userController.getUser(attendee);
                    student.addCamp(newCamp);
                    newCamp.addAttandee(student);
                }
            }
        } catch (IOException e) {
        }
    }

    public static void serialize() {
        serialize("src/data/camp_list.xlsx");
    }

    public static void serialize(String path) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("outputSheet");
            Map<String, Camp> campTable = CampController.getInstance().getCampTable();

            Row headerRow = sheet.createRow(0);
            List<String> header = List.of("Name", "Location", "Description", "Start Date",
                    "End Date", "Registration Deadline", "Total Slots",
                    "Is Visible", "User Group", "Staff in Charge", "Commitee Members", "Attendees");
            for (int i = 0; i < 12; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }

            int rowNum = 1;
            for (Map.Entry<String, Camp> entry : campTable.entrySet()) {
                Camp camp = entry.getValue();
                Row row = sheet.createRow(rowNum);
                for (int i = 0; i < 9; i++) {
                    Cell cell = row.createCell(i);
                    CampInfo campInfo = camp.getCampInfo();
                    CampDate campDate = camp.getCampDate();
                    String commaSeparatedCommittee = String.join(", ",
                            new ArrayList<>(
                                    camp.getCommittee().keySet().stream().map(Student::getUserID)
                                            .collect(Collectors.toSet())));
                    String commaSeparatedAttendee = String.join(", ",
                            new ArrayList<>(
                                    camp.getAttendees().stream().map(Student::getUserID).collect(Collectors.toSet())));
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
                            cell.setCellValue(campDate.getStartDate().toString());
                            break;
                        case 4:
                            cell.setCellValue(campDate.getEndDate().toString());
                            break;
                        case 5:
                            cell.setCellValue(campDate.getRegistrationDeadline().toString());
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
                            cell.setCellValue(commaSeparatedAttendee);
                            break;
                        case 11:
                            cell.setCellValue(commaSeparatedCommittee);
                            break;
                        default:
                            break;
                    }
                    rowNum++;
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
