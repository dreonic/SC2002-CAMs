package cams.view.components;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StudentCommitteeMenu extends SelectionMenu {
    public StudentCommitteeMenu(Scanner scanner) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp  = campController.getCurrentCamp();

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
