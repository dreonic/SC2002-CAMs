package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;

public class FilterByName implements FilterStrategy {
    private String name;

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
}
