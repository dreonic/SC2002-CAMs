package cams.view.components.camp;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.StudentController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.student.StudentMenu;

public class CampRegisterMenu extends SelectionMenu {
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

