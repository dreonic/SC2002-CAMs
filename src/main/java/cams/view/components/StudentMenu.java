package cams.view.components;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.user.AuthController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StudentMenu extends SelectionMenu {
    public StudentMenu(Scanner scanner) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        StudentController studentController = StudentController.getInstance();
        CampController campController = CampController.getInstance();
        Student currentUser = studentController.getCurrentStudent();
        StringBuilder campList = new StringBuilder();

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

        if(currentUser.getCommitteeFor() != null) {
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
