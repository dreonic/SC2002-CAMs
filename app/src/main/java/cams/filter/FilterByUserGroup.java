package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The camp user group filter class implementation of the {@link FilterStrategy} interface.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByUserGroup implements FilterStrategy {
    private String userGroup;

    /**
     * Constructs the camp user group filter with the criteria set to null.
     */
    public FilterByUserGroup() {
        userGroup = null;
    }

    /**
     * Constructs the camp user group filter with the provided criteria.
     *
     * @param userGroup the camp user group filter criteria
     */
    public FilterByUserGroup(String userGroup) {
        this.userGroup = Objects.requireNonNull(userGroup).toUpperCase();
    }


    @Override
    public void setCriteria(Object object) {
        this.userGroup = Objects.requireNonNull((String) object).toUpperCase();
    }

    @Override
    public List<Camp> filter(List<Camp> camps) {
        List<Camp> filteredCamps = new ArrayList<>();

        for (Camp camp : camps) {
            if (camp.getUserGroup().equals("NTU") || camp.getUserGroup().equals(userGroup)) {
                filteredCamps.add(camp);
            }
        }
        return filteredCamps;
    }

    @Override
    public String toString() {
        return "User Group:" + (!userGroup.isBlank() ? userGroup : "");
    }
}
