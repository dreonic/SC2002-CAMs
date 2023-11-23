package cams.view.components.student;

import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.Enquiry;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.repliable.StudentEnquiryMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The boundary class responsible for displaying a list of 
 * enquiries the Student has submitted for them to select.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class StudentViewEnquiryMenu extends SelectionMenu {
    
    /**
     * Constructs the Student View Enquiry Menu specifying the scanner to be used.
     * 
     * @param scanner scanner for this menu
     */
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
