package cams.view.components;

import cams.camp.CampFilterController;
import cams.filter.FilterByLocation;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByLocationForm extends FilterStrategyForm {
    public FilterByLocationForm(Scanner scanner) {
        super(new FilterByLocation(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        addInput(new TextBox("criteria", "Location", scanner));

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
