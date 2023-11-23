package cams.view.components.camp;

import cams.filter.CampFilterController;
import cams.filter.FilterByDate;
import cams.filter.FilterStrategy;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The boundary class displaying the form to add or set the date filter when viewing camps.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByDateForm extends FilterStrategyForm {
    private static FilterStrategy current;

    /**
     * Constructs the date filter form with the scanner used to obtain user input.
     *
     * @param scanner scanner for this form
     */
    public FilterByDateForm(Scanner scanner) {
        super(new FilterByDate(), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        addInput(new TextBox("criteria", "Start Date (dd-mm-yyyy)", scanner));

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
