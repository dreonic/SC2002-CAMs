package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.StudentController;
import cams.view.DisplayController;
import cams.view.base.*;
import cams.view.components.student.StudentMenu;

import java.util.Scanner;

/**
 * The boundary class displaying the menu which allows a student to register for a camp either as an
 * attendee or a committee.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CampRegisterMenu extends SelectionMenu {
    /**
     * Constructs the camp registration menu with the scanner used to obtain user input. A
     * student can either register as an attendee or a committee. Displays an alert if a student
     * tries to register as a committee but is already a committee for another camp.
     *
     * @param scanner scanner for this menu
     */
    public CampRegisterMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        StudentController studentController = StudentController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()) + "\n" +
                "Would you like to register as an Attendee or Committee Member?");

        addItem(new ActionableItem("Attendee", new ItemAction() {
            public void execute() {
                try {
                    studentController.register(camp, false);
                    displayController.setNextDisplay(new Alert("Successfully registered as Attendee!", new StudentMenu(scanner), scanner));
                } catch (RuntimeException e) {
                    displayController.setNextDisplay(new Alert(e.getMessage(), new StudentMenu(scanner), scanner));
                }
            }
        }));

        addItem(new ActionableItem("Committee Member", new ItemAction() {
            public void execute() {
                try {
                    studentController.register(camp, true);
                    displayController.setNextDisplay(new Alert("Successfully registered as Committee Member!", new StudentMenu(scanner), scanner));
                } catch (RuntimeException e) {
                    displayController.setNextDisplay(new Alert(e.getMessage(), new StudentMenu(scanner), scanner));
                }
            }
        }));
    }
}

