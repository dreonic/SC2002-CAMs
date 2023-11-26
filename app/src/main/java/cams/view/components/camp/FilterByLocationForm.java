package cams.view.components.camp;

import cams.filter.CampFilterController;
import cams.filter.FilterByLocation;
import cams.filter.FilterStrategy;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

/**
 * The boundary class displaying the form to add or set the location filter when viewing camps.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByLocationForm extends FilterStrategyForm {
    private static FilterStrategy current = null;

    /**
     * Constructs the location filter form with the scanner used to obtain user input.
     *
     * @param scanner scanner for this form
     */
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
