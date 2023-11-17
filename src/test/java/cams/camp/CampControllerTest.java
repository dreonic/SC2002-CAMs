package cams.camp;

import cams.domain.Staff;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CampControllerTest {
    Staff campStaff = new Staff("Staff A", "staffa", "Testing Faculty", null);

    @BeforeAll
    static void initializeControllers() {
        CampController campController = CampController.getInstance();
        Staff campStaff = new Staff("Staff T", "stafft", "Testing Faculty", null);
        campController.createCamp(
                "Testing Camp", "Testing Location", "A random camp to test functionality",
                LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                100, true, "Testing Faculty", campStaff);
    }

    @Test
    @DisplayName("Creating a valid camp adds a camp to camp table")
    void createCampAddsToCampTable() {
        CampController campController = CampController.getInstance();
        String campName = "Testing Camp";
        assertDoesNotThrow(() -> {
            campController.createCamp(
                    campName, "Testing Location", "A random camp to test functionality",
                    LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"), LocalDate.parse("2023-11-15"),
                    100, true, "Testing Faculty", campStaff);
        });
        Camp addedCamp = campController.getCamp(campName);
        assertNotNull(addedCamp);
        assertEquals(campName, addedCamp.getCampInfo().getCampName());
    }

    @Test
    @DisplayName("Deleting a nonexistent camp throws")
    void deleteNonexistentCampThrows() {
        CampController campController = CampController.getInstance();
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            campController.deleteCamp("Not Testing Camp");
        });
        assertEquals("No camp found with this name!", e.getMessage());
    }

    @AfterAll
    static void closeControllers() {
        CampController.close();
    }
}
