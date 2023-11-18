package cams.view.components.staff;

import cams.repliable.Enquiry;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.repliable.ReplyEnquiryForm;

import java.util.Scanner;

public class StaffEnquiryMenu extends SelectionMenu {
    public StaffEnquiryMenu(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();

        addItem(new ActionableItem("Reply", new ItemAction() {
            public void execute() {
                if(enquiry.getReply() != null) {
                    displayController.setNextDisplay(new Alert("Enquiry has already been answered!", new StaffViewEnquiryMenu(scanner), scanner));
                }
                else {
                    displayController.setNextDisplay(new ReplyEnquiryForm(scanner, enquiry));
                }
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewEnquiryMenu(scanner));
            }
        }));
    }
}
