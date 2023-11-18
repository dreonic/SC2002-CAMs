package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.Scanner;

public class CommitteeEnquiryMenu extends SelectionMenu {
    public CommitteeEnquiryMenu(Scanner scanner, Enquiry enquiry) {
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
                displayController.setNextDisplay(new CommitteeViewEnquiryMenu(scanner));
            }
        }));
    }
}
