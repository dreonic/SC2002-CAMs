package cams.view.components.camp;

import cams.filter.FilterStrategy;
import cams.view.base.CommonElements;
import cams.view.base.Form;

import java.util.Scanner;

public class FilterStrategyForm extends Form {
    protected FilterStrategy filterStrategy;

    public FilterStrategyForm(FilterStrategy filterStrategy, Scanner scanner) {
        super(scanner);
        this.filterStrategy = filterStrategy;
        String filterName = filterStrategy.toString().substring(0, filterStrategy.toString().indexOf(':'));
        setTitle(CommonElements.getStatusBar("Set " + filterName + " Filter"));
    }
}
