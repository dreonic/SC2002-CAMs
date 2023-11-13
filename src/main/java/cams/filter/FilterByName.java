package cams.filter;

import cams.camp.Camp;

import java.util.List;
import java.util.ArrayList;

public class FilterByName implements FilterStrategy {
    private String name;
    List<Camp> campByName = new ArrayList<Camp>();

    public void setCriteria(Object object) {
        String name = (String) object;
        this.name = name;
    }

    public List<Camp> filter(List<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampInfo().getCampName().equalsIgnoreCase(name)) {
                campByName.add(camp);
            }
        }
        return campByName;
    }
}
