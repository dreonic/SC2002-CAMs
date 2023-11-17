package cams.view.components;

import java.util.Scanner;

import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.TextBox;

public class EnquiryEditorForm extends Form {
    public EnquiryEditorForm(Scanner scanner, Enquiry currentEnquiry) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        setTitle("Edit enquiry:\n");

        addInput(new TextBox("New Enquiry", scanner));

        EnquiryEditor enquiryEditor = new EnquiryEditor(campController.getCurrentCamp());

        enquiryEditor.edit(currentEnquiry, getValues().get("New Enquiry"));

        displayController.setNextDisplay(new Alert("Enquiry has been updated!", new StudentViewEnquiryMenu(scanner), scanner));
    }
}
