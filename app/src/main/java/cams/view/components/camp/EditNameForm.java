package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.domain.Staff;
import cams.domain.StaffController;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.*;

import java.util.Map;
import java.util.Scanner;

/**
 * The boundary class displaying the form for editing camp name.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class EditNameForm extends Form {
    /**
     * Constructs the camp name editing form with the scanner used to obtain user input. Displays an
     * alert to confirm successful name edit or to inform that the camp name is invalid because
     * another camp already has the same name.
     *
     * @param scanner scanner for this form
     */
    public EditNameForm(Scanner scanner) {
        super(scanner);

        setTitle(CommonElements.getStatusBar("Edit Camp Name"));

        addInput(new TextBox("New Camp Name", scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();
                DisplayController displayController = DisplayController.getInstance();
                CampController campController = CampController.getInstance();
                Camp camp = campController.getCurrentCamp();
                String newName = values.get("New Camp Name");
                StaffController staffController = StaffController.getInstance();
                Staff currentUser = staffController.getCurrentStaff();

                if (campController.getCamp(newName) != null) {
                    displayController.setNextDisplay(new Alert(
                            "Camp with this name already exists!",
                            new EditNameForm(scanner),
                            scanner));
                } else {
                    CampEditor campEditor = new CampEditor(camp);
                    campEditor.editName(newName, currentUser);
                    displayController.setNextDisplay(new Alert(
                            "Camp name changed successfully!",
                            new EditCampMenu(scanner),
                            scanner));
                }
            }
        });
    }
}
