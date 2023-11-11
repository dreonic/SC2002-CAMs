package cams.filter;

import cams.camp.Camp;

import java.time.LocalDate;
import java.util.ArrayList;

public class FilterByDate implements FilterStrategy {
    private LocalDate date;
    ArrayList<Camp> campsByDate = new ArrayList<Camp>();

    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampDate().getStartDate().equals(date)) {
                campsByDate.add(camp);
            }
        }
        return campsByDate;
    }
}
