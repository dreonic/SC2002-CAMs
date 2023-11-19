package cams.view.components.repliable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.Suggestion;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.student.StudentCommitteeMenu;

public class CommitteeViewSuggestionMenu extends SelectionMenu {
    public CommitteeViewSuggestionMenu(Scanner scanner) {
        super(scanner);
        StudentController studentController = StudentController.getInstance();
        Student currentUser = studentController.getCurrentStudent();
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        List<Suggestion> suggestionsList = new ArrayList<Suggestion>(camp.getSuggestions());

        setPrompt(CommonElements.getStatusBar("View Suggestions"));

        for(Suggestion suggestion:suggestionsList) {
            if(suggestion.getStudent() == currentUser) {
                addItem(new ActionableItem("\"" + suggestion.getContent() + "\"", new ItemAction() {
                public void execute() {
                    displayController.setNextDisplay(new CommitteeSuggestionMenu(scanner, suggestion));
                }
            }));
            }
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
            }
        }));

    }
}
