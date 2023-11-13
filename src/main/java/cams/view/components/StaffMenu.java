package cams.view.components;

import java.util.List;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Staff;
import cams.domain.StaffController;
import cams.user.AuthController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffMenu extends SelectionMenu {
    public StaffMenu(Scanner scanner) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        StaffController staffController = StaffController.getInstance();
        CampController campController = CampController.getInstance();
        Staff currentUser = staffController.getCurrentStaff();
        StringBuilder campList = new StringBuilder();

        if (currentUser == null) {
            throw new IllegalStateException("No staff has been assigned to the Staff Controller class");
        }

        setPrompt(CommonElements.getHeader("Staff") + "\n"
                + "Welcome to CAMs, " + currentUser.getName() + "!\n");

        addItem(new ActionableItem("Change Password", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new ChangePasswordForm(scanner));
            }
        }));

        addItem(new ActionableItem("Create Camp", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new CreateCampForm(scanner));
            }
        }));

        addItem(new ActionableItem("View Created Camps", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffViewCampMenu(scanner));
            }
        }));

        addItem(new ActionableItem("View All Camps", new ItemAction() {
            public void execute() {
                // TODO add filter options
                List<Camp> allCamps = campController.getAllCamps();
                for (Camp camp : allCamps) {
                    campList.append(camp.getCampInfo().getCampName() + "\n");
                }
                displayController.setNextDisplay(new Alert(
                        campList.toString(), new StaffMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Logout", new ItemAction() {
            public void execute() {
                AuthController authController = AuthController.getInstance();
                authController.logout();
                displayController.setNextDisplay(new LogoutAlert(scanner));
            }
        }));
    }
}
