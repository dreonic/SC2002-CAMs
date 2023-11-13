package cams.view.components;

import java.util.Map;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

public class EditNameForm extends Form {
    public EditNameForm(Scanner scanner) {
        super(scanner);

        setTitle("Edit Camp Name: \n");

        addInput(new TextBox("New Camp Name", scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();
                DisplayController displayController = DisplayController.getInstance();
                CampController campController = CampController.getInstance();
                Camp camp = campController.getCurrentCamp();
                String newName = values.get("New Camp Name");

                if (campController.getCamp(newName) != null) {
                    displayController.setNextDisplay(new Alert(
                            "Camp with this name already exists!",
                            new EditNameForm(scanner),
                            scanner));
                } else {
                    CampEditor campEditor = new CampEditor(camp);
                    campEditor.editName(newName);
                }

                displayController.setNextDisplay(new EditCampMenu(scanner));
            }
        });
    }
}
