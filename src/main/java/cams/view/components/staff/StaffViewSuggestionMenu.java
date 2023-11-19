package cams.view.components.staff;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Suggestion;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffViewSuggestionMenu extends SelectionMenu {
    public StaffViewSuggestionMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        List<Suggestion> suggestionList = new ArrayList<Suggestion>(camp.getSuggestions());

        setPrompt(CommonElements.getStatusBar("View Suggestions"));

        for (Suggestion suggestion : suggestionList) {
            addItem(new ActionableItem(suggestion.getContent(), new ItemAction() {
                public void execute() {
                    if(suggestion.getIsApproved() == true) {
                        displayController.setNextDisplay(new Alert("Suggestion already approved!", new StaffViewSuggestionMenu(scanner), scanner));
                    }
                    else {
                        displayController.setNextDisplay(new StaffSuggestionMenu(scanner, suggestion));
                    }
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
