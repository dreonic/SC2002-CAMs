package cams.serializer;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.repliable.*;
import cams.user.UserController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The {@code RepliableSerializer} class provides methods to serialize and deserialize
 * {@link Enquiry} and {@link Suggestion} objects to and from Excel files. It is specifically
 * designed to handle {@code Enquiry} and {@code Suggestion} for a given specific {@link Camp}
 *
 * <p>This class uses the Apache POI library for Excel handling.</p>
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class RepliableSerializer {
    /**
     * Deserializes {@code Enquiry} or {@code Suggestion} data from Excel files based on the
     * specified repliable type and file paths.
     *
     * @param repliableType  the type of repliable data to deserialize {@code Enquiry} or {@code Suggestion}
     * @param enquiryPath    the file path for the enquiry data Excel file
     * @param suggestionPath the file path for the suggestion data Excel file
     */
    public static void deserialize(String repliableType, String enquiryPath, String suggestionPath) {
        try (FileInputStream fileIn = new FileInputStream(
                "enquiry".equals(repliableType) ? enquiryPath : suggestionPath);
             Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);
            CampController campController = CampController.getInstance();
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

                List<String> args = new ArrayList<String>();
                for (Cell cell : row) {
                    args.add(cell.toString());
                }
                if ("enquiry".equals(repliableType)) {
                    EnquiryEditor editor = new EnquiryEditor(campController.getCamp(args.get(0)));
                    Student student = (Student) userController.getUser(args.get(1));
                    Enquiry enquiry = (Enquiry) editor.create(args.get(2), student);
                    student.addEnquiry(enquiry);
                    if (args.get(3) != "") {
                        editor.reply(enquiry, args.get(3));
                    }
                } else {
                    SuggestionEditor editor = new SuggestionEditor(campController.getCamp(args.get(0)));
                    Student student = (Student) userController.getUser(args.get(1));
                    Suggestion suggestion = (Suggestion) editor.create(args.get(2), student);
                    if (Boolean.parseBoolean(args.get(3))) {
                        editor.reply(suggestion, null);
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Serializes {@code Enquiry} or {@code Suggestion} data to Excel files based on the specified repliable type and file paths.
     *
     * @param repliableType  the type of repliable data to serialize {@code Enquiry} or {@code Suggestion}
     * @param enquiryPath    the file path for the enquiry data Excel file
     * @param suggestionPath the file path for the suggestion data Excel file
     */
    public static void serialize(String repliableType, String enquiryPath, String suggestionPath) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("outputSheet");
            Map<String, Camp> campTable = CampController.getInstance().getCampTable();

            Row headerRow = sheet.createRow(0);
            List<String> header = "enquiry".equals(repliableType) ? List.of("Camp", "Student", "Question", "Reply")
                    : List.of("Camp", "Student", "Suggestion", "Is Approved");
            for (int i = 0; i < 4; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }

            int rowNum = 1;
            for (Map.Entry<String, Camp> entry : campTable.entrySet()) {
                Camp camp = entry.getValue();
                Set<? extends Repliable> repliableSet = "enquiry".equals(repliableType) ? camp.getEnquiries()
                        : camp.getSuggestions();
                for (Repliable repliable : repliableSet) {
                    Row row = sheet.createRow(rowNum);
                    for (int i = 0; i < 4; i++) {
                        Cell cell = row.createCell(i);
                        switch (i) {
                            case 0:
                                cell.setCellValue(camp.getCampInfo().getCampName());
                                break;
                            case 1:
                                cell.setCellValue(repliable.getStudent().getUserID());
                                break;
                            case 2:
                                cell.setCellValue("enquiry".equals(repliableType) ? ((Enquiry) repliable).getQuestion()
                                        : ((Suggestion) repliable).getContent());
                                break;
                            case 3:
                                cell.setCellValue("enquiry".equals(repliableType) ? ((Enquiry) repliable).getReply()
                                        : String.valueOf(((Suggestion) repliable).getIsApproved()));
                                break;
                            default:
                                break;
                        }
                    }
                    rowNum++;
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(
                    "enquiry".equals(repliableType) ? enquiryPath : suggestionPath)) {
                workbook.write(fileOut);
            }
        } catch (IOException ignored) {
        }
    }
}
