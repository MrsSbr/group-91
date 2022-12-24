package order;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderStorage {

    private static final Logger logger = Logger.getLogger(OrderStorage.class.getName());
    private final List<Order> orders = new ArrayList<>();

    public void read(String path) {
        logger.log(Level.INFO, "read_file_started");

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(line->{
                Order order = Order.parse(line);
                if (order == null) {
                    logger.log(Level.SEVERE, "order_cast_error");
                }
                else {
                    orders.add(order);
                }
            });
            logger.log(Level.INFO, "file_read");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "file_not_founded", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "read_file_error", e);
        }

    }

    public int getRestaurantsCount() {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::restaurantName))
                .size();
    }

    public Set<String> getCouriersForEachRestaurants() {
        int restCount = getRestaurantsCount();
        return orders.stream()
                .collect(Collectors.groupingBy(Order::courier, Collectors.groupingBy(Order::restaurantName)))
                .entrySet().stream().filter(pair->pair.getValue().size()==restCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public String getMaxPopularRestForLastYear() {
        return orders.stream().filter(order -> order.startDate().getYear() == LocalDate.now().getYear())
                .collect(Collectors.groupingBy(Order::restaurantName, Collectors.summingInt(order->order.composition().length)))
                .entrySet()
                .stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    public LocalDate getMaxSumTimeOrders() {
        return orders.stream().collect(Collectors.groupingBy(order -> {
            int year = order.startDate().getYear();
            int month = order.startDate().getMonthValue();
            return LocalDate.parse(year + "-" + (month < 10 ? "0" + month : month) + "-01");
        },
                Collectors.summingInt(Order::deliveryTime))).entrySet()
                .stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }
}
