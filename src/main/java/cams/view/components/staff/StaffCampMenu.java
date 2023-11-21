package cams.view.components.staff;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.serializer.PerformanceReportSerializer;
import cams.view.DisplayController;
import cams.view.base.*;
import cams.view.components.camp.EditCampMenu;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StaffCampMenu extends SelectionMenu {
    public StaffCampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        StringBuilder studentList = new StringBuilder();
        String campName = camp.getCampInfo().getCampName();

        AsciiTable info = new AsciiTable();
        info.addRule();
        info.addRow("Camp Name: ", camp.getCampInfo().getCampName());
        info.addRule();
        info.addRow("Location: ", camp.getCampInfo().getLocation());
        info.addRule();
        info.addRow("Description: ", camp.getCampInfo().getDescription());
        info.addRule();
        info.addRow("Date: ", camp.getCampDate().getStartDate().toString() + " - " + camp.getCampDate().getEndDate().toString());
        info.addRule();
        info.addRow("Available Slots: ", (camp.getCampInfo().getTotalSlots() - camp.getAttendees().size() - camp.getCommittee().size()));
        info.addRule();
        info.addRow("User Group: ", camp.getUserGroup());
        info.addRule();
        info.addRow("Staff in Charge: ", camp.getStaffInCharge().getName());
        info.addRule();

        String rend = info.render();
        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()) + "\n" + rend + "\n");

        addItem(new ActionableItem("View Students", new ItemAction() {
            public void execute() {
                List<Student> attendeesList = new ArrayList<Student>(camp.getAttendees());
                List<Student> committeeList = new ArrayList<Student>();
                Map<Student, Integer> committee = camp.getCommittee();

                if (committee != null) {
                    for (Map.Entry<Student, Integer> member : committee.entrySet()) {
                        committeeList.add(member.getKey());
                    }
                }

                if (attendeesList.isEmpty()) {
                    studentList.append("0 Attendees");
                } else {
                    studentList.append("Attendees: \n");
                    for (Student student : attendeesList) {
                        studentList.append(student.getName()).append("\n");
                    }
                }

                studentList.append("\n");

                if (committeeList.isEmpty()) {
                    studentList.append("0 Committee Members");
                } else {
                    studentList.append("Committee Members: \n");
                    for (Student student : committeeList) {
                        studentList.append(student.getName()).append("\n");
                    }
                }
                displayController.setNextDisplay(new Alert(
                        studentList.toString(), new StaffCampMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("View Enquiries", new ItemAction() {
            public void execute() {
                if (camp.getEnquiries().isEmpty()) {
                    displayController.setNextDisplay(new Alert(
                            "No Enquiries", new StaffCampMenu(scanner), scanner));
                } else {
                    displayController.setNextDisplay(new StaffViewEnquiryMenu(scanner));
                }
            }
        }));

        addItem(new ActionableItem("View Suggestions", new ItemAction() {
            public void execute() {
                if (camp.getSuggestions().isEmpty()) {
                    displayController.setNextDisplay(new Alert(
                            "No Suggestions", new StaffCampMenu(scanner), scanner));
                } else {
                    displayController.setNextDisplay(new StaffViewSuggestionMenu(scanner));
                }
            }
        }));

        addItem(new ActionableItem("Edit Camp", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new EditCampMenu(scanner));
            }
        }));

        addItem(new ActionableItem("Delete Camp", new ItemAction() {
            public void execute() {
                try {
                    campController.deleteCamp(campName);
                    displayController.setNextDisplay(new Alert(campName + " successfully deleted!", new StaffViewCampMenu(scanner), scanner));
                } catch (RuntimeException e) {
                    displayController.setNextDisplay(new Alert(
                            e.getMessage(),
                            new StaffCampMenu(scanner), scanner));
                }
            }
        }));

        addItem(new ActionableItem("Generate Student List", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffStudentListFilterMenu(scanner));
            }
        }));

        addItem(new ActionableItem("Generate Performance Report", new ItemAction() {
            public void execute() {
                PerformanceReportSerializer.serialize(camp);
                displayController.setNextDisplay(new Alert(
                        "Performance report generated! See report in report/performance_report_" + campName + ".xlsx",
                        new StaffCampMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewCampMenu(scanner));
            }
        }));

    }
}
