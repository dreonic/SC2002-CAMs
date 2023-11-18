package cams.view.components.camp;

import cams.camp.CampFilterController;
import cams.filter.FilterByVisibility;
import cams.filter.FilterStrategy;
import cams.user.UserController;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByVisibilityForm extends FilterStrategyForm {
    private static FilterStrategy current = null;

    public FilterByVisibilityForm(Scanner scanner) {
        super(new FilterByVisibility(true), scanner);


        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        UserController userController = UserController.getInstance();

        addInput(new TextBox("criteria", "Visibilty (Y/n)", scanner));

        setAction(new ItemAction() {
            @Override
            public void execute() {
                filterStrategy.setCriteria(!getValues().get("criteria").equals("n"));
                if (current != null)
                    filterController.removeFilterStrategy(current);
                filterController.addFilterStrategy(filterStrategy);
                current = filterStrategy;
                displayController.setNextDisplay(new SetFilterCampMenu(scanner));
            }
        });
    }
}
