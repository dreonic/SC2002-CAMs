package cams.view.components.staff;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.repliable.Suggestion;
import cams.repliable.SuggestionEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.Map;
import java.util.Scanner;

public class StaffSuggestionMenu extends SelectionMenu {
    public StaffSuggestionMenu(Scanner scanner, SuggestionEditor suggestionEditor) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        Suggestion suggestion = suggestionEditor.getCurrentSuggestion();
        DisplayController displayController = DisplayController.getInstance();

        addItem(new ActionableItem("Approve? (Y/n)", new ItemAction() {
            public void execute() {
                String reply = scanner.nextLine();
                if (reply.equalsIgnoreCase("y")) {
                    suggestion.setIsApproved(true);
                    Student student = suggestion.getStudent();
                    Map<Student, Integer> committee = camp.getCommittee();
                    committee.put(student, committee.get(student) + 1);
                    displayController.setNextDisplay(
                            new Alert("Suggestion approved!", new StaffViewSuggestionMenu(scanner), scanner));
                } else {
                    displayController.setNextDisplay(
                            new Alert("Suggestion pending approval!", new StaffViewSuggestionMenu(scanner), scanner));
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
