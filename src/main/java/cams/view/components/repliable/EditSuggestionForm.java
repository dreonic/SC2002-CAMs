package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Suggestion;
import cams.repliable.SuggestionEditor;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.CommitteeViewSuggestionMenu;

import java.util.Map;
import java.util.Scanner;

/**
 * The boundary class displaying a {@code Form} which the committee member 
 * can use to edit the selected {@code Suggestion} they have submitted.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class EditSuggestionForm extends Form {

    /**
     * Constructs the Edit Suggestion Form specifying the scanner to be used and the {@code Suggestion} to be edited.
     * 
     * @param scanner
     * @param suggestion
     */
    public EditSuggestionForm(Scanner scanner, Suggestion suggestion) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();

        setTitle(CommonElements.getStatusBar("Edit Suggestion") + "\n" + "\"" + suggestion.getContent() + "\"" + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n");

        addInput(new TextBox("New Suggestion", scanner));

        setAction(new ItemAction() {
            public void execute() {
                SuggestionEditor suggestionEditor = new SuggestionEditor(camp);

                Map<String, String> values = getValues();
                suggestionEditor.edit(
                        suggestion,
                        values.get("New Suggestion"));

                displayController.setNextDisplay(new Alert("Suggestion has been updated!", new CommitteeViewSuggestionMenu(scanner), scanner));
            }
        });
    }
}
