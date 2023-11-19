package cams.view.components.repliable;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Suggestion;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class CommitteeSuggestionMenu extends SelectionMenu {
    public CommitteeSuggestionMenu(Scanner scanner, Suggestion suggestion) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();

        setPrompt(CommonElements.getStatusBar("Suggestion Menu") + "\n" + "\"" + suggestion.getContent() + "\"" + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n");

        addItem(new ActionableItem("Edit", new ItemAction() {
            public void execute() {
                if(suggestion.getIsApproved() == true) {
                    displayController.setNextDisplay(new Alert("Suggestion already approved. Cannot Edit!", new CommitteeViewSuggestionMenu(scanner), scanner));
                }
                else {
                    displayController.setNextDisplay(new EditSuggestionForm(scanner, suggestion));
                }
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new CommitteeViewSuggestionMenu(scanner));
            }
        }));

    }
}
