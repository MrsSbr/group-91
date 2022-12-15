import Helpers.AllHuntResult;
import TribeDatawork.Tribes;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static UI.Menus.todos;

public class Program {
    private static final Logger logger = Logger.getLogger(Program.class.getName());
    private static final String DATASET_PATH = "/Users/alexander/Projects/Java/labs/alexander.yakunin/lab4/tribes.txt";

    public static String getAverageHuntResultFromStatsForHunters(HashMap<String, AllHuntResult> huntersResult) {
        StringBuilder resultString = new StringBuilder();
        for (var entry : huntersResult.entrySet()) {
            var entryValue = entry.getValue();
            double averageWeight = (double) entryValue.getWeight() / entryValue.getCountOfHunt();
            resultString.append(entry.getKey()).append(": ").append(averageWeight).append("\n");
        }
        return resultString.toString();
    }

    public static String getBestMammothKillerFromMammothKillersStats(HashMap<String, Integer> huntersResult) {
        String bestMammothKiller = "";
        Integer maxWeightMammoth = -1;

        for (var entry : huntersResult.entrySet()) {
            if (entry.getValue() > maxWeightMammoth) {
                bestMammothKiller = entry.getKey();
                maxWeightMammoth = entry.getValue();
            }
        }

        return bestMammothKiller;
    }

    public static String getMostKillerMonthFromMonthStat(HashMap<String, Integer> monthKillerStat) {
        String mostKillerMonth = "";
        Integer maxWeightMammoth = -1;
        for (var entry : monthKillerStat.entrySet()) {
            if (entry.getValue() > maxWeightMammoth) {
                mostKillerMonth = entry.getKey();
                maxWeightMammoth = entry.getValue();
            }
        }

        return mostKillerMonth;
    }

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
                    System.out.println(getAverageHuntResultFromStatsForHunters(tribes.getStatsForHunters()));
                }
                case 2 -> {
                    System.out.println("Имя охотника, который убил больше всего мамонтов: ");
                    System.out.println(getBestMammothKillerFromMammothKillersStats(tribes.getMammothKillersStats()));
                }
                case 3 -> {
                    System.out.println("Месяц, в который добыли больше всего мяса: ");
                    System.out.println(getMostKillerMonthFromMonthStat(tribes.getMonthHuntStat()));
                }
            }
        } while (ans != 4);
        logger.log(Level.INFO, "End of program");
    }
}
