package models;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class FarmStatistics {
    final private LocalDate date; // дата
    final private double feedQuantity; // кол-во съеденной еды
    final private double milkQuantity; // кол-во выпитого молока

    public FarmStatistics(LocalDate date, double feedQuantity, double milkQuantity) {
        this.date = date;
        this.feedQuantity = feedQuantity;
        this.milkQuantity = milkQuantity;
    }

    public static FarmStatistics randomGenerate() {
        LocalDate start = LocalDate.of(1970, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));

        int randomFeed = new Random().nextInt(1000, 1700);

        int randomMilk = new Random().nextInt(700, 1200);

        return new FarmStatistics(
                randomDate,
                randomFeed,
                randomMilk
        );
    }

    public LocalDate getDate() {
        return date;
    }

    public double getFeedQuantity() {
        return feedQuantity;
    }

    public double getMilkQuantity() {
        return milkQuantity;
    }

}
