package cams.view.components.auth;

import cams.domain.Staff;
import cams.user.AuthController;
import cams.user.User;
import cams.view.DisplayController;
import cams.view.base.*;
import cams.view.components.staff.StaffMenu;
import cams.view.components.student.StudentMenu;

import java.util.Map;
import java.util.Scanner;

public class ChangePasswordForm extends Form {

    public ChangePasswordForm(Scanner scanner) {
        super("Change password:\n", scanner);

        AuthController authController = AuthController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        addInput(new TextBox("Old Password", true, scanner));
        addInput(new TextBox("New Password", true, scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();

                String oldPassword = values.get("Old Password");
                String newPassword = values.get("New Password");
                User currentUser = authController.getCurrentUser();

                try {
                    authController.changePassword(
                            currentUser.getUserID(), oldPassword, newPassword);

                    Displayable nextDisplay;
                    String successAlertContent = "Changed password successfully!";

                    if (authController.getCurrentUser() instanceof Staff) {
                        nextDisplay = new StaffMenu(scanner);
                    } else {
                        nextDisplay = new StudentMenu(scanner);
                    }

                    displayController.setNextDisplay(new Alert(
                            successAlertContent, nextDisplay, scanner));

                } catch (IllegalArgumentException e) {
                    displayController.setNextDisplay(new Alert(
                            "Wrong password! Please try again.",
                            new ChangePasswordForm(scanner),
                            scanner));
                }
            }
        });
    }
}
