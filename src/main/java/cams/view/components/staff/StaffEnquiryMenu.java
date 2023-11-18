package cams.view.components.staff;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffEnquiryMenu extends SelectionMenu {
    public StaffEnquiryMenu(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        EnquiryEditor enquiryEditor = new EnquiryEditor(camp);
        DisplayController displayController = DisplayController.getInstance();

        addItem(new ActionableItem("Reply", new ItemAction() {
            public void execute() {
                String reply = scanner.nextLine();
                enquiryEditor.reply(enquiry, reply);
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewEnquiryMenu(scanner));
            }
        }));
    }
}