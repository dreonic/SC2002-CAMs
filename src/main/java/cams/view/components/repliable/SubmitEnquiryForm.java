package cams.view.components.repliable;

import java.util.Map;
import java.util.Scanner;

import cams.camp.CampController;
import cams.domain.StudentController;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentMenu;

public class SubmitEnquiryForm extends Form {
    public SubmitEnquiryForm(Scanner scanner) {
        super(scanner);
        setTitle("Submit a new Enquiry:\n");

        addInput(new TextBox("Question", scanner));

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
