package cams.filter;

import cams.camp.Camp;

import java.util.ArrayList;

public interface FilterStrategy {
    public void setCriteria(Object object);
    public ArrayList<Camp> filter(ArrayList<Camp> camps);
}