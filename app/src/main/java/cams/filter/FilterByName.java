package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;

/**
 * The camp name filter class implementation of the {@link FilterStrategy} interface.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByName implements FilterStrategy {
    private String name;

    /**
     * Constructs the camp name filter with the criteria set to null.
     */
    public FilterByName() {
        name = null;
    }

    /**
     * Constructs the camp name filter with the provided criteria.
     *
     * @param name the camp name filter criteria
     */
    public FilterByName(String name) {
        this.name = name;
    }

    public void setCriteria(Object object) {
        this.name = (String) object;
    }

    public List<Camp> filter(List<Camp> camps) {
        List<Camp> campsByName = new ArrayList<>();

        for (Camp camp : camps) {
            if (camp.getCampInfo().getCampName().equalsIgnoreCase(name)) {
                campsByName.add(camp);
            }
        }
        return campsByName;
    }

    @Override
    public String toString() {
        return "Name: " + (name != null ? name : "");
    }
}
