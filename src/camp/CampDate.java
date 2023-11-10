package camp;

import java.time.LocalDate;

/**
 * Represents the date-related information for a camp, including start date,
 * end date, and registration deadline.
 *
 * Instances of this class encapsulate the dates of a camp's schedule.
 * Start date indicates when the camp begins, end date indicates when it ends,
 * and registration deadline is the last date for registration.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class CampDate {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationDeadline;

    /**
     * Constructs a new {@code CampDate} object with the specified dates.
     *
     * @param startDate            The start date of the camp.
     * @param endDate              The end date of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     */
    public CampDate(LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = registrationDeadline;
    }

    /**
     * Retrieves the start date of the camp.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the camp.
     *
     * @param startDate The new start date.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the camp.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the camp.
     *
     * @param endDate The new end date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the registration deadline for the camp.
     *
     * @return The registration deadline.
     */
    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    /**
     * Sets the registration deadline for the camp.
     *
     * @param registrationDeadline The new registration deadline.
     */
    public void setRegistrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }
}
