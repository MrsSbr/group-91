import modules.PlantStorage;
import input.ConsoleInput;
import input.InvalidBoundsException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final int END_MENU_ITEM = 4;

    private static final String DATA_PATH = "polina.poliakova/lab3/group-91/polina.poliakova/lab4/data.txt";

    public static void main(final String[] args) {
        logger.log(Level.INFO, "Start program");

        PlantStorage plantStorage = new PlantStorage();
        plantStorage.fillFromFile(DATA_PATH);

        try {
            int selectedMenuItem;
            do {
                printMenu();
                selectedMenuItem = ConsoleInput.inputIntInRange("Выберите пункт", 1, END_MENU_ITEM);
                switch (selectedMenuItem) {
                    case 1 -> System.out.println(plantStorage.getLessProfitablePlant());
                    case 2 -> System.out.println(plantStorage.getAllSizesPotsForEveryPlant());
                    case 3 -> System.out.println(plantStorage.getMonthsForYearWhenMostFloweringPlantsSold());
                }
                waitUser();
            } while (selectedMenuItem != END_MENU_ITEM);
        } catch (InvalidBoundsException e) {
            logger.log(Level.SEVERE, "Input menu item exception", e);
        }

        logger.log(Level.OFF, "End program");
    }

    public static void printMenu() {
        System.out.println("1: Найти растение(растения), на продаже которого(-ых) магазин заработал меньше всего");
        System.out.println("2: Найти для каждого растения найти все размеры горшков, в которых оно продается");
        System.out.println("3: Найти месяц, в котором было продано больше всего цветущих растений");
        System.out.println("4: Выход");
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
