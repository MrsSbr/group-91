package orderProcessing;

import methodNameGetter.MethodNameGetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

public final class OrderReader {
    private static final Logger logger = Logger.getGlobal();
    private String dateTimeFormat = "dd.MM.yyyy HH:mm";
    private String durationFormat = "HH:mm:ss";

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

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName(), new Object[] {filename, dateTimeFormat, durationFormat});
        List<Order> listOfOrders = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename)).useDelimiter(";")) {

            while (scanner.hasNext()) {
                String name = scanner.next();
                logger.info("Got order name: " + name);

                if (!scanner.hasNext()) {
                    throw new OrderReadingException(OrderReadingExceptionTypes.NO_DATE, name);
                }

                String dateString = scanner.next();
                logger.info("Got unprocessed order date: " + dateString);
                LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(dateTimeFormat));
                logger.info("Got order date: " + date);

                if (!scanner.hasNext()) {
                    throw new OrderReadingException(OrderReadingExceptionTypes.NO_PREP_TIME, name);
                }

                String prepTimeString = scanner.next();
                logger.info("Got unprocessed prep time: " + prepTimeString);
                LocalTime prepTime = LocalTime.parse(prepTimeString, DateTimeFormatter.ofPattern(durationFormat));
                logger.info("Got prep time: " + prepTime);

                if (!scanner.hasNextDouble()) {
                    throw new OrderReadingException(OrderReadingExceptionTypes.NO_COST, name);
                }

                double cost = scanner.nextDouble();
                logger.info("Got order cost: " + cost);

                Order order = new Order(name, date, prepTime, cost);
                listOfOrders.add(order);
                logger.info("Got order: " + order);

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } catch (OrderReadingException | FileNotFoundException | DateTimeParseException e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
        return listOfOrders;
    }
}