package cams.view.components.student;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.user.AuthController;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.auth.ChangePasswordForm;
import cams.view.components.auth.LogoutAlert;
import cams.view.components.camp.FilterCampMenu;

import java.util.Scanner;

/**
 * The boundary class displaying menu options for the Student to select.
 * This serves as the homepage for Students.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class StudentMenu extends SelectionMenu {

    /**
     * Constructs the Student Menu specifying the scanner to be used.
     * 
     * @param scanner scanner for this menu
     */
    public StudentMenu(Scanner scanner) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        StudentController studentController = StudentController.getInstance();
        CampController campController = CampController.getInstance();
        Student currentUser = studentController.getCurrentStudent();

        if (currentUser == null) {
            throw new IllegalStateException(
                    "No student has been assigned to the Student Controller class");
        }

        setPrompt(CommonElements.getHeader("Student") + "\nWelcome to CAMs, "
                + currentUser.getName() + "!\n");

        addItem(new ActionableItem("Change Password", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new ChangePasswordForm(scanner));
            }
        }));

        addItem(new ActionableItem("View All Camps", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new FilterCampMenu(scanner));
            }
        }));

        addItem(new ActionableItem("View Registered Camps", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentViewRegisteredCampMenu(scanner));
            }
        }));

        addItem(new ActionableItem("View Enquiries", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentViewEnquiryMenu(scanner));
            }
        }));

        if (currentUser.getCommitteeFor() != null) {
            Camp committeeCamp = currentUser.getCommitteeFor();
            addItem(new ActionableItem("View Committee Camp", new ItemAction() {
                public void execute() {
                    campController.setCurrentCamp(committeeCamp);
                    displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
                }
            }));
        }

        addItem(new ActionableItem("Logout", new ItemAction() {
            public void execute() {
                AuthController authController = AuthController.getInstance();
                authController.logout();
                displayController.setNextDisplay(new LogoutAlert(scanner));
            }
        }));
    }
}
