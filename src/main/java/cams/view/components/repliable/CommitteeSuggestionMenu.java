package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Suggestion;
import cams.repliable.SuggestionEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.student.CommitteeViewSuggestionMenu;

import java.util.Scanner;

/**
 * The boundary class displaying menu options a committee member can select
 * once they have selected a specific {@code Suggestion} they have submitted.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CommitteeSuggestionMenu extends SelectionMenu {

    /**
     * Constructs the Committee Suggestion Menu specifying the scanner to be used and the {@code Suggestion} to be altered.
     * 
     * @param scanner scanner for this menu
     * @param suggestion specified suggestion to be altered
     */
    public CommitteeSuggestionMenu(Scanner scanner, Suggestion suggestion) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        SuggestionEditor suggestionEditor = new SuggestionEditor(camp);

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

        addItem(new ActionableItem("Delete", new ItemAction() {
            public void execute() {
                if(suggestion.getIsApproved() == true) {
                    displayController.setNextDisplay(new Alert("Suggestion already approved. Cannot Delete!", new CommitteeViewSuggestionMenu(scanner), scanner));
                }
                else {
                    suggestionEditor.delete(suggestion);
                    displayController.setNextDisplay(new Alert("Suggestion has been deleted!", new CommitteeViewSuggestionMenu(scanner), scanner));
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
