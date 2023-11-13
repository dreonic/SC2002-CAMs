package cams.view.components;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffCampMenu extends SelectionMenu {
    public StaffCampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        StringBuilder studentList = new StringBuilder();

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
                        studentList.append(student.getName() + "\n");
                    }
                }

                studentList.append("\n");

                if (committeeList.isEmpty()) {
                    studentList.append("0 Committee Members");
                } else {
                    studentList.append("Committee Members: \n");
                    for (Student student : committeeList) {
                        studentList.append(student.getName() + "\n");
                    }
                }
                displayController.setNextDisplay(new Alert(
                        studentList.toString(), new StaffCampMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("View Enquiries", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewEnquiryMenu(scanner));
            }
        }));

        addItem(new ActionableItem("Edit Camp", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new EditCampMenu(scanner));
            }
        }));

        addItem(new ActionableItem("Delete Camp", new ItemAction() {
            public void execute() {

            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewCampMenu(scanner));
            }
        }));

    }
}
