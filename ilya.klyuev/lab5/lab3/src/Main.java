import input.ConsoleInput;
import input.InvalidBoundsException;
import performances.Performance;
import performances.PerformancesStorage;

import java.util.Map;
import java.util.Set;
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
                    case 2 -> printPerformancesTicketsStatistic(performancesStorage.getPerformancesTicketsStatistic());
                    case 3 -> printMaxPopularPerformances(performancesStorage.getMaxPopularPerformances());
                    case 4 -> printPerformancesNotTickets(performancesStorage.getPerformancesNotTickets());
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

    private static void printPerformancesTicketsStatistic(Map<Performance, Integer> performancesTicketsStatistic) {
        if (performancesTicketsStatistic.isEmpty()) {
            System.out.println("Нет информации о купленных билетах");
        } else {
            System.out.println(performancesTicketsStatistic.entrySet());
        }
    }

    private static void printMaxPopularPerformances(Set<Performance> maxPopularPerformances) {
        if (maxPopularPerformances.isEmpty()) {
            System.out.println("Нет информации о самых продаваемых спектаклях");
        } else {
            System.out.println(maxPopularPerformances);
        }
    }

    private static void printPerformancesNotTickets(Set<Performance> performancesNotTickets) {
        if (performancesNotTickets.isEmpty()) {
            System.out.println("Нет спектаклей, на которые не купили билеты");
        } else {
            System.out.println(performancesNotTickets);
        }
    }
}