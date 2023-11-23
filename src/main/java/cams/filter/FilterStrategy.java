package cams.filter;

import cams.camp.Camp;

import java.util.List;

/**
 * The interface for filtering a list of camps. A {@code FilterStrategy} represents a specific
 * strategy used to filter a list of camps. This strategy can be by visibility, location, name, etc.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public interface FilterStrategy {
    /**
     * Sets the filter criteria for this filter strategy. An implementation should downcast this
     * criteria object to the type needed for that filter strategy.
     *
     * @param object the filter criteria
     */
    void setCriteria(Object object);

    /**
     * Filters the list of camps provided using this filter strategy and returns the result.
     *
     * @param camps list of camps to be filtered
     * @return filtered list of camps
     */
    List<Camp> filter(List<Camp> camps);

    /**
     * Returns a string representation of this filter strategy, which consists of the filter
     * strategy and the criteria separated by a colon.
     *
     * @return the string representation
     */
    String toString();
}
