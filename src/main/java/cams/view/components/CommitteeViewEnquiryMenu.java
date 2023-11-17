package cams.view.components;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class CommitteeViewEnquiryMenu extends SelectionMenu {
    public CommitteeViewEnquiryMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        List<Enquiry> enquiriesList = new ArrayList<Enquiry>(camp.getEnquiries());

        for (Enquiry enquiry : enquiriesList) {
            addItem(new ActionableItem(enquiry.getQuestion(), new ItemAction() {
                public void execute() {
                    displayController.setNextDisplay(new CommitteeEnquiryMenu(scanner, enquiry));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
            }
        }));
    }
}
