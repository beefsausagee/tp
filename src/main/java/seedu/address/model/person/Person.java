package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Interest;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Remark remark;

    // Data fields
    private final Address address;
    private final Set<Tag> allTags = new HashSet<>();
    private final Set<Interest> interests = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    private final ArrayList<Schedule> schedules = new ArrayList<>();


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Set<Tag> tags, Set<Interest> interests,
        ArrayList<Schedule> schedules) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = new Address("");
        this.remark = null;
        if (!tags.isEmpty()) {
            this.tags.addAll(tags);
            this.allTags.addAll(tags);
        }
        if (!interests.isEmpty()) {
            this.interests.addAll(interests);
            this.allTags.addAll(interests);
        }
        if (!schedules.isEmpty()) {
            this.schedules.addAll(schedules);
        }
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Set<Tag> tags, Set<Interest> interests,
        ArrayList<Schedule> schedules, Remark remark) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = new Address("");
        this.remark = remark;
        if (!tags.isEmpty()) {
            this.tags.addAll(tags);
            this.allTags.addAll(tags);
        }
        if (!interests.isEmpty()) {
            this.interests.addAll(interests);
            this.allTags.addAll(interests);
        }
        if (!schedules.isEmpty()) {
            this.schedules.addAll(schedules);
        }
    }

    /**
     * Overloaded constructor to consider if Schedule is empty
     */
    public Person(Name name, Phone phone, Email email, Address address,
        Set<Tag> tags, Set<Interest> interests, ArrayList<Schedule> schedules) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = null;
        if (!tags.isEmpty()) {
            this.tags.addAll(tags);
            this.allTags.addAll(tags);
        }
        if (!interests.isEmpty()) {
            this.interests.addAll(interests);
            this.allTags.addAll(interests);
        }
        if (!schedules.isEmpty()) {
            this.schedules.addAll(schedules);
        }
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Remark getRemark() {
        return remark;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(allTags);
    }

    public Set<Tag> getTag() {
        return Collections.unmodifiableSet(tags);
    }

    public Set<Interest> getInterest() {
        return Collections.unmodifiableSet(interests);
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void addSchedule(Schedule s) {
        schedules.add(s);
    }

    public void deleteSchedule(Schedule s) {
        schedules.remove(s);
    }

    /**
     * Removes the Person from the participants' list by their Person Name
     * @param s Schedule to remove participant from
     * @param name Name of participant
     */
    public void removePersonfromSchedule(Schedule s, String name) {
        schedules.forEach(schedule -> {
            if (schedule.isSameSchedule(s)) {
                schedule.removePerson(name);
            }
        });
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
            && otherPerson.getName().equals(getName())
            && (otherPerson.getPhone().equals(getPhone())
                || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && schedules.equals(otherPerson.schedules);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    private String schedulesToString() {
        StringBuilder schedulesList = new StringBuilder();

        for (int i = 0; i < schedules.size(); i++) {
            schedulesList.append(i + 1).append(". ");
            schedulesList.append(schedules.get(i).toString());
            schedulesList.append("\n");

        }
        String res = schedulesList.toString();
        if (!res.isEmpty()) {
            return res.substring(0, res.length() - 2);
        }
        return res;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("tags", tags)
                .add("schedules", schedules.toString())
                .toString();
    }

}
