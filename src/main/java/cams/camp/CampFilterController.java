package cams.camp;

import cams.filter.FilterStrategy;

import java.util.*;

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

    public void addFilterStrategy(FilterStrategy strategy) {
        filterStrategies.add(strategy);
    }

    public void removeFilterStrategy(FilterStrategy strategy) {
        filterStrategies.remove(strategy);
    }

    public void clearFilterStrategies() { filterStrategies.clear(); }

    public List<Camp> filter(String faculty) {
        CampController campController = CampController.getInstance();
        List<Camp> allCamps = campController.getAllCamps();
        List<Camp> filteredList = new ArrayList<>();

        for (Camp camp : allCamps) {
            if (faculty.equals(camp.getUserGroup()))
                filteredList.add(camp);
        }
        for (FilterStrategy strategy : filterStrategies) {
            strategy.filter(filteredList);
        }

        return filteredList;
    }

}
