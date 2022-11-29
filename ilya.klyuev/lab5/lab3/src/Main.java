import input.ConsoleInput;
import input.InvalidBoundsException;
import performances.PerformancesStorage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final int MAX_MENU_ITEM = 5;

    public static void main(String[] args) {
        logger.log(Level.INFO, "Start program");

        PerformancesStorage performancesStorage = new PerformancesStorage();

        try {
            int selectedMenuItem;
            do {
                printMenu();
                selectedMenuItem = ConsoleInput.inputIntInRange("Выберите пункт: ", 1, MAX_MENU_ITEM);
                switch (selectedMenuItem) {
                    case 1 -> performancesStorage.fillRandom();
                    case 2 -> System.out.println(performancesStorage.getPerformancesTicketsString());
                    case 3 -> System.out.println(performancesStorage.getMaxPopularPerformancesString());
                    case 4 -> System.out.println(performancesStorage.getPerformancesNotTicketsString());
                }

            } while (selectedMenuItem != MAX_MENU_ITEM);
        } catch (InvalidBoundsException e) {
            logger.log(Level.SEVERE, "Input menu item exception", e);
        }

        logger.log(Level.OFF, "End program");
    }

    private static void printMenu() {
        System.out.println("1: Заполнить информацию о билетах случайно");
        System.out.println("2: Показать количество билетов для  каждого спектакля");
        System.out.println("3: Показать самые популярные спектакли");
        System.out.println("4: Показать спектакли, на которые никто не купил билет");
        System.out.println("5: Выход");
    }
}