package seedu.address.model.tag;

public class Interest extends Tag {

    public Interest(String tagName) {
        super(tagName);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '{' + tagName + '}';
    }
}