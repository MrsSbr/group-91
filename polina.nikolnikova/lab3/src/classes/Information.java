package classes;

import enums.VarietiesOfCoffee;

import java.time.LocalDate;
import java.time.LocalTime;

public class Information {

    private String nameOfTheDrink;
    private LocalDate dateOfPreparationOfTheDrink;
    private LocalTime timeOfPreparationOfTheDrink;

    public Information() {
        nameOfTheDrink = "";
        dateOfPreparationOfTheDrink = LocalDate.of(2000, 1, 1);
        timeOfPreparationOfTheDrink = LocalTime.of(0, 0);
    }

    public String getNameOfTheDrink() {
        return nameOfTheDrink;
    }

    public LocalDate getDateOfPreparationOfTheDrink() {
        return dateOfPreparationOfTheDrink;
    }

    public LocalTime getTimeOfPreparationOfTheDrink() {
        return timeOfPreparationOfTheDrink;
    }

    private int randBetween(int min, int max) {

        return (int) (Math.random() * (max - min) + 1) + min;
    }

    public void fillingInInformation() {

        nameOfTheDrink = VarietiesOfCoffee.getById(randBetween(0, 14));

        int year = randBetween(2000, 2022);
        int month = randBetween(1, 12);

        LocalDate date = LocalDate.of(year, month, 1);
        int day = randBetween(1, date.lengthOfMonth());

        dateOfPreparationOfTheDrink = LocalDate.of(year, month, day);

        int hour = randBetween(7, 22);
        int minute = randBetween(0, 59);
        timeOfPreparationOfTheDrink = LocalTime.of(hour, minute);
    }
}