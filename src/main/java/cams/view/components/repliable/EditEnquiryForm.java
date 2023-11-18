package cams.view.components.repliable;

import cams.camp.Camp;
import cams.repliable.EnquiryEditor;
import cams.repliable.Repliable;
import cams.view.DisplayController;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentMenu;

import java.util.Map;
import java.util.Scanner;

public class EditEnquiryForm extends Form {
    public EditEnquiryForm(Scanner scanner, Camp currentCamp, Repliable oldRepliable) {
        super(scanner);
        setTitle("Edit Enquiry:\n");

        addInput(new TextBox("New Question", scanner));

        setAction(new ItemAction() {
            public void execute() {
                DisplayController displayController = DisplayController.getInstance();
                EnquiryEditor enquiryEditor = new EnquiryEditor(currentCamp);

                Map<String, String> values = getValues();
                enquiryEditor.edit(
                        oldRepliable,
                        values.get("New Question"));

                displayController.setNextDisplay(new StudentMenu(scanner));
            }
        });
    }

}
