package cams.view.components;

import java.util.ArrayList;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffViewEnquiryMenu extends SelectionMenu {
    public StaffViewEnquiryMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController controller = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        EnquiryEditor enquiryEditor = new EnquiryEditor(camp);
        ArrayList<Enquiry> enquiriesList = camp.getEnquiriesArray();

        for(Enquiry enquiry:enquiriesList) {
            addItem(new ActionableItem(enquiry.getQuestion(), new ItemAction() {
                public void execute() {
                    enquiryEditor.setCurrentEnquiry(enquiry);
                    controller.setNextDisplay(new StaffEnquiryMenu(scanner));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                controller.setNextDisplay(new StaffCampMenu(scanner));
            }
        }));
    }
}
