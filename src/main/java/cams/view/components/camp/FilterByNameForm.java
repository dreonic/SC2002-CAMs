package cams.view.components.camp;

import cams.filter.CampFilterController;
import cams.filter.FilterByName;
import cams.filter.FilterStrategy;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

/**
 * The boundary class displaying the form to add or set the name filter when viewing camps.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByNameForm extends FilterStrategyForm {
    private static FilterStrategy current;

    /**
     * Constructs the name filter form with the scanner used to obtain user input.
     *
     * @param scanner scanner for this form
     */
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
