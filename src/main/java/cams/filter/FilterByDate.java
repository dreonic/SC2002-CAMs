package cams.filter;

import cams.camp.Camp;

import java.time.LocalDate;
import java.util.ArrayList;

public class FilterByDate implements FilterStrategy {
    private LocalDate date;
    ArrayList<Camp> campsByDate = new ArrayList<Camp>();

    public void setCriteria(Object object) {
        LocalDate date = (LocalDate) object;
        this.date = date;
    }

    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        for (Camp camp : camps) {
            if (camp.getCampDate().getStartDate().equals(date)) {
                campsByDate.add(camp);
            }
        }
        return campsByDate;
    }
}
