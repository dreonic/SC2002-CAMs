package cams.view.components.staff;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Suggestion;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.repliable.StaffSuggestionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The boundary class responsible for displaying a list of {@code Suggestion} 
 * regarding the specified {@code Camp} they are in charge of.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class StaffViewSuggestionMenu extends SelectionMenu {

    /**
     * Constructs the Staff View Suggestion Menu specifying the scanner to be used.
     * 
     * @param scanner scanner for this menu
     */
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
                    if (suggestion.getIsApproved()) {
                        displayController.setNextDisplay(new Alert("Suggestion already approved!", new StaffViewSuggestionMenu(scanner), scanner));
                    } else {
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
