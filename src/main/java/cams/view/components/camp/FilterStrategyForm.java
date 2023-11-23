package cams.view.components.camp;

import cams.filter.FilterStrategy;
import cams.view.base.CommonElements;
import cams.view.base.Form;

import java.util.Scanner;

/**
 * The base boundary class displaying the current specified filter strategy. This class is usually
 * extended by specific form components for each filter strategy.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterStrategyForm extends Form {
    protected FilterStrategy filterStrategy;

    /**
     * Constructs the base filter strategy form with the scanner used to obtain user input. Displays
     * the current criteria of the specified filter strategy.
     *
     * @param filterStrategy filter strategy that this form manages
     * @param scanner        scanner for this form
     */
    public FilterStrategyForm(FilterStrategy filterStrategy, Scanner scanner) {
        super(scanner);
        this.filterStrategy = filterStrategy;
        String filterName = filterStrategy.toString().substring(0, filterStrategy.toString().indexOf(':'));
        setTitle(CommonElements.getStatusBar("Set " + filterName + " Filter"));
    }
}
