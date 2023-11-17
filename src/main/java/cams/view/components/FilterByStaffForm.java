package cams.view.components;

import cams.camp.CampFilterController;
import cams.filter.FilterByStaff;
import cams.user.UserController;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByStaffForm extends FilterStrategyForm {
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
                filterController.addFilterStrategy(filterStrategy);
                displayController.setNextDisplay(new AddFilterCampMenu(scanner));
            }
        });
    }
}
