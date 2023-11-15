package cams.view.components;

import java.util.List;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Suggestion;
import cams.repliable.SuggestionEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffViewSuggestionMenu extends SelectionMenu {
    public StaffViewSuggestionMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        SuggestionEditor suggestionEditor = new SuggestionEditor(camp);
        List<Suggestion> suggestionList = camp.getSuggestionsArray();

        for (Suggestion suggestion : suggestionList) {
            addItem(new ActionableItem(suggestion.getContent(), new ItemAction() {
                public void execute() {
                    suggestionEditor.setCurrentSuggestion(suggestion);
                    displayController.setNextDisplay(new StaffSuggestionMenu(scanner));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffCampMenu(scanner));
            }
        }));
    }
}
