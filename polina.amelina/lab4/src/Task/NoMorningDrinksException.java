package Task;

public class NoMorningDrinksException extends RuntimeException {

    public NoMorningDrinksException() {
        super("No drinks are being ordered in the morning");
    }
}