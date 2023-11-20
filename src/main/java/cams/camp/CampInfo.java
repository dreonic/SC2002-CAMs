package cams.camp;

/**
 * The {@code CampInfo} class represents information about a camp, including its
 * name,
 * location, description, total slots, committee slots, and visibility status.
 * <p>
 * Instances of this class encapsulate the editable details of a camp.
 * These details can be retrieved and modified using getter and setter methods
 * accessed through {@code CampEditor}.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class CampInfo {
    private String campName;
    private String location;
    private String description;
    private int totalSlots;
    private int committeeSlots;
    private boolean isVisible;

    /**
     * Constructs a new {@code CampInfo} object with the specified details.
     *
     * @param campName       The name of the camp.
     * @param location       The location of the camp.
     * @param description    The description of the camp.
     * @param totalSlots     The total available slots for the camp.
     * @param committeeSlots The number of slots reserved for the committee.
     * @param isVisible      Indicates whether the camp is visible.
     */
    public CampInfo(String campName, String location, String description,
                    int totalSlots, int committeeSlots, boolean isVisible) throws IllegalArgumentException {
        if (campName.isBlank()) {
            throw new IllegalArgumentException("Camp name can't be empty!");
        }
        this.campName = campName;

        if (location.isBlank()) {
            throw new IllegalArgumentException("Camp location can't be empty!");
        }
        this.location = location;

        if (description.isBlank()) {
            throw new IllegalArgumentException("Camp description can't be empty!");
        }
        this.description = description;
        this.totalSlots = totalSlots;
        this.committeeSlots = committeeSlots;
        this.isVisible = isVisible;
    }

    /**
     * Retrieves the name of the camp.
     *
     * @return The camp name.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Sets the name of the camp.
     *
     * @param campName The new name for the camp.
     */
    public void setCampName(String campName) {
        if (campName.isBlank()) {
            throw new IllegalArgumentException("Camp name can't be empty!");
        }
        this.campName = campName;
    }

    /**
     * Retrieves the location of the camp.
     *
     * @return The camp location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the camp.
     *
     * @param location The new location for the camp.
     */
    public void setLocation(String location) {
        if (location.isBlank()) {
            throw new IllegalArgumentException("Camp location can't be empty!");
        }
        this.location = location;
    }

    /**
     * Retrieves the description of the camp.
     *
     * @return The camp description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the camp.
     *
     * @param description The new description for the camp.
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("Camp description can't be empty!");
        }
        this.description = description;
    }

    /**
     * Retrieves the total available slots for the camp.
     *
     * @return The total available slots.
     */
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * Sets the total available slots for the camp.
     *
     * @param totalSlots The new total available slots.
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    /**
     * Retrieves the number of slots reserved for the committee.
     *
     * @return The number of committee slots.
     */
    public int getCommitteeSlots() {
        return committeeSlots;
    }

    /**
     * Sets the number of slots reserved for the committee.
     *
     * @param committeeSlots The new number of committee slots.
     */
    public void setCommitteeSlots(int committeeSlots) {
        this.committeeSlots = committeeSlots;
    }

    /**
     * Retrieves the visibility status of the camp.
     *
     * @return {@code true} if the camp is visible, {@code false} otherwise.
     */
    public boolean getIsVisible() {
        return isVisible;
    }

    /**
     * Sets the visibility status of the camp.
     *
     * @param isVisible {@code true} to make the camp visible, {@code false}
     *                  otherwise.
     */
    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
