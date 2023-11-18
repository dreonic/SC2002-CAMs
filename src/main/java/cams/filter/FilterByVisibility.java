package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;
import java.util.List;

public class FilterByVisibility implements FilterStrategy {
    private boolean isVisible;

    public FilterByVisibility() {
        isVisible = true;
    }

    public FilterByVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public void setCriteria(Object object) {
        isVisible = (boolean) object;
    }

    @Override
    public List<Camp> filter(List<Camp> camps) {
        List<Camp> filteredCamps = new ArrayList<>();

        for (Camp camp : camps) {
            if (camp.getCampInfo().getIsVisible()) {
                filteredCamps.add(camp);
            }
        }
        return filteredCamps;
    }

    @Override
    public String toString() {
        return "Visibility: " + (isVisible ? "on" : "off");
    }
}
