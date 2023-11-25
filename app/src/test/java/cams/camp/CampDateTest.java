package cams.camp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CampDateTest {
    @Test
    @DisplayName("Registration deadline cannot be after start date")
    void registrationDeadlineAfterStartDateThrows() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new CampDate(
                    LocalDate.parse("17-11-2023", formatter),
                    LocalDate.parse("20-11-2023", formatter),
                    LocalDate.parse("18-11-2023", formatter));
        });

        assertEquals("Registration deadline cannot be after start date!", e.getMessage());
    }

    @Test
    @DisplayName("Registration deadline cannot be after start date")
    void startDateAfterEndDateThrows() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new CampDate(
                    LocalDate.parse("17-11-2023", formatter),
                    LocalDate.parse("15-11-2023", formatter),
                    LocalDate.parse("16-11-2023", formatter));
        });

        assertEquals("Start date cannot be after end date!", e.getMessage());
    }
}
