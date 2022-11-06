import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final int END_MENU_ITEM = 5;

    private static final String dataPath = "ilya.klyuev/lab4/data.txt";

    public static void main(final String[] args) {
        logger.log(Level.INFO, "Start program");

        DealsStorage dealsStorage = new DealsStorage();
        dealsStorage.addFromFile(dataPath);

        int selectedMenuItem;

        do {
            printMenu();
            selectedMenuItem = ConsoleInput.inputIntInRange("Выберите пункт", 1, END_MENU_ITEM);
            switch (selectedMenuItem) {
                case 1 -> System.out.println(dealsStorage.getAllDealsString());
                case 2 -> System.out.println(dealsStorage.getMostEffectiveManagersStringForLastMonth());
                case 3 -> System.out.println(dealsStorage.getCustomersIncomeStatisticString());
                case 4 -> System.out.println(dealsStorage.getMostProfitableMonthsStringForLastYear());
            }
            waitUser();
        } while (selectedMenuItem != END_MENU_ITEM);

        logger.log(Level.OFF, "End program");
    }

    public static void printMenu() {
        System.out.println("1: Вывести все сделки");
        System.out.println("2: Найти самого эффективного менеджера за последний месяц");
        System.out.println("3: Собрать статистику по доходу от каждого клиента");
        System.out.println("4: Найти самый прибыльный месяц за последний год");
        System.out.println("5: Выход");
    }

    public static void waitUser() {
        System.out.println("Введите <ENTER>");
        try {
            System.in.read();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "wait press enter", e);
        }
    }
}
