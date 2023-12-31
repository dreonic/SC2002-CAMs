package cams.view.components.camp;

import cams.domain.Staff;
import cams.filter.CampFilterController;
import cams.filter.FilterStrategy;
import cams.user.AuthController;
import cams.user.User;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.Scanner;

/**
 * The boundary class displaying the menu to set camp viewing filters. The user can choose specific
 * options within this menu to set criteria for specific filter strategies.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class SetFilterCampMenu extends SelectionMenu {
    /**
     * Constructs the filter setting menu with the scanner used to obtain user input. Displays the
     * filter strategies that can be set by the user. If the current user is a staff, then the user
     * group and visibility filter can be set.
     *
     * @param scanner scanner for this menu
     */
    public SetFilterCampMenu(Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        AuthController authController = AuthController.getInstance();
        CampFilterController filterController = CampFilterController.getInstance();

        User currentUser = authController.getCurrentUser();
        StringBuilder promptBuilder = new StringBuilder(CommonElements.getStatusBar("Add Filters"));
        promptBuilder.append("Current filters: ");

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
