package tasks;

import methodNameGetter.MethodNameGetter;
import orderProcessing.Order;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class TasksService {
    private static final Logger logger = Logger.getGlobal();
    private final List<Order> orders;

    public TasksService(List<Order> orders) {
        this.orders = orders;
    }

    public Map<String, LocalTime> getAvgPrepTimeByDrink() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.info("No orders found");
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<String, Double> avgPrepTimeInSeconds = orders
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Order::name,
                                Collectors.averagingInt(x -> x.prepTime().toSecondOfDay())
                        ));
        logger.info("Got avg prep time in seconds: " + avgPrepTimeInSeconds);

        Map<String, LocalTime> avgPrepTime = avgPrepTimeInSeconds
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                x -> LocalTime.ofSecondOfDay(Math.round(x.getValue()))
                        ));
        logger.info("Got avg prep time in LocalTime: " + avgPrepTime);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), avgPrepTime);
        return avgPrepTime;
    }

    public List<Integer> getBusiestWeekdayHours() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.info("No orders found");
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<Integer, Long> customersByHours = orders
                .stream()
                .filter(x -> x.date().getDayOfWeek() != DayOfWeek.SATURDAY && x.date().getDayOfWeek() != DayOfWeek.SUNDAY)
                .collect(
                        Collectors.groupingBy(
                                x -> x.date().getHour(),
                                Collectors.counting()
                        ));

        if (customersByHours.isEmpty()) {
            logger.info("No workday orders found");
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;

        } else {
            logger.info("Got amount of customers by workday hours: " + customersByHours);
        }

        long maxCustomers = customersByHours
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getValue();
        logger.info("Got max customers on workdays: " + maxCustomers);

        List<Integer> busiestHours = customersByHours
                .entrySet()
                .stream()
                .filter(x -> x.getValue().equals(maxCustomers))
                .map(Map.Entry::getKey)
                .toList();
        logger.info("Got busiest hours on workdays: " + busiestHours);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), busiestHours);
        return busiestHours;
    }

    public List<String> getMostPopularDrinks() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.info("No orders found");
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<String, Long> drinksByPopularity = orders
                .stream()
                .filter(x -> x.date().getHour() >= 7 && x.date().getHour() < 12)
                .collect(
                        Collectors.groupingBy(
                                Order::name,
                                Collectors.counting()
                        ));

        if (drinksByPopularity.isEmpty()) {
            logger.info("No morning orders found");
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;

        } else {
            logger.info("Got morning drinks by popularity: " + drinksByPopularity);
        }

        long maxPopularity = drinksByPopularity
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getValue();
        logger.info("Got max morning popularity: " + maxPopularity);

        List<String> mostPopularDrinks = drinksByPopularity
                .entrySet()
                .stream()
                .filter(x -> x.getValue().equals(maxPopularity))
                .map(Map.Entry::getKey)
                .toList();
        logger.info("Got most popular drinks in the morning: " + mostPopularDrinks);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), mostPopularDrinks);
        return mostPopularDrinks;
    }

    public String getOptimalDrink() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.info("No orders found");
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<Order, Double> costTimeRatio = orders
                .stream()
                .collect(
                        Collectors.toMap(
                                x -> x,
                                y -> y.cost() / y.prepTime().toSecondOfDay()
                        )
                );
        logger.info("Got cost time ratio: " + costTimeRatio);

        double average = costTimeRatio
                .values()
                .stream()
                .mapToDouble(x -> x)
                .average()
                .orElseThrow();
        logger.info("Got average ratio: " + average);

        Map<String, Double> drinksByCost = orders
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Order::name,
                                Collectors.summingDouble(Order::cost)
                        ));
        logger.info("Got sums of cost by drink: " + drinksByCost);

        Map<String, Long> drinksByPrepTime = orders
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Order::name,
                                Collectors.summingLong(x -> x.prepTime().toSecondOfDay())
                        ));
        logger.info("Got sums of prep time by drink: " + drinksByPrepTime);

        Map<String, Double> costTimeRatioOfSums = drinksByCost
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                x -> x.getValue() / drinksByPrepTime.get(x.getKey())
                        ));
        logger.info("Got cost time ratio of sums: " + costTimeRatioOfSums);

        String optimalDrink = costTimeRatioOfSums
                .entrySet()
                .stream()
                .min(Comparator.comparingDouble(x -> Math.abs(x.getValue() - average)))
                .orElseThrow()
                .getKey();
        logger.info("Got drink with closest to average ratio: " + optimalDrink);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), optimalDrink);
        return optimalDrink;
    }
}