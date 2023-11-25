package cams.view.components.camp;

import cams.filter.CampFilterController;
import cams.filter.FilterByVisibility;
import cams.filter.FilterStrategy;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

import java.util.Scanner;

/**
 * The boundary class displaying the form to add or set the camp visibility filter when viewing camps.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByVisibilityForm extends FilterStrategyForm {
    private static FilterStrategy current = null;

    /**
     * Constructs the camp visibility filter form with the scanner used to obtain user input. The
     * visibility is set to false only if the input is "n".
     *
     * @param scanner scanner for this form
     */
    public FilterByVisibilityForm(Scanner scanner) {
        super(new FilterByVisibility(true), scanner);

        CampFilterController filterController = CampFilterController.getInstance();
        DisplayController displayController = DisplayController.getInstance();

        addInput(new TextBox("criteria", "Visibility (Y/n)", scanner));

        setAction(new ItemAction() {
            @Override
            public void execute() {
                filterStrategy.setCriteria(!getValues().get("criteria").equals("n"));
                if (current != null)
                    filterController.removeFilterStrategy(current);
                filterController.addFilterStrategy(filterStrategy);
                current = filterStrategy;
                displayController.setNextDisplay(new SetFilterCampMenu(scanner));
            }
        });
    }
}
