package OrderProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class OrderReader {
    private static final Logger logger = Logger.getGlobal();
    private String dateTimeFormat = "dd.MM.yyyy HH:mm";
    private String durationFormat = "mm:ss";

    public OrderReader withDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
        logger.info("New date time format: " + dateTimeFormat);
        return this;
    }

    public OrderReader withDurationFormat(String durationFormat) {
        this.durationFormat = durationFormat;
        logger.info("New duration format: " + durationFormat);
        return this;
    }

    public List<Order> readOrders(String filename) throws OrderReadingException, FileNotFoundException {

        logger.entering(this.getClass().getName(), "readOrders", new Object[] {filename, dateTimeFormat, durationFormat});
        List<Order> listOfOrders = new LinkedList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.useDelimiter("; ");

            while (scanner.hasNext()) {
                String name = scanner.next();
                logger.info("Got order name: " + name);

                if (!scanner.hasNext()) {
                    throw new OrderReadingException(OrderReadingExceptionTypes.NO_DATE, name);
                }

                String dateString = scanner.next();
                LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(dateTimeFormat));
                logger.info("Got order date: " + date);

                if (!scanner.hasNext()) {
                    throw new OrderReadingException(OrderReadingExceptionTypes.NO_PREPARATION_TIME, name);
                }

                String preparationTimeString = scanner.next();
                Duration preparationTime = Duration.between(LocalTime.MIN, LocalTime.parse(preparationTimeString, DateTimeFormatter.ofPattern(durationFormat)));
                logger.info("Got preparation time: " + preparationTime);

                if (!scanner.hasNextDouble()) {
                    throw new OrderReadingException(OrderReadingExceptionTypes.NO_COST, name);
                }

                double cost = scanner.nextDouble();
                logger.info("Got order cost: " + cost);

                Order order = new Order(name, date, preparationTime, cost);
                listOfOrders.add(order);
                logger.info("Got order: " + order);
            }
        } catch (Exception e) {

            logger.throwing(this.getClass().getName(), "readOrders", e);
            throw e;
        }

        logger.exiting(this.getClass().getName(), "readOrders");
        return listOfOrders;
    }
}