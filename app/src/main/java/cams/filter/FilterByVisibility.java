package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;

/**
 * The camp visibility filter class implementation of the {@link FilterStrategy} interface.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByVisibility implements FilterStrategy {
    private boolean isVisible;

    /**
     * Constructs the camp visibility filter with the criteria set to visible.
     */
    public FilterByVisibility() {
        isVisible = true;
    }

    /**
     * Constructs the camp visibility filter with the provided criteria.
     *
     * @param isVisible the camp visibility filter criteria
     */
    public FilterByVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public void setCriteria(Object object) {
        isVisible = (boolean) object;
    }

    @Override
    public List<Camp> filter(List<Camp> camps) {
        List<Camp> filteredCamps = new ArrayList<>();

        for (Camp camp : camps) {
            if (camp.getCampInfo().getIsVisible()) {
                filteredCamps.add(camp);
            }
        }
        return filteredCamps;
    }

    @Override
    public String toString() {
        return "Visibility: " + (isVisible ? "on" : "off");
    }
}
