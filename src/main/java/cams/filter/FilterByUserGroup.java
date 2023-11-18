package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterByUserGroup implements FilterStrategy {
    private String userGroup;

    public FilterByUserGroup() {
        userGroup = null;
    }

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
        return "User Group:" + (userGroup != null ? userGroup : "");
    }
}
