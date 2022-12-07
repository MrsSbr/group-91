package classes;

import enums.VarietiesOfCoffee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class Information {

    public static final String[] coffee = {"AMERICANO", "CAPPUCCINO", "DOPPIO", "ESPRESSO", "FLAT_WHITE", "FRAPPE",
            "GLACE", "ICE_COFFEE", "IRISH_COFFEE", "LATTE", "LONG_BLACK", "MACCHIATO", "MOCHA", "RAF", "VENICE_COFFEE"};


    String nameOfTheDrink;
    LocalDate dateOfPreparationOfTheDrink;
    LocalTime timeOfPreparationOfTheDrink;

    public Information() {
        nameOfTheDrink = "";
        dateOfPreparationOfTheDrink = LocalDate.of(2000, 1, 1);
        timeOfPreparationOfTheDrink = LocalTime.of(0, 0);
    }

    public int randBetween(int min, int max) {

        return (int) (Math.random() * (max - min) + 1) + min;
    }

    public void fillingInInformation() {

        nameOfTheDrink = VarietiesOfCoffee.valueOf(coffee[randBetween(0, 14)]).getName();

        Calendar calendar = Calendar.getInstance();

        int year = randBetween(2000, 2022);
        calendar.set(Calendar.YEAR, year);

        int month = randBetween(1, 12);
        calendar.set(Calendar.MONTH, month - 1);

        int day = randBetween(1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        dateOfPreparationOfTheDrink = LocalDate.of(year, month, day);

        int hour = randBetween(7, 22);
        int minute = randBetween(0, 59);
        timeOfPreparationOfTheDrink = LocalTime.of(hour, minute);
    }
}