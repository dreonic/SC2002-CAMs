package cams.view.components.camp;

import cams.camp.Camp;
import cams.domain.Student;
import cams.filter.CampFilterController;
import cams.filter.FilterStrategy;
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
 * The boundary class displaying the menu to view camps and change the viewing filters. User can
 * set and clear view filters before viewing the filtered camps.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterCampMenu extends SelectionMenu {
    /**
     * Constructs the camp filter and view menu with the scanner used to obtain user input. Displays
     * the current view filters set by the user.
     *
     * @param scanner scanner for this menu
     */
    public FilterCampMenu(Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        AuthController authController = AuthController.getInstance();
        CampFilterController filterController = CampFilterController.getInstance();

        User currentUser = authController.getCurrentUser();
        StringBuilder promptBuilder = new StringBuilder(CommonElements.getStatusBar("View Camps"));
        promptBuilder.append("\nCurrent filters: ");

        if (filterController.getFilterStrategies().isEmpty()) {
            promptBuilder.append("-");
        }
        for (FilterStrategy strat : filterController.getFilterStrategies()) {
            promptBuilder.append("[").append(strat.toString()).append("] ");
        }

        setPrompt(promptBuilder.append("\n").toString());

        addItem(new ActionableItem("Set filters", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new SetFilterCampMenu(scanner));
            }
        }));

        addItem(new ActionableItem("Clear filters", new ItemAction() {
            @Override
            public void execute() {
                filterController.clearFilterStrategies();
                displayController.setNextDisplay(new FilterCampMenu(scanner));
            }
        }));

        addItem(new ActionableItem("View camps", new ItemAction() {
            @Override
            public void execute() {
                List<Camp> filteredCamps = filterController.getFilteredCamps();
                displayController.setNextDisplay(new CampListMenu(filteredCamps, scanner));
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            @Override
            public void execute() {
                if (currentUser instanceof Student) {
                    displayController.setNextDisplay(new StudentMenu(scanner));
                } else {
                    displayController.setNextDisplay(new StaffMenu(scanner));
                }
            }
        }));
    }
}
