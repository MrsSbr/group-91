import TribeDatawork.Tribes;

import java.util.logging.Level;
import java.util.logging.Logger;

import static UI.Menus.todos;

public class Program {
    private static final Logger logger = Logger.getLogger(Program.class.getName());
    private static final String DATASET_PATH = "/Users/alexander/Projects/Java/labs/alexander.yakunin/lab4/tribes.txt";

    public static void main(String[] args) {
        logger.log(Level.INFO, "Start program");
        Tribes tribes = new Tribes();
        tribes.getTribesFromFile(DATASET_PATH);

        int ans;
        do {
            ans = todos();

            switch (ans) {
                case 1 -> { //средние значения
                    System.out.println("Среднее значение добытого мяса для каждого охотника:");
                    System.out.println(tribes.getAverageHuntResultForHunters());
                }
                case 2 -> {
                    System.out.println("Имя охотника, который убил больше всего мамонтов: ");
                    System.out.println(tribes.getBestMammothKiller());
                }
                case 3 -> {
                    System.out.println("Месяц, в который добыли больше всего мяса: ");
                    System.out.println(tribes.getMostKillerMonth());
                }
            }
        } while (ans != 4);
        logger.log(Level.INFO, "End of program");
    }
}
