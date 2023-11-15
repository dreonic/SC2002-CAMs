package cams.serializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Staff;
import cams.domain.Student;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.repliable.Repliable;
import cams.repliable.Suggestion;
import cams.repliable.SuggestionEditor;
import cams.user.User;
import cams.user.UserController;

public class RepliableSerializer {
    public static void deserialize(String repliableType) {
        try (FileInputStream fileIn = new FileInputStream(
                "enquiry".equals(repliableType) ? "src/data/enquiry_list.xlsx" : "src/data/suggestion_list.xlsx");
                Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);
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
                if ("enquiry".equals(repliableType)) {
                    EnquiryEditor editor = new EnquiryEditor(campController.getCamp(args.get(0)));
                    Student student = (Student) userController.getUser(args.get(1));
                    Enquiry enquiry = (Enquiry) editor.create(args.get(2), student);
                    student.addEnquiry(enquiry);
                    editor.reply(enquiry, args.get(3));

                } else {
                    SuggestionEditor editor = new SuggestionEditor(campController.getCamp(args.get(0)));
                    Student student = (Student) userController.getUser(args.get(1));
                    Suggestion suggestion = (Suggestion) editor.create(args.get(2), student);
                    editor.reply(suggestion, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serialize(String repliableType) {
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
                    "enquiry".equals(repliableType) ? "src/data/enquiry_list.xlsx" : "src/data/_list.xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
