package cams.view.components;

import cams.camp.Camp;
import cams.camp.CampFilterController;
import cams.domain.Student;
import cams.filter.FilterByUserGroup;
import cams.filter.FilterStrategy;
import cams.user.AuthController;
import cams.user.User;
import cams.view.DisplayController;
import cams.view.base.*;

import java.util.List;
import java.util.Scanner;

public class FilterCampMenu extends SelectionMenu {
    public FilterCampMenu(Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        AuthController authController = AuthController.getInstance();
        CampFilterController filterController = CampFilterController.getInstance();

        User currentUser = authController.getCurrentUser();
        StringBuilder promptBuilder = new StringBuilder(CommonElements.getStatusBar("View Camps"));
        promptBuilder.append("Current filters: ");

        if (currentUser instanceof Student) {
            filterController.addFilterStrategy(new FilterByUserGroup(currentUser.getFaculty()));
        }

        if (filterController.getFilterStrategies().isEmpty()) {
            promptBuilder.append("-");
        }
        for (FilterStrategy strat : filterController.getFilterStrategies()) {
            promptBuilder.append("[").append(strat.toString()).append("] ");
        }

        setPrompt(promptBuilder.append("\n").toString());

        addItem(new ActionableItem("Add filters", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new AddFilterCampMenu(scanner));
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
                StringBuilder campList = new StringBuilder();
                if (filteredCamps.isEmpty()) {
                    campList.append("No camps found.");
                }
                for (Camp camp : filteredCamps) {
                    campList.append(camp.getCampInfo().getCampName()).append("\n");
                }
                displayController.setNextDisplay(new Alert(
                        campList.toString(), new StaffMenu(scanner), scanner
                ));
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            @Override
            public void execute() {
                displayController.setNextDisplay(new StaffMenu(scanner));
            }
        }));
    }
}
