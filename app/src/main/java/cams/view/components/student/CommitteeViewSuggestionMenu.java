package cams.view.components.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.Suggestion;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.repliable.CommitteeSuggestionMenu;

/**
 * The boundary class responsible for displaying a list of suggestions  
 * the committee member has submitted for them to select.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CommitteeViewSuggestionMenu extends SelectionMenu {

    /**
     * Constructs the Committee View Suggestion Menu specifying the scanner to be used.
     * 
     * @param scanner scanner for this menu
     */
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
