package cams.view.components.repliable;

import java.util.Map;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.user.AuthController;
import cams.user.User;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.staff.StaffViewEnquiryMenu;
import cams.view.components.student.CommitteeViewEnquiryMenu;

/**
 * The boundary class displaying a {@code Form} which the Staff
 * committee member can use to reply to the selected {@code Enquiry}.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class ReplyEnquiryForm extends Form {

    /**
     * Constructs the Reply Enquiry Form specifying the scanner to be used and the {@code Enquiry} to be replied.
     * 
     * @param scanner scanner for this form
     * @param enquiry specified enquiry to be replied
     */
    public ReplyEnquiryForm(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        AuthController authController = AuthController.getInstance();
        User currentUser = authController.getCurrentUser();

        setTitle(CommonElements.getStatusBar("Reply Enquiry") + "\n" + "\"" + enquiry.getQuestion() + "\"" + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n");

        addInput(new TextBox("Reply", scanner));

        setAction(new ItemAction() {
            public void execute() {
                EnquiryEditor enquiryEditor = new EnquiryEditor(camp);

                Map<String, String> values = getValues();
                enquiryEditor.reply(
                        enquiry,
                        values.get("Reply"));

                if(currentUser instanceof Student) {
                    camp.incrementCommitteePoint((Student) currentUser);
                    displayController.setNextDisplay(new Alert("Enquiry answered!", new CommitteeViewEnquiryMenu(scanner), scanner));
                }
                else {
                    displayController.setNextDisplay(new Alert("Enquiry answered!", new StaffViewEnquiryMenu(scanner), scanner));
                }
            }
        });
    }
}
