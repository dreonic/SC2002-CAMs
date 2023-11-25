package cams.camp;

import java.time.LocalDate;

/**
 * Represents the date-related information for a camp, including start date,
 * end date, and registration deadline.
 * <p>
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
     * @param startDate            the start date of the camp
     * @param endDate              the end date of the camp
     * @param registrationDeadline the registration deadline for the camp
     */
    public CampDate(LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline)
            throws IllegalArgumentException {
        if (registrationDeadline.isAfter(startDate))
            throw new IllegalArgumentException("Registration deadline cannot be after start date!");
        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("Start date cannot be after end date!");

        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = registrationDeadline;
    }

    /**
     * Retrieves the start date of the camp.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date of the camp.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Retrieves the registration deadline for the camp.
     *
     * @return the registration deadline
     */
    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    /**
     * Sets the date related data of the camp.
     *
     * @param startDate            the start date of the camp
     * @param endDate              the end date of the camp
     * @param registrationDeadline the registration deadline for the camp
     * @throws IllegalStateException when the registration deadline is after the start date or the
     *                               start date is after the end date
     */
    public void setDates(LocalDate startDate, LocalDate endDate, LocalDate registrationDeadline)
            throws IllegalArgumentException {
        if (registrationDeadline.isAfter(startDate))
            throw new IllegalArgumentException("Registration deadline cannot be after start date!");
        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("Start date cannot be after end date!");

        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationDeadline = registrationDeadline;
    }
}
