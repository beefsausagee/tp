package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a Schedule in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSchedName(String)},
 * timings are valid as declared in {@link #isValidTiming(LocalDateTime, LocalDateTime)}
 */
public class Schedule {

    public static final String MESSAGE_CONSTRAINTS = "Schedule names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String schedName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final ArrayList<Person> participants;

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedName A valid schedule name
     * @param startTime A valid start time
     * @param endTime A valid end time
     */

    public Schedule(String schedName, LocalDateTime startTime,
                    LocalDateTime endTime) {
        requireNonNull(schedName);
        checkArgument(isValidSchedName(schedName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidTiming(startTime, endTime));
        this.schedName = schedName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = null;
    }

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedName A valid schedule name
     * @param startTime A valid start time
     * @param endTime A valid end time
     * @param participants A list of people who are related to schedule
     */
    public Schedule(String schedName, LocalDateTime startTime,
                    LocalDateTime endTime, ArrayList<Person> participants) {
        requireNonNull(schedName);
        checkArgument(isValidSchedName(schedName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidTiming(startTime, endTime));
        this.schedName = schedName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public String getSchedName() {
        return schedName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ArrayList<Person> getParticipants() {
        return participants;
    }

    /**
     * Returns true if a given string is a valid schedule name.
     */
    public static boolean isValidSchedName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if startTime before endTime
     */
    public static boolean isValidTiming(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isBefore(endTime);
    }

    /**
     * Returns true if both schedules have the same identity and data fields.
     * This defines a stronger notion of equality between two schedules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSched = (Schedule) other;
        return schedName.equals(otherSched.schedName)
                && startTime.equals(otherSched.startTime)
                && endTime.equals(otherSched.endTime)
                && participants.equals(otherSched.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedName, startTime, endTime, participants);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return schedName
                + " start " + startTime.toString()
                + " end " + endTime.toString();
    }

}
