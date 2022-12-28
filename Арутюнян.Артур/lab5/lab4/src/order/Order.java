package order;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public record Order(String courier, String restaurantName, String[] composition, LocalDateTime startDate,
                    LocalDateTime finishDate) {

    private static final Logger logger = Logger.getLogger(Order.class.getName());

    public int deliveryTime() {
        return (int) ChronoUnit.SECONDS.between(startDate, finishDate);
    }

    @Override
    public String toString() {
        return "Дата: " + startDate
                + "\nКурьер: " + courier
                + "\nНазвание ресторана: " + restaurantName
                + "\nЗаказ: " + Arrays.toString(composition)
                + "\nДата доставки: " + finishDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Order order)) {
            return false;
        }
        return startDate.equals(order.startDate)
                && finishDate.equals(order.finishDate)
                && Objects.equals(courier, order.courier)
                && Objects.equals(restaurantName, order.restaurantName)
                && composition == order.composition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(finishDate, startDate, courier, Arrays.hashCode(composition), restaurantName);
    }

    // parse from string Manager;Customer;Amount;Date
    public static Order parse(String text) {
        try {
            String[] parts = text.split(";");
            return new Order(parts[0], parts[1], parts[2].split(","), LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
        } catch (DateTimeParseException e) {
            logger.log(Level.SEVERE, "data_parse_error", e);
        }
        return null;
    }
}
