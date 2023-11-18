package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.Suggestion;
import cams.view.DisplayController;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentCommitteeMenu;

import java.util.Map;
import java.util.Scanner;

public class SubmitSuggestionForm extends Form {
    public SubmitSuggestionForm(Scanner scanner) {
        super(scanner);

        setTitle("Submit a new Suggestion: \n");

        addInput(new TextBox("new-suggestion", "Enter your Suggestion", scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();
                DisplayController displayController = DisplayController.getInstance();
                CampController campController = CampController.getInstance();
                StudentController studentController = StudentController.getInstance();
                Student currentUser = studentController.getCurrentStudent();
                Camp camp = campController.getCurrentCamp();
                String newSuggestion = values.get("new-suggestion");

                camp.addSuggestion(new Suggestion(newSuggestion, currentUser));
                displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
            }
        });
    }
}
