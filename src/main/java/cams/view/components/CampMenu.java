package cams.view.components;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.user.AuthController;
import cams.user.User;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class CampMenu extends SelectionMenu {
    public CampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        AuthController authController = AuthController.getInstance();
        User currentUser = authController.getCurrentUser();
        StringBuilder campInfo = new StringBuilder();

        campInfo.append("Camp Name: " + camp.getCampInfo().getCampName() + "\n");
        campInfo.append("Location: " + camp.getCampInfo().getLocation() + "\n");
        campInfo.append("Description: " + camp.getCampInfo().getDescription() + "\n");
        campInfo.append("Start Date: " + camp.getCampDate().getStartDate().toString() + "\n");
        campInfo.append("End Date: " + camp.getCampDate().getEndDate().toString() + "\n");
        campInfo.append("Available Slots: " + (camp.getCampInfo().getTotalSlots() - camp.getAttendees().size() - camp.getCommittee().size()) + "\n");
        campInfo.append("User Group: " + camp.getUserGroup() + "\n");
        campInfo.append("Staff in Charge: " + camp.getStaffInCharge().getName() + "\n");

        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()) + campInfo.toString());

        if(currentUser instanceof Student) {
            addItem(new ActionableItem("Register", new ItemAction() {
                public void execute() {
                    if(((Student)currentUser).getCamps().contains(camp)) {
                        displayController.setNextDisplay(new Alert("Already registered for this camp!", new CampMenu(scanner), scanner));
                    }
                    else {
                        displayController.setNextDisplay(new RegisterMenu(scanner));
                    }
                }
            }));

            addItem(new ActionableItem("Submit Enquiry", new ItemAction() {
                public void execute() {
                    displayController.setNextDisplay(new SubmitEnquiryForm(scanner));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new FilterCampMenu(scanner));
            }
        }));
    }
}
