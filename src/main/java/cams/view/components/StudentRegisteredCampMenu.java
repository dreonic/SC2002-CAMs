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

public class StudentRegisteredCampMenu extends SelectionMenu {
    public StudentRegisteredCampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        StudentController studentController = StudentController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        Student currentUser = studentController.getCurrentStudent();

        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()));

        addItem(new ActionableItem("Withdraw", new ItemAction() {
            public void execute() {
                currentUser.removeCamp(camp);
                camp.removeAttendee(currentUser);
                displayController.setNextDisplay(new Alert("Withdrawn from Camp successfully.", new StudentViewRegisteredCampMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Submit Enquiry", new ItemAction() {
            public void execute() {
                
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentViewRegisteredCampMenu(scanner));
            }
        }));

    }
}
