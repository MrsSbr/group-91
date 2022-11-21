package Task;

public class NoWorkdayCustomersException extends RuntimeException {

    public NoWorkdayCustomersException() {
        super("No customers on weekdays");
    }
}