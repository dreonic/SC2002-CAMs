package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.CommonElements;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentMenu;

import java.util.Map;
import java.util.Scanner;

public class SubmitEnquiryForm extends Form {
    public SubmitEnquiryForm(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        DisplayController displayController = DisplayController.getInstance();
        StudentController studentController = StudentController.getInstance();
        Student currentUser = studentController.getCurrentStudent();

        setTitle(CommonElements.getStatusBar("Submit Enquiry") + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n");

        addInput(new TextBox("Question", scanner));

        setAction(new ItemAction() {
            public void execute() {

                EnquiryEditor enquiryEditor = new EnquiryEditor(camp);

                Map<String, String> values = getValues();
                enquiryEditor.create(
                        values.get("Question"),
                        currentUser);

                displayController.setNextDisplay(new StudentMenu(scanner));
            }
        });
    }

}
