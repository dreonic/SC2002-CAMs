package cams.view.components;

import java.util.Map;
import java.util.Scanner;

import cams.camp.CampController;
import cams.domain.StudentController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

public class EnquiryEditorForm extends Form {
    public EnquiryEditorForm(Scanner scanner, Enquiry currentEnquiry) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        setTitle("Edit enquiry:\n");

        addInput(new TextBox("New Enquiry", scanner));

        EnquiryEditor enquiryEditor = new EnquiryEditor(campController.getCurrentCamp());

        enquiryEditor.edit(currentEnquiry, getTitle());

        displayController.setNextDisplay(new Alert("Suggestion has been approved", new StaffViewSuggestionMenu(scanner), scanner));


        setAction(new ItemAction() {
            public void execute() {
                StudentController studentController = StudentController.getInstance();
                DisplayController displayController = DisplayController.getInstance();
                CampController campController = CampController.getInstance();

                EnquiryEditor enquiryEditor = new EnquiryEditor(campController.getCurrentCamp());

                Map<String, String> values = getValues();

                

                enquiryEditor.create(
                        values.get("Question"),
                        studentController.getCurrentStudent());

                displayController.setNextDisplay(new StudentMenu(scanner));
            }
        });
    }
}
