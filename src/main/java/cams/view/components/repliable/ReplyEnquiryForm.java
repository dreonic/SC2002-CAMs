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
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.staff.StaffViewEnquiryMenu;

public class ReplyEnquiryForm extends Form {
    public ReplyEnquiryForm(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        AuthController authController = AuthController.getInstance();
        User currentUser = authController.getCurrentUser();

        setTitle("Replying Enquiry:\n");

        addInput(new TextBox("Reply", scanner));

        setAction(new ItemAction() {
            public void execute() {
                EnquiryEditor enquiryEditor = new EnquiryEditor(camp);

                Map<String, String> values = getValues();
                enquiryEditor.reply(
                        enquiry,
                        values.get("Reply"));

                if(currentUser instanceof Student) {
                    displayController.setNextDisplay(new Alert("Enquiry answered!", new CommitteeViewEnquiryMenu(scanner), scanner));
                }
                else {
                    displayController.setNextDisplay(new Alert("Enquiry answered!", new StaffViewEnquiryMenu(scanner), scanner));
                }
            }
        });
    }
}
