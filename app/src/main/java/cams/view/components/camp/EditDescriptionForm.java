package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.*;

import java.util.Map;
import java.util.Scanner;

/**
 * The boundary class displaying the form for editing camp description.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class EditDescriptionForm extends Form {
    /**
     * Constructs the camp description editing form with the scanner used to obtain user input.
     * Displays an alert to confirm successful description edit.
     *
     * @param scanner scanner for this form
     */
    public EditDescriptionForm(Scanner scanner) {
        super(scanner);

        setTitle(CommonElements.getStatusBar("Edit Camp Description"));

        addInput(new TextBox("New Camp Description", scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();
                DisplayController displayController = DisplayController.getInstance();
                CampController campController = CampController.getInstance();
                Camp camp = campController.getCurrentCamp();
                String newDescription = values.get("New Camp Description");

                CampEditor campEditor = new CampEditor(camp);
                campEditor.editDescription(newDescription);

                displayController.setNextDisplay(new Alert("Description successfully changed!", new EditCampMenu(scanner), scanner));
            }
        });
    }
}
