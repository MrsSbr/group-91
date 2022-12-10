import deals.DealsStorage;
import input.ConsoleInput;
import input.InvalidBoundsException;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final int END_MENU_ITEM = 4;

    private static final String DATA_PATH = "ilya.klyuev/lab5/lab4/data.txt";

    public static void main(final String[] args) {
        logger.log(Level.INFO, "Start program");

        DealsStorage dealsStorage = new DealsStorage();
        dealsStorage.addFromFile(DATA_PATH);

        try {
            int selectedMenuItem;
            do {
                printMenu();
                selectedMenuItem = ConsoleInput.inputIntInRange("Выберите пункт", 1, END_MENU_ITEM);
                switch (selectedMenuItem) {
                    case 1 ->
                            printMostEffectiveManagersForLastMonth(dealsStorage.getMostEffectiveManagersForLastMonth());
                    case 2 -> printCustomersIncomeStatistic(dealsStorage.getCustomersIncomeStatistic());
                    case 3 -> printMostProfitableMonthsForLastYear(dealsStorage.getMostProfitableMonthsForLastYear());
                }
            } while (selectedMenuItem != END_MENU_ITEM);
        } catch (InvalidBoundsException e) {
            logger.log(Level.SEVERE, "Input menu item exception", e);
        }

        logger.log(Level.OFF, "End program");
    }

    private static void printMenu() {
        System.out.println("1: Найти самого эффективного менеджера за последний месяц");
        System.out.println("2: Собрать статистику по доходу от каждого клиента");
        System.out.println("3: Найти самый прибыльный месяц за последний год");
        System.out.println("4: Выход");
    }

    private static void printMostEffectiveManagersForLastMonth(Set<String> mostEffectiveManagers) {
        if (mostEffectiveManagers.isEmpty()) {
            System.out.println("Нет информации о самых эффективных менеджерах за последний месяц");
        } else {
            System.out.println(mostEffectiveManagers);
        }
    }

    private static void printCustomersIncomeStatistic(Map<String, Integer> customersIncomeStatistic) {
        if (customersIncomeStatistic.isEmpty()) {
            System.out.println("Нет информации о доходах клиентов");
        } else {
            System.out.println(customersIncomeStatistic);
        }
    }

    private static void printMostProfitableMonthsForLastYear(Set<String> mostProfitableMonths) {
        if (mostProfitableMonths.isEmpty()) {
            System.out.println("Нет информации о самых прибыльных месяцах за последний год");
        } else {
            System.out.println(mostProfitableMonths);
        }
    }
}
