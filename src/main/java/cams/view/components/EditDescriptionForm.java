package cams.view.components;

import java.util.Map;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.view.DisplayController;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

public class EditDescriptionForm extends Form {
    public EditDescriptionForm(Scanner scanner) {
        super(scanner);

        setTitle("Edit Camp Description: \n");

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

                displayController.setNextDisplay(new EditCampMenu(scanner));
            }
        });
    }
}
