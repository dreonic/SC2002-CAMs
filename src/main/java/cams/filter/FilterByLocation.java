package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;

public class FilterByLocation implements FilterStrategy {
    private String location;
    ArrayList<Camp> campsAtLocation = new ArrayList<Camp>();

    public void setCriteria(Object object) {
        String location = (String) object;
        this.location = location;
    }

    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampInfo().getLocation().equalsIgnoreCase(location)) {
                campsAtLocation.add(camp);
            }
        }
        return campsAtLocation;
    }
}
