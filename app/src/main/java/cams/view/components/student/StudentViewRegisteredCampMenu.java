package cams.view.components.student;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.List;
import java.util.Scanner;

/**
 * The boundary class responsible for displaying a list of Camps the Student
 * has registered for. The Student will then be able to select one of these Camps.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class StudentViewRegisteredCampMenu extends SelectionMenu {

    /**
     * Constructs the Student View Registered Camp Menu specifying the scanner to be used.
     * 
     * @param scanner scanner for this menu
     */
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
