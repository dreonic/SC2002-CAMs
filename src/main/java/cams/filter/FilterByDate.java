package cams.filter;

import cams.camp.Camp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The camp start date filter class implementation of the {@code FilterStrategy} interface.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class FilterByDate implements FilterStrategy {
    private LocalDate date;

    /**
     * Constructs the camp start date filter with the criteria set to null.
     */
    public FilterByDate() {
        date = null;
    }

    /**
     * Constructs the camp start date filter with the provided criteria.
     *
     * @param date the camp start date filter criteria
     */
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
        return "Date: " + (date != null ? date.toString() : "");
    }
}
