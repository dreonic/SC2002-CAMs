package cams.view.components;

import java.util.ArrayList;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Staff;
import cams.domain.StaffController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffMenu extends SelectionMenu {
    public StaffMenu(Scanner scanner) {
        super(scanner);
        StaffController staffController = StaffController.getInstance();
        Staff currentUser = staffController.getCurrenStaff();

        if (currentUser == null) {
            throw new IllegalStateException("No staff has been assigned to the Staff Controller class");
        }

        setPrompt("Welcome to CAMs, " + currentUser.getName() + "!");

        addItem(new ActionableItem("Change Password", new ItemAction() {
            public void execute() {

            }
        }));

        addItem(new ActionableItem("Create Camp", new ItemAction() {
            public void execute() {

            }
        }));

        addItem(new ActionableItem("Edit Camp", new ItemAction() {
            public void execute() {

            }
        }));

        addItem(new ActionableItem("View Camps", new ItemAction() {
            public void execute() {
                CampController campController = CampController.getInstance();
                ArrayList<Camp> allCamps = campController.getAllCamps();
                for(Camp camp:allCamps) {
                    System.out.println(camp.getCampInfo().getCampName());
                }
            }
        }));
    }
}
