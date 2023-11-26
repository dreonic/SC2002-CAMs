package cams.filter;

import cams.camp.Camp;
import cams.camp.CampController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code CampFilterController} class manages filtering strategies for camps.
 * It is designed as a singleton to ensure a single point of control for filter strategies
 * across the application.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class CampFilterController {
    /**
     * The singleton instance of the camp filter controller.
     */
    private static CampFilterController campFilterController;

    /**
     * The set of filter strategies associated with this controller.
     */
    private final Set<FilterStrategy> filterStrategies;

    /**
     * Constructs the camp filter controller with no filter strategies.
     */
    private CampFilterController() {
        filterStrategies = new HashSet<>();
    }

    /**
     * Returns the singleton instance of camp filter controller.
     *
     * @return the singleton instance of camp filter controller
     */
    public static CampFilterController getInstance() {
        if (campFilterController == null) {
            campFilterController = new CampFilterController();
        }
        return campFilterController;
    }

    /**
     * Closes the singleton instance of camp filter controller and destructures the controller.
     */
    public static void close() {
        campFilterController = null;
    }

    /**
     * Gets a copy of the set of filter strategies associated with this controller.
     *
     * @return a set of filter strategies
     */
    public Set<FilterStrategy> getFilterStrategies() {
        return new HashSet<>(filterStrategies);
    }

    /**
     * Adds a filter strategy to the set of filter strategies.
     *
     * @param strategy the filter strategy to be added
     */
    public void addFilterStrategy(FilterStrategy strategy) {
        filterStrategies.add(strategy);
    }

    /**
     * Removes a filter strategy from the set of filter strategies.
     *
     * @param strategy the filter strategy to be removed
     */
    public void removeFilterStrategy(FilterStrategy strategy) {
        filterStrategies.remove(strategy);
    }

    /**
     * Clears all filter strategies from the set.
     */
    public void clearFilterStrategies() {
        filterStrategies.clear();
    }

    /**
     * Gets a list of camps filtered based on the registered filter strategies.
     *
     * @return a list of filtered camps
     */
    public List<Camp> getFilteredCamps() {
        CampController campController = CampController.getInstance();
        List<Camp> filteredCamps = campController.getAllCamps();

        for (FilterStrategy filterStrategy : filterStrategies) {
            filteredCamps = filterStrategy.filter(filteredCamps);
        }
        return filteredCamps;
    }
}
