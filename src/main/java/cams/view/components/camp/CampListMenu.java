package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.user.AuthController;
import cams.user.User;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.staff.StaffMenu;
import cams.view.components.student.StudentMenu;

import java.util.List;
import java.util.Scanner;

public class CampListMenu extends SelectionMenu {
    public CampListMenu(List<Camp> camps, Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        AuthController authController = AuthController.getInstance();
        User currentUser = authController.getCurrentUser();
        setPrompt(CommonElements.getStatusBar("View Camp Details"));

        if (currentUser instanceof Student) {
            for (Camp camp : camps) {
                if (("NTU".equalsIgnoreCase(camp.getUserGroup()) || currentUser.getFaculty().equalsIgnoreCase(camp.getUserGroup()))
                    && camp.getCampInfo().getIsVisible())
                addItem(new ActionableItem(camp.getCampInfo().getCampName(), new ItemAction() {
                    @Override
                    public void execute() {
                        campController.setCurrentCamp(camp);
                        displayController.setNextDisplay(new CampMenu(scanner));
                    }
                }));
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
