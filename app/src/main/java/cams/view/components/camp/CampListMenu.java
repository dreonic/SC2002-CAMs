package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.user.AuthController;
import cams.user.User;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.staff.StaffMenu;
import cams.view.components.student.StudentMenu;

import java.util.List;
import java.util.Scanner;

/**
 * The boundary class displaying all accessible camps to the user. The user can select one of the
 * camps to obtain more information and actions for the selected camp.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CampListMenu extends SelectionMenu {
    /**
     * Constructs the camp list menu based on the provided camp list and the scanner used to
     * obtain user input. The camp list is filtered based on whether the current user accessing the
     * menu is a student or staff. The displayed camp list is the result of this filter.
     *
     * @param camps   list of camps
     * @param scanner scanner for this menu
     */
    public CampListMenu(List<Camp> camps, Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        AuthController authController = AuthController.getInstance();
        User currentUser = authController.getCurrentUser();
        setPrompt(CommonElements.getStatusBar("View Camp Details"));

        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            for (Camp camp : camps) {
                if (("NTU".equalsIgnoreCase(camp.getUserGroup()) || student.getFaculty().equalsIgnoreCase(camp.getUserGroup()))
                        && camp.getCampInfo().getIsVisible() && !student.getCamps().contains(camp)) {
                    addItem(new ActionableItem(camp.getCampInfo().getCampName(), new ItemAction() {
                        @Override
                        public void execute() {
                            campController.setCurrentCamp(camp);
                            displayController.setNextDisplay(new CampMenu(scanner));
                        }
                    }));
                }
            }
        } else {
            for (Camp camp : camps) {
                addItem(new ActionableItem(camp.getCampInfo().getCampName(), new ItemAction() {
                    @Override
                    public void execute() {
                        campController.setCurrentCamp(camp);
                        displayController.setNextDisplay(new CampMenu(scanner));
                    }
                }));
            }
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            @Override
            public void execute() {
                if (authController.getCurrentUser() instanceof Student) {
                    displayController.setNextDisplay(new StudentMenu(scanner));
                } else {
                    displayController.setNextDisplay(new StaffMenu(scanner));
                }
            }
        }));
    }
}
