package cams.view.components.student;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.StudentController;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.*;
import cams.view.components.repliable.SubmitEnquiryForm;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.u8.U8_Grids;

import java.util.Scanner;

/**
 * The boundary class responsible for displaying menu options a Student can select
 * once they have selected a Camp they have registered for.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class StudentRegisteredCampMenu extends SelectionMenu {

    /**
     * Constructs the Student Registered Camp Menu specifying the scanner to be used.
     * 
     * @param scanner scanner for this menu
     */
    public StudentRegisteredCampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        StudentController studentController = StudentController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();

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

        addItem(new ActionableItem("Withdraw", new ItemAction() {
            public void execute() {
                studentController.withdraw(camp);
                displayController.setNextDisplay(new Alert("Withdrawn from Camp successfully.", new StudentMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Submit Enquiry", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new SubmitEnquiryForm(scanner));
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentViewRegisteredCampMenu(scanner));
            }
        }));

    }
}
