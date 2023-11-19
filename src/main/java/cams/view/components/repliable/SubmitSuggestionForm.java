package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.SuggestionEditor;
import cams.view.DisplayController;
import cams.view.base.CommonElements;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentCommitteeMenu;

import java.util.Map;
import java.util.Scanner;

public class SubmitSuggestionForm extends Form {
    public SubmitSuggestionForm(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        DisplayController displayController = DisplayController.getInstance();
        StudentController studentController = StudentController.getInstance();
        Student currentUser = studentController.getCurrentStudent();

        setTitle(CommonElements.getStatusBar("Submit Suggestion") + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n");

        addInput(new TextBox("new-suggestion", "Enter your Suggestion", scanner));

        setAction(new ItemAction() {
            public void execute() {

                SuggestionEditor suggestionEditor = new SuggestionEditor(camp);

                Map<String, String> values = getValues();
                suggestionEditor.create(
                        values.get("new-suggestion"),
                        currentUser);

                displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
            }
        });
    }
}
