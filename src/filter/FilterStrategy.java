package filter;

import camp.Camp;

import java.util.ArrayList;

public interface FilterStrategy {
    public ArrayList<Camp> filter(ArrayList<Camp> camps, Object object);
}
