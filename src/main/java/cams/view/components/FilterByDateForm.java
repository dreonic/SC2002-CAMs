package cams.view.components;

import cams.camp.CampFilterController;
import cams.filter.FilterByDate;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FilterByDateForm extends FilterStrategyForm {
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
                filterController.addFilterStrategy(filterStrategy);
                displayController.setNextDisplay(new AddFilterCampMenu(scanner));
            }
        });
    }
}
