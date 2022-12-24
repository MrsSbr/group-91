package order;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderStorage {

    private static final Logger logger = Logger.getLogger(OrderStorage.class.getName());
    private final List<Order> orders = new ArrayList<>();

    public void read(String path) {
        logger.log(Level.INFO, "read_file_started");

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                Order order = Order.parse(line);
                if (order == null) {
                    logger.log(Level.SEVERE, "order_cast_error");
                    break;
                }
                orders.add(order);
            }
            logger.log(Level.INFO, "file_read");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "file_not_founded", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "read_file_error", e);
        }

    }

    public int getRestaurantsCount() {
        Set<String> restaurants = new HashSet<>();
        for (var order : orders) {
            restaurants.add(order.restaurantName());
        }
        return restaurants.size();
    }

    public Set<String> getCouriersForEachRestaurants() {
        Set<String> couriers = new HashSet<>();
        int restCount = getRestaurantsCount();
        for (var order1 : orders) {
            String courierName = order1.courier();
            if (!couriers.contains(courierName)) {
                Set<String> restsForCourier = new HashSet<>();
                for (var order2 : orders) {
                    if (courierName.equals(order2.courier())) {
                        restsForCourier.add(order2.restaurantName());
                    }
                }
                if (restsForCourier.size() == restCount) {
                    couriers.add(courierName);
                }
            }
        }
        return couriers;
    }

    public String getMaxPopularRestForLastYear() {
        Map<String, Integer> restaurants = new HashMap<>();
        LocalDate date = LocalDate.now();
        for (var order : orders) {
            if (!(order.startDate().getYear() != date.getYear())) {
                String rest = order.restaurantName();
                if (!restaurants.containsKey(rest)) {
                    restaurants.put(rest, order.composition().length);
                }
                else {
                    restaurants.put(rest, restaurants.get(rest) + order.composition().length);
                }
            }
        }
        return getMaxValueFromMap(restaurants);
    }

    private <T> T getMaxValueFromMap(Map<T, Integer> map) {
        int maxValue = 0;
        T res = null;
        for (var pair : map.entrySet()) {
            if (pair.getValue() > maxValue) {
                maxValue = pair.getValue();
                res = pair.getKey();
            }
        }
        return res;
    }

    public LocalDate getMaxSumTimeOrders() {
        Map<LocalDate, Integer> months = new HashMap<>();
        for (var order : orders) {
            int year = order.startDate().getYear();
            int month = order.startDate().getMonthValue();

            LocalDate key = LocalDate.parse(year + "-" + (month < 10 ? "0" + month : month) + "-01");
            int value = (int) ChronoUnit.SECONDS.between(order.startDate(), order.finishDate());
            if (months.containsKey(key)) {
                months.put(key, months.get(key) + value);
            }
            else {
                months.put(key, value);
            }
        }
        return getMaxValueFromMap(months);
    }
}
