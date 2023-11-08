package camp;

public class CampInfo {
    private String campName;
    private String location;
    private String description;
    private int totalSlots;
    private int committeeSlots;
    private boolean isVisible;

    public CampInfo(String campName, String location, String description, int totalSlots, int committeeSlots, boolean isVisible) {
        this.campName = campName;
        this.location = location;
        this.description = description;
        this.totalSlots = totalSlots;
        this.committeeSlots = committeeSlots;
        this.isVisible = isVisible;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getCommitteeSlots() {
        return committeeSlots;
    }

    public void setCommitteeSlots(int committeeSlots) {
        this.committeeSlots = committeeSlots;
    }

    public boolean getisVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
