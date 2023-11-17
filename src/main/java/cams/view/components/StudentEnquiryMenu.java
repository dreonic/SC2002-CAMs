package cams.view.components;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StudentEnquiryMenu extends SelectionMenu {
    public StudentEnquiryMenu(Scanner scanner, Enquiry currentEnquiry) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        EnquiryEditor enquiryEditor = new EnquiryEditor(camp);
        
        System.out.println(currentEnquiry.getQuestion());

        addItem(new ActionableItem("Edit", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new EnquiryEditorForm(scanner, currentEnquiry));
            }
        }));

        addItem(new ActionableItem("Delete", new ItemAction() {
            public void execute() {
                enquiryEditor.delete(currentEnquiry);
                displayController.setNextDisplay(new Alert("Enquiry has been deleted", new StudentViewEnquiryMenu(scanner), scanner));
            }
        }));

        
        addItem(new ActionableItem("View Reply", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new Alert(currentEnquiry.getReply(), new StudentEnquiryMenu(scanner, currentEnquiry), scanner));
            }
        }));

        
        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentViewEnquiryMenu(scanner));
            }
        }));
    }
}
