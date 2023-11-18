package cams.filter;

import cams.camp.Camp;

import java.util.List;

public interface FilterStrategy {

    void setCriteria(Object object);

    List<Camp> filter(List<Camp> camps);

    String toString();
}
