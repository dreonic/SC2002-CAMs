package cams.view.components.staff;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Suggestion;
import cams.repliable.SuggestionEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.Scanner;

public class StaffSuggestionMenu extends SelectionMenu {
    public StaffSuggestionMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        SuggestionEditor suggestionEditor = new SuggestionEditor(camp);
        Suggestion suggestion = suggestionEditor.getCurrentSuggestion();
        DisplayController displayController = DisplayController.getInstance();

        addItem(new ActionableItem("Approve? (Y/n)", new ItemAction() {
            public void execute() {
                String reply = scanner.nextLine();
                if (reply.toLowerCase().equals("y")) {
                    suggestion.setIsApproved(true);
                    displayController.setNextDisplay(new Alert("Suggestion has been approved", new StaffViewSuggestionMenu(scanner), scanner));
                } else {
                    displayController.setNextDisplay(new Alert("Suggestion pending approval", new StaffViewSuggestionMenu(scanner), scanner));
                }
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewSuggestionMenu(scanner));
            }
        }));
    }
}
