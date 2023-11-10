package camp;

import java.time.LocalDate;
import java.util.HashMap;

public class CampController {
    private static CampController campController;
    private HashMap<String, Camp> campTable;

    private CampController() {
        campTable = new HashMap<>();
    }

    public static CampController getInstance() {
        if (campController == null) {
            campController = new CampController();
        }
        return campController;
    }

    public void createCamp(String campName, String location, String description, LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline, int totalSlots, boolean isVisible, String userGroup) {
        Camp newCamp = new Camp(campName, location, description, startDate, endDate, registrationDeadline, totalSlots, isVisible, userGroup);
        campTable.put(campName, newCamp);
    }

    public Camp[] getAllCamps() {
        return campTable.values().toArray(new Camp[0]);
    }

    public Camp getCamp(String name) {
        return campTable.get(name);
    }

    public void deleteCamp(String name) {
        campTable.remove(name);
    }

    protected void updateName(String oldName, String newName) {
        if (campTable.containsKey(oldName)) {
            Camp camp = campTable.get(oldName);
            campTable.remove(oldName);
            campTable.put(newName, camp);
        }
    }

    // public HashMap<Student, Integer> getPerformanceReport() {
    //     return new HashMap<>();
    // }

    // public Set<Student> getAttendanceList() {
    //     return new HashSet<>();
    // }
}

