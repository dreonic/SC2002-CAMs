package cams.view.components.camp;

import cams.camp.CampFilterController;
import cams.filter.FilterByUserGroup;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByUserGroupForm extends FilterStrategyForm {
    public FilterByUserGroupForm(Scanner scanner) {
        super(new FilterByUserGroup(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        addInput(new TextBox("criteria", "User group", scanner));
        setAction(new ItemAction() {
            @Override
            public void execute() {
                filterStrategy.setCriteria(getValues().get("criteria"));
                filterController.addFilterStrategy(filterStrategy);
                displayController.setNextDisplay(new AddFilterCampMenu(scanner));
            }
        });
    }
}
