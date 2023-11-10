package filter;

import camp.Camp;

import java.util.ArrayList;

public class FilterByLocation implements FilterStrategy {
    ArrayList<Camp> campsAtLocation = new ArrayList<Camp>();

    public ArrayList<Camp> filter(ArrayList<Camp> camps, Object object) {
        String location = (String)object;
         for(Camp camp:camps) {
            if(camp.getCampInfo().getLocation().equalsIgnoreCase(location)) {
                campsAtLocation.add(camp);
            }
         }
         return campsAtLocation;
    }
}
