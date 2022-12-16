package Main;

import HelpersStreamApi.AllHuntResult;
import TribeDataworkStreamApi.Tribes;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static UI.Menus.todos;

public class Program {
    private static final Logger logger = Logger.getLogger(Program.class.getName());
    private static final String DATASET_PATH = "/Users/alexander/Projects/Java/labs/alexander.yakunin/lab5/4/tribes.txt";

    public static String getAverageHuntResultFromStatsForHunters(HashMap<String, AllHuntResult> huntersResult) {
        StringBuilder resultString = new StringBuilder();
        huntersResult.forEach((key, value) -> {
            double averageWeight = (double) value.getWeight() / value.getCountOfHunt();
            resultString.append(key).append(": ").append(averageWeight).append("\n");
        });
        return resultString.toString();
    }

    public static String getBestMammothKillerFromMammothKillersStats(HashMap<String, Integer> huntersResult) {
        final String[] bestMammothKiller = {""};
        final Integer[] maxWeightMammoth = {-1};

        huntersResult.forEach((key, value) -> {
            if (value > maxWeightMammoth[0]) {
                bestMammothKiller[0] = key;
                maxWeightMammoth[0] = value;
            }
        });

        return bestMammothKiller[0];
    }

    public static String getMostKillerMonthFromMonthStat(HashMap<String, Integer> monthKillerStat) {
        final String[] mostKillerMonth = {""};
        final Integer[] maxWeightMammoth = {-1};

        monthKillerStat.forEach((key, value) -> {
            if (value > maxWeightMammoth[0]) {
                mostKillerMonth[0] = key;
                maxWeightMammoth[0] = value;
            }
        });

        return mostKillerMonth[0];
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
