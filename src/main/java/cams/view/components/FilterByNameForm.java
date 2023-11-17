package cams.view.components;

import cams.camp.CampFilterController;
import cams.filter.FilterByName;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByNameForm extends FilterStrategyForm {
    public FilterByNameForm(Scanner scanner) {
        super(new FilterByName(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        addInput(new TextBox("criteria", "Camp name", scanner));
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
