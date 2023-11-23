package cams.view.components.staff;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Staff;
import cams.domain.StaffController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.List;
import java.util.Scanner;

/**
 * The boundary class responsible for displaying a list of Camps the Staff
 * has created. The Staff will then be able to select one of these Camps.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class StaffViewCampMenu extends SelectionMenu {

    /**
     * Constructs the Staff View Camp Menu specifying the scanner to be used.
     * 
     * @param scanner
     */
    public StaffViewCampMenu(Scanner scanner) {
        super(scanner);
        StaffController staffController = StaffController.getInstance();
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        Staff currentUser = staffController.getCurrentStaff();
        List<Camp> campsCreated = currentUser.getCamps();

        setPrompt(CommonElements.getStatusBar("View Created Camps"));

        for (Camp camp : campsCreated) {
            addItem(new ActionableItem(camp.getCampInfo().getCampName(), new ItemAction() {
                public void execute() {
                    campController.setCurrentCamp(camp);
                    displayController.setNextDisplay(new StaffCampMenu(scanner));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffMenu(scanner));
            }
        }));
    }
}
