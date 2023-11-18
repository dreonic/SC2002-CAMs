package cams.view.components.camp;

import cams.camp.CampFilterController;
import cams.filter.FilterByStaff;
import cams.filter.FilterStrategy;
import cams.user.UserController;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByStaffForm extends FilterStrategyForm {
    private static FilterStrategy current = null;

    public FilterByStaffForm(Scanner scanner) {
        super(new FilterByStaff(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        UserController userController = UserController.getInstance();

        addInput(new TextBox("criteria", "Staff user ID", scanner));

        setAction(new ItemAction() {
            @Override
            public void execute() {
                filterStrategy.setCriteria(userController.getUser(getValues().get("criteria")));
                if (current != null)
                    filterController.removeFilterStrategy(current);
                filterController.addFilterStrategy(filterStrategy);
                current = filterStrategy;
                displayController.setNextDisplay(new SetFilterCampMenu(scanner));
            }
        });
    }
}
