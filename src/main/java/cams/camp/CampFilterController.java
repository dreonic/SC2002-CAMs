package cams.camp;

import cams.filter.FilterStrategy;
import java.util.List;
import java.util.ArrayList;

public class CampFilterController {
    private static CampFilterController campFilterController;
    private List<FilterStrategy> filterStrategies;

    private CampFilterController() {
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

    public List<FilterStrategy> getFilterStrategies() {
        return filterStrategies;
    }

    public List<Camp> filter(String faculty) {
        List<Camp> filteredList = new ArrayList<Camp>();
        CampController campController = CampController.getInstance();
        List<Camp> allCamps = campController.getAllCamps();
        for (Camp camp : allCamps) {
            if (camp.getUserGroup() == faculty)
                filteredList.add(camp);
        }
        for (FilterStrategy strategy : filterStrategies) {
            strategy.filter(filteredList);
        }
        return filteredList;
    }

}
