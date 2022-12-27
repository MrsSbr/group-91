package Classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private int weight;
    private LocalDate dateOfOrder;
    private LocalTime timeOfDelivery;

    public Order() {
        weight = 0;
        dateOfOrder = LocalDate.of(2000, 1, 1);
        timeOfDelivery = LocalTime.of(0, 0);
    }

    public int getWeight() {
        return weight;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public LocalTime getTimeOfDelivery() {
        return timeOfDelivery;
    }

    private int randBetween(int min, int max) {
        return (int) (Math.random() * (max - min) + 1) + min;
    }

    public void fillingInformationOfOrder() {
        weight = randBetween(50, 5000);

        int year = randBetween(2000, 2022);
        int month = randBetween(1, 12);

        LocalDate date = LocalDate.of(year, month, 1);
        int day = randBetween(1, date.lengthOfMonth());

        dateOfOrder = LocalDate.of(year, month, day);

        int hour = randBetween(7, 22);
        int minute = randBetween(0, 59);
        timeOfDelivery = LocalTime.of(hour, minute);
    }
}
