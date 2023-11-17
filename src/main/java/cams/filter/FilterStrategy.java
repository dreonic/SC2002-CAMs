package cams.filter;

import cams.camp.Camp;

import java.util.List;

public interface FilterStrategy {

    public void setCriteria(Object object);

    public List<Camp> filter(List<Camp> camps);

    public String toString();
}
