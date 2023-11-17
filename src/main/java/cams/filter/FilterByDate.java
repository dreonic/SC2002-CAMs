package cams.filter;

import cams.camp.Camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilterByDate implements FilterStrategy {
    private LocalDate date;

    public FilterByDate() {
        date = null;
    }

    public FilterByDate(LocalDate date) {
        this.date = date;
    }

    public void setCriteria(Object object) {
        this.date = (LocalDate) object;
    }

    public List<Camp> filter(List<Camp> camps) {
        List<Camp> campsByDate = new ArrayList<>();

        for (Camp camp : camps) {
            if (camp.getCampDate().getStartDate().equals(date)) {
                campsByDate.add(camp);
            }
        }
        return campsByDate;
    }

    @Override
    public String toString() {
        return "Date: " + date.toString();
    }
}
