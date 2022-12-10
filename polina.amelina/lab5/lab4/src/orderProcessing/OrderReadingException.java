package orderProcessing;

public final class OrderReadingException extends Exception {

    public OrderReadingException(OrderReadingExceptionTypes orderReadingExceptionType, String orderName) {
        super(orderReadingExceptionType.getDescription() + " found for " + orderName);
    }
}