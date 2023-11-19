package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.domain.Staff;
import cams.domain.StaffController;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Map;
import java.util.Scanner;

public class EditNameForm extends Form {
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
