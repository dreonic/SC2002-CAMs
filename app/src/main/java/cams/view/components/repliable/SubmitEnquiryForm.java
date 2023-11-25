package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.EnquiryEditor;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentMenu;

import java.util.Map;
import java.util.Scanner;

/**
 * The boundary class displaying a {@code Form} which the {@code Student}
 * can use to submit a new {@code Enquiry} to a specific {@code Camp}.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class SubmitEnquiryForm extends Form {

    /**
     * Constructs the Submit Enquiry Form specifying the scanner to be used.
     * 
     * @param scanner scanner for this form
     */
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
