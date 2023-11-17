package cams.view.components;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class RegisterMenu extends SelectionMenu {
    public RegisterMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        StudentController studentController = StudentController.getInstance();
        Student currentUser = studentController.getCurrentStudent();
        DisplayController displayController = DisplayController.getInstance();

        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()) + "\n" +
        "Would you like to register as an Attendee or Committee Member?");

        addItem(new ActionableItem("Attendee", new ItemAction() {
            public void execute() {
                currentUser.addCamp(camp);
                camp.addAttendee(currentUser);
                displayController.setNextDisplay(new Alert("Successfully registered as Attendee!", new StudentMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Committee Member", new ItemAction() {
            public void execute() {
                if(currentUser.getCommitteeFor() != null) {
                    displayController.setNextDisplay(new Alert("Already a committee member for another camp!", new StudentMenu(scanner), scanner));
                }
                else {
                    currentUser.addCamp(camp);
                    currentUser.setCommitteeFor(camp);
                    camp.addCommittee(currentUser);
                    displayController.setNextDisplay(new Alert("Successfully registered as Committee Member!", new StudentMenu(scanner), scanner));
                }
            }
        }));
    }
}

