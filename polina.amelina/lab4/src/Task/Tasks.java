package Task;

import OrderProcessing.Order;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {
    private static final Logger logger = Logger.getGlobal();
    private static final StackWalker walker = StackWalker.getInstance();
    List<Order> orders;

    public Tasks(List<Order> orders) {
        this.orders = orders;
    }

    private String getMethodName() {
        try {
            return walker.walk(Stream::findFirst)
                         .orElseThrow()
                         .getMethodName();
        } catch (Exception ignored) {
            return "";
        }
    }

    public Map<String, Duration> getAveragePreparationTimeByBeverage() {

        logger.entering(this.getClass().getName(), getMethodName());
        Map<String, Duration> result;

        try {
            result = orders
                    .stream()
                    .collect(
                            Collectors.groupingBy(
                                    Order::name,
                                    Collectors.averagingLong(x -> x.preparationTime().getSeconds())
                            ))
                    .entrySet()
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    x -> Duration.ofSeconds(Math.round(x.getValue()))
                            ));
            logger.info("Got avg prep time: " + result);

        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), getMethodName(), e);
            throw e;
        }

        logger.exiting(this.getClass().getName(), getMethodName(), result);
        return result;
    }

    public List<Integer> getBusiestWeekdayHours() {

        logger.entering(this.getClass().getName(), getMethodName());
        List<Integer> result;

        try {
            Map<Integer, Long> customersByHours = orders
                    .stream()
                    .filter(x -> x.date().getDayOfWeek() != DayOfWeek.SATURDAY && x.date().getDayOfWeek() != DayOfWeek.SUNDAY)
                    .collect(
                            Collectors.groupingBy(
                                    x -> x.date().getHour(),
                                    Collectors.counting()
                            ));
            logger.info("Got customers by workday hours: " + customersByHours);

            long maxCustomers = customersByHours
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow(NoWorkdayCustomersException::new)
                    .getValue();
            logger.info("Got max customers on workdays: " + maxCustomers);

            result = customersByHours
                    .entrySet()
                    .stream()
                    .filter(x -> x.getValue().equals(maxCustomers))
                    .map(Map.Entry::getKey)
                    .toList();
            logger.info("Got busiest hours on workdays: " + result);

        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), getMethodName(), e);
            throw e;
        }

        logger.exiting(this.getClass().getName(), getMethodName(), result);
        return result;
    }

    public List<String> getMostPopularDrinks() {

        logger.entering(this.getClass().getName(), getMethodName());
        List<String> result;

        try {
            Map<String, Long> drinksByPopularity = orders
                    .stream()
                    .filter(x -> x.date().getHour() >= 7 && x.date().getHour() <= 12)
                    .collect(
                            Collectors.groupingBy(
                                    Order::name,
                                    Collectors.counting()
                            ));
            logger.info("Got morning drinks by popularity: " + drinksByPopularity);

            long maxPopularity = drinksByPopularity
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow(NoMorningDrinksException::new)
                    .getValue();
            logger.info("Got max morning popularity: " + maxPopularity);

            result = drinksByPopularity
                    .entrySet()
                    .stream()
                    .filter(x -> x.getValue().equals(maxPopularity))
                    .map(Map.Entry::getKey)
                    .toList();
            logger.info("Got most popular drinks in the morning: " + result);

        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), getMethodName(), e);
            throw e;
        }

        logger.exiting(this.getClass().getName(), getMethodName(), result);
        return result;
    }

    public String getOptimalDrink() {

        logger.entering(this.getClass().getName(), getMethodName());
        String result;

        try {
            Map<String, Double> costTimeRatio = orders
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    Order::name,
                                    x -> x.cost() / x.preparationTime().getSeconds()
                            ));
            logger.info("Got drinks by ratio: " + costTimeRatio);

            double average = costTimeRatio
                    .values()
                    .stream()
                    .mapToDouble(x -> x)
                    .average()
                    .orElseThrow(NoOrdersException::new);
            logger.info("Got average ratio: " + average);

            result = costTimeRatio
                    .entrySet()
                    .stream()
                    .min(Comparator.comparingDouble(x -> Math.abs(x.getValue() - average)))
                    .orElseThrow(NoOrdersException::new)
                    .getKey();
            logger.info("Got drink with closest to average ratio: " + result);

        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), getMethodName(), e);
            throw e;
        }

        logger.exiting(this.getClass().getName(), getMethodName(), result);
        return result;
    }
}