package cams.view.components.auth;

import cams.domain.Staff;
import cams.domain.StaffController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.user.AuthController;
import cams.user.User;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.staff.StaffMenu;
import cams.view.components.student.StudentMenu;

import java.util.Scanner;

/**
 * User login interface. Gets current user ID and its corresponding
 * candidate password. Redirects to the corresponding user menu if
 * a match is found. If no match is found, displays
 * <code>LoginErrorAlert</code> and refresh the page.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */

public class LoginForm extends Form {
    /**
     * Class constructor specifying the scanner to be used to receive user input.
     *
     * @param scanner scanner for this menu
     */
    public LoginForm(Scanner scanner) {
        super(CommonElements.getStatusBar("Login") + "Login to CAMS:\n", scanner);

        addInput(new TextBox("User ID", scanner));
        addInput(new TextBox("Password", true, scanner));

        setAction(new ItemAction() {
            public void execute() {
                AuthController authController = AuthController.getInstance();
                DisplayController displayController = DisplayController.getInstance();

                String userIDInput = getValues().get("User ID");
                String passwordInput = getValues().get("Password");

                try {
                    User currentUser = authController.login(userIDInput, passwordInput);
                    if (currentUser instanceof Staff) {
                        StaffController.getInstance((Staff) currentUser);
                        displayController.setNextDisplay(new StaffMenu(scanner));
                    } else {
                        StudentController.getInstance((Student) currentUser);
                        displayController.setNextDisplay(new StudentMenu(scanner));
                    }

                } catch (IllegalArgumentException e) {
                    displayController.setNextDisplay(
                            new LoginErrorAlert(new LoginForm(scanner), scanner));
                }
            }
        });
    }
}
