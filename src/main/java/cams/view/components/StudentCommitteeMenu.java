package cams.view.components;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import de.vandermeer.asciitable.AsciiTable;

public class StudentCommitteeMenu extends SelectionMenu {
    public StudentCommitteeMenu(Scanner scanner) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp  = campController.getCurrentCamp();

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

        addItem(new ActionableItem("Submit Suggestion", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new SubmitSuggestionForm(scanner));
            }
        }));

        addItem(new ActionableItem("View Enquiries", new ItemAction() {
            public void execute() {
                if (camp.getEnquiries().isEmpty()) {
                    displayController.setNextDisplay(new Alert(
                            "No Enquiries", new StudentCommitteeMenu(scanner), scanner));
                } else {
                    displayController.setNextDisplay(new CommitteeViewEnquiryMenu(scanner));
                }
            }
        }));

        addItem(new ActionableItem("Generate Attendance List", new ItemAction() {
            public void execute() {
                
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentMenu(scanner));
            }
        }));

    }
}
