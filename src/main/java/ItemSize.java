/**
 * The size of an Inventory Item
 */
public enum ItemSize {

    SMALL("No dimension is greater than one foot"),
    MEDIUM("At least one dimension is greater than one foot but no dimension is greater than three feet"),
    LARGE("At least one dimension is greater than three feet");

    private String description;

    ItemSize(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
