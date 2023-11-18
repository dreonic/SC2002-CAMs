package cams.view.components.camp;

import cams.camp.CampFilterController;
import cams.domain.Staff;
import cams.domain.Student;
import cams.filter.FilterByUserGroup;
import cams.filter.FilterByVisibility;
import cams.filter.FilterStrategy;
import cams.user.AuthController;
import cams.user.User;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.Scanner;

public class SetFilterCampMenu extends SelectionMenu {
    public SetFilterCampMenu(Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        AuthController authController = AuthController.getInstance();
        CampFilterController filterController = CampFilterController.getInstance();

        User currentUser = authController.getCurrentUser();
        StringBuilder promptBuilder = new StringBuilder(CommonElements.getStatusBar("Add Filters"));
        promptBuilder.append("Current filters: ");

        if (currentUser instanceof Student) {
            filterController.clearFilterStrategies();
            filterController.addFilterStrategy(new FilterByVisibility(true));
            filterController.addFilterStrategy(new FilterByUserGroup(currentUser.getFaculty()));
        }

        if (filterController.getFilterStrategies().isEmpty()) {
            promptBuilder.append("-");
        } else {
            promptBuilder.append("\n");
        }
        for (FilterStrategy filterStrategy : filterController.getFilterStrategies()) {
            promptBuilder.append("[").append(filterStrategy.toString()).append("] ");
        }

        setPrompt(promptBuilder.append("\n").toString());

        addItem(new ActionableItem("Set name filter", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new FilterByNameForm(scanner));
            }
        }));

        addItem(new ActionableItem("Set date filter", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new FilterByDateForm(scanner));
            }
        }));

        addItem(new ActionableItem("Set location filter", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new FilterByLocationForm(scanner));
            }
        }));

        addItem(new ActionableItem("Set staff-in-charge filter", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new FilterByStaffForm(scanner));
            }
        }));

        if (currentUser instanceof Staff) {
            addItem(new ActionableItem("Set user group filter", new ItemAction() {
                @Override
                public void execute() {
                    displayController.setNextDisplay(new FilterByUserGroupForm(scanner));
                }
            }));

            addItem(new ActionableItem("Set visibility filter", new ItemAction() {
                @Override
                public void execute() {
                    displayController.setNextDisplay(new FilterByVisibilityForm(scanner));
                }
            }));
        }
        addItem(new ActionableItem("Back", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new FilterCampMenu(scanner));
            }
        }));
    }
}