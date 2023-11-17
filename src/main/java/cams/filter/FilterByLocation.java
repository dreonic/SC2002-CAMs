package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;

public class FilterByLocation implements FilterStrategy {
    private String location;

    public FilterByLocation() {
        location = null;
    }

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
        return "Location: " + location;
    }
}
