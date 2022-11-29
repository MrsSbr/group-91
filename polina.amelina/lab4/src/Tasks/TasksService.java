package Tasks;

import MethodNameGetter.MethodNameGetter;
import OrderProcessing.Order;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TasksService {
    private static final Logger logger = Logger.getGlobal();
    private final List<Order> orders;

    public TasksService(List<Order> orders) {
        this.orders = orders;
    }

    public Map<String, LocalTime> getAvgPrepTimeByDrink() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        Map<String, Double> avgPrepTimeInSeconds = orders
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Order::name,
                                Collectors.averagingLong(x -> x.prepTime().toSecondOfDay())
                        ));
        logger.info("Got avg prep time in seconds: " + avgPrepTimeInSeconds);

        Map<String, LocalTime> avgPrepTime =
                avgPrepTimeInSeconds.isEmpty()
                ? null
                : avgPrepTimeInSeconds
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
                .orElse(Map.entry(-1, (long) -1))
                .getValue();
        logger.info("Got max customers on workdays: " + maxCustomers);

        List<Integer> busiestHours =
                maxCustomers == -1
                ? null
                : customersByHours
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

        Map<String, Long> drinksByPopularity = orders
                .stream()
                .filter(x -> x.date().getHour() >= 7 && x.date().getHour() < 12)
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
                .orElse(Map.entry("", (long) -1))
                .getValue();
        logger.info("Got max morning popularity: " + maxPopularity);

        List<String> mostPopularDrinks =
                maxPopularity == -1
                ? null
                : drinksByPopularity
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

        Map<Order, Double> costTimeRatio = orders
                .stream()
                .collect(
                        Collectors.toMap(
                                x -> x,
                                y -> y.cost() / y.prepTime().toSecondOfDay()
                        )
                );
        logger.info("Got cost time ratio: " + costTimeRatio);

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

        double average = costTimeRatio
                .values()
                .stream()
                .mapToDouble(x -> x)
                .average()
                .orElse(-1);
        logger.info("Got average ratio: " + average);

        String optimalDrink =
                average == -1
                ? null
                : costTimeRatioOfSums
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