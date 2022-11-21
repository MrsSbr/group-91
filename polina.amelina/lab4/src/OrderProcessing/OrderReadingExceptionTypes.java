package OrderProcessing;

public enum OrderReadingExceptionTypes {
    NO_DATE("No date"),
    NO_PREP_TIME("No prep time"),
    NO_COST("No cost");

    private final String description;

    OrderReadingExceptionTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}