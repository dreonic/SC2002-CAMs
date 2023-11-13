package cams.filter;

import cams.camp.Camp;

import java.util.List;
import java.util.ArrayList;

public class FilterByLocation implements FilterStrategy {
    private String location;
    List<Camp> campsAtLocation = new ArrayList<Camp>();

    public void setCriteria(Object object) {
        String location = (String) object;
        this.location = location;
    }

    public List<Camp> filter(List<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampInfo().getLocation().equalsIgnoreCase(location)) {
                campsAtLocation.add(camp);
            }
        }
        return campsAtLocation;
    }
}
