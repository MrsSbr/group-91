package OrderProcessing;

public enum OrderReadingExceptionTypes {
    NO_DATE("No date"),
    NO_PREPARATION_TIME("No preparation time"),
    NO_COST("No cost");

    private final String description;

    OrderReadingExceptionTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}