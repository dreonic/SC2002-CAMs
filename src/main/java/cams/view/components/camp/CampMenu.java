package cams.view.components.camp;

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
import cams.view.components.repliable.SubmitEnquiryForm;
import de.vandermeer.asciitable.AsciiTable;

public class CampMenu extends SelectionMenu {
    public CampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        AuthController authController = AuthController.getInstance();
        User currentUser = authController.getCurrentUser();

        AsciiTable info = new AsciiTable();
        info.addRule();
        info.addRow("Camp Name: ", camp.getCampInfo().getCampName());
        info.addRule();
        info.addRow("Location: ", camp.getCampInfo().getLocation());
        info.addRule();
        info.addRow("Description: ", camp.getCampInfo().getDescription());
        info.addRule();
        info.addRow("Date: ", camp.getCampDate().getStartDate().toString() + " - " + camp.getCampDate().getEndDate().toString());
        info.addRule();
        info.addRow("Available Slots: ", (camp.getCampInfo().getTotalSlots() - camp.getAttendees().size() - camp.getCommittee().size()));
        info.addRule();
        info.addRow("User Group: ", camp.getUserGroup());
        info.addRule();
        info.addRow("Staff in Charge: ", camp.getStaffInCharge().getName());
        info.addRule();

        String rend = info.render();
        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()) + "\n" + rend + "\n");

        if (currentUser instanceof Student) {
            addItem(new ActionableItem("Register", new ItemAction() {
                public void execute() {
                    if (((Student) currentUser).getCamps().contains(camp)) {
                        displayController.setNextDisplay(new Alert("Already registered for this camp!", new CampMenu(scanner), scanner));
                    } else {
                        displayController.setNextDisplay(new CampRegisterMenu(scanner));
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
