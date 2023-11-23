package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.student.StudentViewEnquiryMenu;

import java.util.Map;
import java.util.Scanner;

/**
 * The boundary class displaying a {@code Form} which the Student 
 * can use to edit the selected {@code Enquiry} they have submitted.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class EditEnquiryForm extends Form {

    /**
     * Constructs the Edit Enquiry Form specifying the scanner to be used and the {@code Enquiry} to be edited.
     * 
     * @param scanner scanner for this form
     * @param enquiry specified enquiry to be edited
     */
    public EditEnquiryForm(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();

        setTitle(CommonElements.getStatusBar("Edit Enquiry") + "\n" + "\"" + enquiry.getQuestion() + "\"" + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n");

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
