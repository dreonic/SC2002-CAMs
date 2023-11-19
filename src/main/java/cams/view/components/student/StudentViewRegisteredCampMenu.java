package cams.view.components.student;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.List;
import java.util.Scanner;

public class StudentViewRegisteredCampMenu extends SelectionMenu {
    public StudentViewRegisteredCampMenu(Scanner scanner) {
        super(scanner);
        StudentController studentController = StudentController.getInstance();
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        Student currentUser = studentController.getCurrentStudent();
        List<Camp> campsRegistered = currentUser.getCamps();

        setPrompt(CommonElements.getStatusBar("View Registered Camps"));

        for (Camp camp : campsRegistered) {
            if (camp != currentUser.getCommitteeFor()) {
                addItem(new ActionableItem(camp.getCampInfo().getCampName(), new ItemAction() {
                    public void execute() {
                        campController.setCurrentCamp(camp);
                        displayController.setNextDisplay(new StudentRegisteredCampMenu(scanner));
                    }
                }));
            } else {
                addItem(new ActionableItem(camp.getCampInfo().getCampName(), new ItemAction() {
                    public void execute() {
                        campController.setCurrentCamp(camp);
                        displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
                    }
                }));
            }
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentMenu(scanner));
            }
        }));
    }
}
