package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentViewEnquiryMenu;

import java.util.Map;
import java.util.Scanner;

public class EnquiryEditorForm extends Form {
    public EnquiryEditorForm(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();

        setTitle("Edit enquiry:\n");

        addInput(new TextBox("New Enquiry", scanner));

        setAction(new ItemAction() {
            public void execute() {
                EnquiryEditor enquiryEditor = new EnquiryEditor(camp);

                Map<String, String> values = getValues();
                enquiryEditor.edit(
                        enquiry,
                        values.get("New Enquiry"));

                displayController.setNextDisplay(new Alert("Enquiry has been updated!", new StudentViewEnquiryMenu(scanner), scanner));
            }
        });

    }
}
