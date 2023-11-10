package cams.filter;

import cams.camp.Camp;

import java.time.LocalDate;
import java.util.ArrayList;

public class FilterByDate implements FilterStrategy {
    ArrayList<Camp> campsByDate = new ArrayList<Camp>();

    public ArrayList<Camp> filter(ArrayList<Camp> camps, Object object) {
        LocalDate date = (LocalDate) object;
        for (Camp camp : camps) {
            if (camp.getCampDate().getStartDate().equals(date)) {
                campsByDate.add(camp);
            }
        }
        return campsByDate;
    }
}
