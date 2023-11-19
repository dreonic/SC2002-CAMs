package cams.view.components.student;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.repliable.EditEnquiryForm;

import java.util.Scanner;

public class StudentEnquiryMenu extends SelectionMenu {
    public StudentEnquiryMenu(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        StudentController studentController = StudentController.getInstance();
        Student currentUser = studentController.getCurrentStudent();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        EnquiryEditor enquiryEditor = new EnquiryEditor(camp);

        System.out.println(enquiry.getQuestion());

        addItem(new ActionableItem("Edit", new ItemAction() {
            public void execute() {
                if(enquiry.getReply() == null) {
                    displayController.setNextDisplay(new EditEnquiryForm(scanner, enquiry));    
                }
                else {
                    displayController.setNextDisplay(new Alert("Enquiry cannot be edited", new StudentViewEnquiryMenu(scanner), scanner));
                }
                
            }
        }));

        addItem(new ActionableItem("Delete", new ItemAction() {
            public void execute() {
                if (enquiry.getReply() == null) {
                    enquiryEditor.delete(enquiry);
                    currentUser.removeEnquiry(enquiry);
                    displayController.setNextDisplay(new Alert("Enquiry has been deleted", new StudentViewEnquiryMenu(scanner), scanner));
                } else {
                    displayController.setNextDisplay(new Alert("Enquiry cannot be deleted", new StudentViewEnquiryMenu(scanner), scanner));
                }
            }
        }));


        addItem(new ActionableItem("View Reply", new ItemAction() {
            public void execute() {
                if(enquiry.getReply() == null){
                    displayController.setNextDisplay(new Alert("No reply yet", new StudentViewEnquiryMenu(scanner), scanner));
                }
                else{
                    displayController.setNextDisplay(new Alert(enquiry.getReply(), new StudentEnquiryMenu(scanner, enquiry), scanner));
                }
            }
        }));


        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentViewEnquiryMenu(scanner));
            }
        }));
    }
}
