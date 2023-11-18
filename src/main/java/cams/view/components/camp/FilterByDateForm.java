package cams.view.components.camp;

import cams.camp.CampFilterController;
import cams.filter.FilterByDate;
import cams.filter.FilterStrategy;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FilterByDateForm extends FilterStrategyForm {
    private static FilterStrategy current;

    public FilterByDateForm(Scanner scanner) {
        super(new FilterByDate(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        addInput(new TextBox("criteria", "Date (dd-mm-yyyy)", scanner));

        setAction(new ItemAction() {
            @Override
            public void execute() {
                filterStrategy.setCriteria(LocalDate.parse(getValues().get("criteria"), formatter));
                if (current != null)
                    filterController.removeFilterStrategy(current);
                filterController.addFilterStrategy(filterStrategy);
                current = filterStrategy;
                displayController.setNextDisplay(new SetFilterCampMenu(scanner));
            }
        });
    }
}
