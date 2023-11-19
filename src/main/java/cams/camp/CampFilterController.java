package cams.camp;

import cams.filter.FilterStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CampFilterController {
    private static CampFilterController campFilterController;
    private final Set<FilterStrategy> filterStrategies;

    private CampFilterController() {
        filterStrategies = new HashSet<>();
    }

    public static CampFilterController getInstance() {
        if (campFilterController == null) {
            campFilterController = new CampFilterController();
        }
        return campFilterController;
    }

    public static void close() {
        campFilterController = null;
    }

    public Set<FilterStrategy> getFilterStrategies() {
        return new HashSet<>(filterStrategies);
    }

    public void addFilterStrategy(FilterStrategy strategy) {
        filterStrategies.add(strategy);
    }

    public void removeFilterStrategy(FilterStrategy strategy) {
        filterStrategies.remove(strategy);
    }

    public void clearFilterStrategies() {
        filterStrategies.clear();
    }

    public List<Camp> getFilteredCamps() {
        CampController campController = CampController.getInstance();
        List<Camp> filteredCamps = campController.getAllCamps();

        for (FilterStrategy filterStrategy : filterStrategies) {
            filteredCamps = filterStrategy.filter(filteredCamps);
        }
        return filteredCamps;
    }

}
