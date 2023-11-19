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

import java.util.Map;
import java.util.Scanner;

public class EditSuggestionForm extends Form {
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
