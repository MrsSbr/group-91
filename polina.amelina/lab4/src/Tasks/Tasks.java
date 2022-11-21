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

public class Tasks {
    private static final Logger logger = Logger.getGlobal();
    List<Order> orders;

    public Tasks(List<Order> orders) {
        this.orders = orders;
    }

    public String getAveragePrepTimeByDrink() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());
        String result;

        try {
            Map<String, Double> avgPrepTimeInSeconds = orders
                    .stream()
                    .collect(
                            Collectors.groupingBy(
                                    Order::name,
                                    Collectors.averagingLong(x -> x.prepTime().toSecondOfDay())
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

            result = "Среднее время приготовления каждого напитка:\n"
                    + avgPrepTime
                    .entrySet()
                    .stream()
                    .map(x -> x.getKey() + ": " + x.getValue())
                    .collect(Collectors.joining("\n"))
                    + "\n";

        } catch (Exception e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getBusiestWeekdayHours() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());
        String result;

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
                    .orElse(Map.entry(-1, (long) -1))
                    .getValue();
            logger.info("Got max customers on workdays: " + maxCustomers);

            if (maxCustomers == -1) {
                result = "По будням нет посетителей\n";
            } else {
                List<Integer> busiestHours = customersByHours
                        .entrySet()
                        .stream()
                        .filter(x -> x.getValue().equals(maxCustomers))
                        .map(Map.Entry::getKey)
                        .toList();
                logger.info("Got busiest hours on workdays: " + busiestHours);

                result = "Самые загруженные часы по будням: "
                        + busiestHours
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "))
                        + "\n";
            }
        } catch (Exception e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getMostPopularDrinks() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());
        String result;

        try {
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

            if (maxPopularity == -1) {
                result = "С 7 до 12 утра нет заказов\n";
            } else {
                List<String> mostPopularDrinks = drinksByPopularity
                        .entrySet()
                        .stream()
                        .filter(x -> x.getValue().equals(maxPopularity))
                        .map(Map.Entry::getKey)
                        .toList();
                logger.info("Got most popular drinks in the morning: " + mostPopularDrinks);

                result = "Напитки, которые чаще всего заказывают с 7 до 12 утра: "
                        + mostPopularDrinks
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "))
                        + "\n";
            }
        } catch (Exception e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getOptimalDrink() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());
        String result;

        try {
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

            if (average == -1) {
                result = "Нет ни одного проданного напитка\n";
            } else {
                String optimalDrink = costTimeRatioOfSums
                        .entrySet()
                        .stream()
                        .min(Comparator.comparingDouble(x -> Math.abs(x.getValue() - average)))
                        .orElseThrow()
                        .getKey();
                logger.info("Got drink with closest to average ratio: " + optimalDrink);

                result = "Напиток с наилучшим соотношением цена/время: " + optimalDrink;
            }
        } catch (Exception e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }
}