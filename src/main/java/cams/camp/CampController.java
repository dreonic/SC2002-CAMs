package cams.camp;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import cams.domain.Staff;
import cams.domain.Student;
import cams.serializer.CampSerializer;

/**
 * The {@code CampController} class manages camps and provides functionality
 * for creating, retrieving, updating, and deleting camps.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class CampController {
    private static CampController campController;
    private Map<String, Camp> campTable;
    private Camp currentCamp;

    /**
     * Private constructor to ensure singleton pattern.
     */
    private CampController() {
        campTable = new HashMap<String, Camp>();
        currentCamp = null;
    }

    /**
     * Returns the singleton instance of the {@code CampController}.
     *
     * @return The {@code CampController} instance.
     */
    public static CampController getInstance() {
        if (campController == null) {
            campController = new CampController();
            CampSerializer.deserialize();
        }
        return campController;
    }

    public static void close() {
        CampSerializer.serialize();
        CampSerializer.serialize();
        campController = null;
    }

    /**
     * Retrieves the whole camp table.
     *
     * @returns The camp table {@code HashMap} containing all created camps
     */
    public Map<String, Camp> getCampTable() {
        return campTable;
    }

    /**
     * Creates a new camp and adds it to the camp table.
     *
     * @param campName             The name of the camp.
     * @param location             The location of the camp.
     * @param description          The description of the camp.
     * @param startDate            The start date of the camp.
     * @param endDate              The end date of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     * @param totalSlots           The total available slots for the camp.
     * @param isVisible            Indicates whether the camp is visible.
     * @param userGroup            The user group associated with the camp.
     * @param staffInCharge
     */
    public void createCamp(String campName, String location, String description, LocalDate startDate,
            LocalDate endDate, LocalDate registrationDeadline, int totalSlots,
            boolean isVisible, String userGroup, Staff staffInCharge) {
        Camp newCamp = new Camp(campName, location, description, startDate, endDate, registrationDeadline, totalSlots,
                isVisible, userGroup, staffInCharge);
        campTable.put(campName.toLowerCase(), newCamp);
        staffInCharge.addCamp(newCamp);
    }

    /**
     * Retrieves an array of all camps in the camp table.
     *
     * @return An array of {@code Camp} objects.
     */
    public List<Camp> getAllCamps() {
        List<Camp> campList = new ArrayList<Camp>();
        for (Map.Entry<String, Camp> camp : campTable.entrySet()) {
            campList.add(camp.getValue());
        }
        return campList;
    }

    /**
     * Retrieves a specific camp by name.
     *
     * @param name The name of the camp to retrieve.
     * @return The {@code Camp} object with the specified name, or {@code null} if
     *         not found.
     */
    public Camp getCamp(String name) {
        return campTable.get(name.toLowerCase());
    }

    /**
     * Deletes a camp with the specified name from the camp table.
     *
     * @param name The name of the camp to delete.
     */
    public void deleteCamp(String name) {
        if (campTable.get(name).getAttendees().isEmpty())
            campTable.remove(name.toLowerCase());
    }

    /**
     * Updates the name of a camp in the camp table.
     *
     * @param oldName The current name of the camp.
     * @param newName The new name for the camp.
     */
    protected void updateName(String oldName, String newName) {
        if (campTable.containsKey(oldName)) {
            Camp camp = campTable.get(oldName);
            campTable.remove(oldName);
            campTable.put(newName, camp);
        }
    }

    /**
     * Gets a performance report for all students in the camps.
     *
     * @return A {@code HashMap} containing the performance report.
     */
    public Map<Student, Integer> getPerformanceReport(String campName) {
        Map<Student, Integer> performanceReport = new HashMap<Student, Integer>(
                campTable.get(campName).getCommittee());
        return performanceReport;
    }

    /**
     * Gets the attendance list for all students in the camps.
     *
     * @return A {@code Set} containing the attendance list.
     */
    public List<Student> getAttendanceList(String campName) {
        Camp camp = campTable.get(campName);
        List<Student> attendanceList = camp.getAttendees();
        return attendanceList;
    }

    public void setCurrentCamp(Camp camp) {
        currentCamp = camp;
    }

    public Camp getCurrentCamp() {
        return currentCamp;
    }
}