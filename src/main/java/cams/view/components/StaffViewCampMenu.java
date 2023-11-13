package cams.view.components;

import java.util.ArrayList;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Staff;
import cams.domain.StaffController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StaffViewCampMenu extends SelectionMenu{
    public StaffViewCampMenu(Scanner scanner) {
        super(scanner);
        StaffController staffController = StaffController.getInstance();
        CampController campController = CampController.getInstance();
        DisplayController controller = DisplayController.getInstance();
        Staff currentUser = staffController.getCurrenStaff();
        ArrayList<Camp> campsCreated = currentUser.getCamps();

        for(Camp camp:campsCreated) {
            addItem(new ActionableItem(camp.getCampInfo().getCampName(), new ItemAction() {
                public void execute() {
                    campController.setCurrentCamp(camp);
                    controller.setNextDisplay(new StaffCampMenu(scanner));
                }
            }));
        }
    }
}
