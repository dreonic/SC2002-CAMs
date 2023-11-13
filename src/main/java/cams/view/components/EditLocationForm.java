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

public class EditLocationForm extends Form {
    public EditLocationForm(Scanner scanner) {
        super(scanner);

        setTitle("Edit Camp Location: \n");

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
                
                displayController.setNextDisplay(new EditCampMenu(scanner));
            }
        });
    }
}
