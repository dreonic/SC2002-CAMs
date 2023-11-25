package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;

/**
 * The camp location filter class implementation of the {@link FilterStrategy} interface.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByLocation implements FilterStrategy {
    private String location;

    /**
     * Constructs the camp location filter with the criteria set to an empty string.
     */
    public FilterByLocation() {
        location = "";
    }

    /**
     * Constructs the camp location filter with the provided criteria.
     *
     * @param location the camp location filter criteria
     */
    public FilterByLocation(String location) {
        this.location = location;
    }

    public void setCriteria(Object object) {
        this.location = (String) object;
    }

    public List<Camp> filter(List<Camp> camps) {
        List<Camp> campsAtLocation = new ArrayList<>();

        for (Camp camp : camps) {
            if (camp.getCampInfo().getLocation().equalsIgnoreCase(location)) {
                campsAtLocation.add(camp);
            }
        }
        return campsAtLocation;
    }

    @Override
    public String toString() {
        return "Location: " + (!location.isBlank() ? location : "");
    }
}
