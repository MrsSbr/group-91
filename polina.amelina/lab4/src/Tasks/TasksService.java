package Tasks;

import MethodNameGetter.MethodNameGetter;
import OrderProcessing.Order;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TasksService {
    private static final Logger logger = Logger.getGlobal();
    private final List<Order> orders;

    public TasksService(List<Order> orders) {
        this.orders = orders;
    }

    public Map<String, LocalTime> getAvgPrepTimeByDrink() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<String, Long> sumPrepTime = new HashMap<>();
        Map<String, Long> countPrepTime = new HashMap<>();
        for (Order order : orders) {
            sumPrepTime.put(order.name(), sumPrepTime.getOrDefault(order.name(), (long) 0) + order.prepTime().toSecondOfDay());
            countPrepTime.put(order.name(), countPrepTime.getOrDefault(order.name(), (long) 0) + 1);
        }

        logger.info("Got sum of prep time by drink: " + sumPrepTime);
        logger.info("Got count of drinks: " + countPrepTime);

        Map<String, Double> avgPrepTimeInSeconds = new HashMap<>();
        for (String name : sumPrepTime.keySet()) {
            avgPrepTimeInSeconds.put(name, sumPrepTime.get(name) / (double) countPrepTime.get(name));
        }

        logger.info("Got avg prep time in seconds: " + avgPrepTimeInSeconds);

        Map<String, LocalTime> avgPrepTime = new HashMap<>();
        for (String name : avgPrepTimeInSeconds.keySet()) {
            avgPrepTime.put(name, LocalTime.ofSecondOfDay(Math.round(avgPrepTimeInSeconds.get(name))));
        }

        logger.info("Got avg prep time in LocalTime: " + avgPrepTime);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), avgPrepTime);
        return avgPrepTime;
    }

    public List<Integer> getBusiestWeekdayHours() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        List<Order> ordersFiltered = new ArrayList<>();
        for (Order order : orders) {
            if (order.date().getDayOfWeek() != DayOfWeek.SATURDAY && order.date().getDayOfWeek() != DayOfWeek.SUNDAY) {
                ordersFiltered.add(order);
            }
        }

        logger.info("Got workday orders: " + ordersFiltered);

        if (ordersFiltered.isEmpty()) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<Integer, Long> customersByHours = new HashMap<>();
        for (Order order : ordersFiltered) {
            customersByHours.put(order.date().getHour(), customersByHours.getOrDefault(order.date().getHour(), (long) 0) + 1);
        }

        logger.info("Got customers by workday hours: " + customersByHours);

        long maxCustomers = 0;
        for (long customers : customersByHours.values()) {
            if (customers > maxCustomers) {
                maxCustomers = customers;
            }
        }

        logger.info("Got max customers on workdays: " + maxCustomers);

        List<Integer> busiestHours = new ArrayList<>();
        for (int hour : customersByHours.keySet()) {
            if (maxCustomers == customersByHours.get(hour)) {
                busiestHours.add(hour);
            }
        }

        logger.info("Got busiest hours on workdays: " + busiestHours);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), busiestHours);
        return busiestHours;
    }

    public List<String> getMostPopularDrinks() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        List<Order> ordersFiltered = new ArrayList<>();
        for (Order order : orders) {
            if (order.date().getHour() >= 7 && order.date().getHour() < 12) {
                ordersFiltered.add(order);
            }
        }

        logger.info("Got morning orders: " + ordersFiltered);

        if (ordersFiltered.isEmpty()) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<String, Long> drinksByPopularity = new HashMap<>();
        for (Order order : ordersFiltered) {
            drinksByPopularity.put(order.name(), drinksByPopularity.getOrDefault(order.name(), (long) 0) + 1);
        }

        logger.info("Got morning drinks by popularity: " + drinksByPopularity);

        long maxPopularity = 0;
        for (long popularity : drinksByPopularity.values()) {
            if (popularity > maxPopularity) {
                maxPopularity = popularity;
            }
        }
        logger.info("Got max morning popularity: " + maxPopularity);

        List<String> mostPopularDrinks = new ArrayList<>();
        for (String drink : drinksByPopularity.keySet()) {
            if (maxPopularity == drinksByPopularity.get(drink)) {
                mostPopularDrinks.add(drink);
            }
        }
        logger.info("Got most popular drinks in the morning: " + mostPopularDrinks);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), mostPopularDrinks);
        return mostPopularDrinks;
    }

    public String getOptimalDrink() {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        if (orders.isEmpty()) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());
            return null;
        }

        Map<Order, Double> costTimeRatio = new HashMap<>();
        for (Order order : orders) {
            costTimeRatio.put(order, order.cost() / order.prepTime().toSecondOfDay());
        }

        logger.info("Got cost time ratio: " + costTimeRatio);

        double sum = 0;
        for (double ratio : costTimeRatio.values()) {
            sum += ratio;
        }
        double average = sum / costTimeRatio.size();

        logger.info("Got average ratio: " + average);

        Map<String, Double> drinksByCost = new HashMap<>();
        Map<String, Long> drinksByPrepTime = new HashMap<>();
        for (Order order : orders) {
            drinksByCost.put(order.name(), drinksByCost.getOrDefault(order.name(), (double) 0) + order.cost());
            drinksByPrepTime.put(order.name(), drinksByPrepTime.getOrDefault(order.name(), (long) 0) + order.prepTime().toSecondOfDay());
        }

        logger.info("Got sums of cost by drink: " + drinksByCost);
        logger.info("Got sums of prep time by drink: " + drinksByPrepTime);

        Map<String, Double> costTimeRatioOfSums = new HashMap<>();
        for (String name : drinksByCost.keySet()) {
            costTimeRatioOfSums.put(name, drinksByCost.get(name) / drinksByPrepTime.get(name));
        }
        logger.info("Got cost time ratio of sums: " + costTimeRatioOfSums);

        String optimalDrink = orders.get(0).name();
        double minDifference = Math.abs(costTimeRatioOfSums.get(optimalDrink) - average);
        for (String name : costTimeRatioOfSums.keySet()) {
            double difference = Math.abs(costTimeRatioOfSums.get(name) - average);
            if (difference < minDifference) {
                minDifference = difference;
                optimalDrink = name;
            }
        }

        logger.info("Got drink with closest to average ratio: " + optimalDrink);

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), optimalDrink);
        return optimalDrink;
    }
}