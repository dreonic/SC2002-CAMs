package cams.view.components.camp;

import cams.camp.CampFilterController;
import cams.filter.FilterByName;
import cams.filter.FilterStrategy;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

public class FilterByNameForm extends FilterStrategyForm {
    private static FilterStrategy current;

    public FilterByNameForm(Scanner scanner) {
        super(new FilterByName(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        addInput(new TextBox("criteria", "Camp name", scanner));
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
