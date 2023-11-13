package cams.view.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffCampMenu extends SelectionMenu{
    public StaffCampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        StringBuilder studentList = new StringBuilder();

        addItem(new ActionableItem("View students", new ItemAction() {
            public void execute() {
                ArrayList<Student> attendeesList = camp.getAttendees();
                ArrayList<Student> committeeList = new ArrayList<Student>();
                HashMap<Student,Integer> committee = camp.getCommittee();

                for(HashMap.Entry<Student,Integer> member:committee.entrySet()) {
                    committeeList.add(member.getKey());
                }
                studentList.append("Attendees: \n");
                for(Student student:attendeesList) {
                    studentList.append(student.getName() + "\n");
                }
                studentList.append("\n");
                studentList.append("Committee Members: \n");
                for(Student student:committeeList) {
                    studentList.append(student.getName() + "\n");
                }
                displayController.setNextDisplay(new Alert(studentList.toString(), new StaffCampMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("View enquiries", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewEnquiryMenu(scanner));
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewCampMenu(scanner));
            }
        }));

    }
}
