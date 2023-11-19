package cams.view.components.student;

import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.Enquiry;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentViewEnquiryMenu extends SelectionMenu {
    public StudentViewEnquiryMenu(Scanner scanner) {
        super(scanner);
        StudentController studentController = StudentController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Student currentUser = studentController.getCurrentStudent();
        List<Enquiry> enquiriesList = new ArrayList<Enquiry>(currentUser.getEnquiries());

        setPrompt(CommonElements.getStatusBar("View Enquiries"));

        for (Enquiry enquiry : enquiriesList) {
            addItem(new ActionableItem("\"" + enquiry.getQuestion() + "\"", new ItemAction() {
                public void execute() {
                    displayController.setNextDisplay(new StudentEnquiryMenu(scanner, enquiry));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentMenu(scanner));
            }
        }));
    }
}
