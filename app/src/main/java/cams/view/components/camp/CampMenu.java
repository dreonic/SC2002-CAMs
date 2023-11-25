package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.user.AuthController;
import cams.user.User;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.*;
import cams.view.components.repliable.SubmitEnquiryForm;
import cams.view.components.student.StudentMenu;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.u8.U8_Grids;

import java.util.Scanner;

/**
 * The boundary class displaying the current camp information and its related actions for the user.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CampMenu extends SelectionMenu {
    /**
     * Constructs the camp menu with the scanner used to obtain user input. Displays camp
     * information and related actions of the current camp selected depending on the current user.
     * If the current user is a student, displays an action to register for the camp and to submit
     * an enquiry for the camp.
     *
     * @param scanner scanner for this menu
     */
    public CampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        AuthController authController = AuthController.getInstance();
        User currentUser = authController.getCurrentUser();

        AsciiTable info = new AsciiTable();
        info.getContext().setGrid(U8_Grids.borderDouble());
        info.getContext().setWidth(75);

        info.addRule();
        info.addRow("Camp Name: ", camp.getCampInfo().getCampName());
        info.addRule();
        info.addRow("Location: ", camp.getCampInfo().getLocation());
        info.addRule();
        info.addRow("Description: ", camp.getCampInfo().getDescription());
        info.addRule();
        info.addRow("Date: ", camp.getCampDate().getStartDate().toString() + " to " + camp.getCampDate().getEndDate().toString());
        info.addRule();
        info.addRow("Available Slots: ", (camp.getCampInfo().getTotalSlots() - camp.getAttendees().size() - camp.getCommittee().size()));
        info.addRule();
        info.addRow("User Group: ", camp.getUserGroup());
        info.addRule();
        info.addRow("Staff in Charge: ", camp.getStaffInCharge().getName());
        info.addRule();

        info.getContext().setFrameLeftMargin(2);
        info.setPaddingLeftRight(1);

        String rend = info.render();
        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()) + "\n" + rend + "\n");

        if (currentUser instanceof Student) {
            addItem(new ActionableItem("Register", new ItemAction() {
                public void execute() {
                    if (camp.getCampInfo().getTotalSlots() - camp.getAttendees().size() - camp.getCommittee().size() == 0) {
                        displayController.setNextDisplay(new Alert("This camp is full!", new StudentMenu(scanner), scanner));
                    } else {
                        if (((Student) currentUser).getCamps().contains(camp)) {
                            displayController.setNextDisplay(new Alert("Already registered for this camp!", new StudentMenu(scanner), scanner));
                        } else {
                            displayController.setNextDisplay(new CampRegisterMenu(scanner));
                        }
                    }
                }
            }));

            addItem(new ActionableItem("Submit Enquiry", new ItemAction() {
                public void execute() {
                    displayController.setNextDisplay(new SubmitEnquiryForm(scanner));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new FilterCampMenu(scanner));
            }
        }));
    }
}
