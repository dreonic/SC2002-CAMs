package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.repliable.Suggestion;
import cams.repliable.SuggestionEditor;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.staff.StaffViewSuggestionMenu;

import java.util.Map;
import java.util.Scanner;

/**
 * The boundary class displaying a {@code Form} which the Staff
 * can use to approve the selected {@code Suggestion}.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class StaffSuggestionMenu extends Form {

    /**
     * Constructs the Staff Suggestion Menu specifying the scanner to be used and the {@code Suggestion} to be approved.
     * 
     * @param scanner
     * @param suggestion
     */
    public StaffSuggestionMenu(Scanner scanner, Suggestion suggestion) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        SuggestionEditor suggestionEditor = new SuggestionEditor(camp);
        DisplayController displayController = DisplayController.getInstance();
        Student student = suggestion.getStudent();

        setTitle(CommonElements.getStatusBar("Approve Suggestion") + "\n" + "\"" + suggestion.getContent() + "\"" + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n" );

        addInput(new TextBox("approval", "Approve? (Y/n)", scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();

                if(values.get("approval").equalsIgnoreCase("Y")) {
                    camp.incrementCommitteePoint(student);
                    suggestionEditor.reply(suggestion, "Approve");
                    displayController.setNextDisplay(new Alert("Suggestion approved!", new StaffViewSuggestionMenu(scanner), scanner));
                }
                else {
                    displayController.setNextDisplay(new Alert("Suggestion pending approval...", new StaffViewSuggestionMenu(scanner), scanner));
                }
            }
        });


    }
}
