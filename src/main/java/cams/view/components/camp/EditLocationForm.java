package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.view.DisplayController;
import cams.view.base.*;

import java.util.Map;
import java.util.Scanner;

/**
 * The boundary class displaying the form for editing camp location.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class EditLocationForm extends Form {
    /**
     * Constructs the camp location editing form with the scanner used to obtain user input.
     * Displays an alert to confirm successful location edit.
     *
     * @param scanner scanner for this form
     */
    public EditLocationForm(Scanner scanner) {
        super(scanner);

        setTitle(CommonElements.getStatusBar("Edit Camp Location"));

        addInput(new TextBox("New Camp Location", scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();
                DisplayController displayController = DisplayController.getInstance();
                CampController campController = CampController.getInstance();
                Camp camp = campController.getCurrentCamp();
                String newLocation = values.get("New Camp Location");

                CampEditor campEditor = new CampEditor(camp);
                campEditor.editLocation(newLocation);
                displayController.setNextDisplay(new Alert("Location successfully changed!", new EditCampMenu(scanner), scanner));
            }
        });
    }
}
