import deals.DealsStorage;
import input.ConsoleInput;
import input.InvalidBoundsException;

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
                    case 1 -> System.out.println(dealsStorage.getMostEffectiveManagersStringForLastMonth());
                    case 2 -> System.out.println(dealsStorage.getCustomersIncomeStatisticString());
                    case 3 -> System.out.println(dealsStorage.getMostProfitableMonthsStringForLastYear());
                }
            } while (selectedMenuItem != END_MENU_ITEM);
        } catch (InvalidBoundsException e) {
            logger.log(Level.SEVERE, "Input menu item exception", e);
        }

        logger.log(Level.OFF, "End program");
    }

    public static void printMenu() {
        System.out.println("1: Найти самого эффективного менеджера за последний месяц");
        System.out.println("2: Собрать статистику по доходу от каждого клиента");
        System.out.println("3: Найти самый прибыльный месяц за последний год");
        System.out.println("4: Выход");
    }
}
