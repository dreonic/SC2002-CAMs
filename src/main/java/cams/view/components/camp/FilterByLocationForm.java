package cams.view.components.camp;

import cams.camp.CampFilterController;
import cams.filter.FilterByLocation;
import cams.filter.FilterStrategy;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByLocationForm extends FilterStrategyForm {
    private static FilterStrategy current = null;

    public FilterByLocationForm(Scanner scanner) {
        super(new FilterByLocation(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        addInput(new TextBox("criteria", "Location", scanner));

        setAction(new ItemAction() {
            @Override
            public void execute() {
                filterStrategy.setCriteria(getValues().get("criteria"));
                if (current != null)
                    filterController.removeFilterStrategy(current);
                filterController.addFilterStrategy(filterStrategy);
                current = filterStrategy;
                displayController.setNextDisplay(new SetFilterCampMenu(scanner));
            }
        });
    }
}
