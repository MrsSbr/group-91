package Task;

public class NoOrdersException extends RuntimeException {

    public NoOrdersException() {
        super("No orders");
    }
}