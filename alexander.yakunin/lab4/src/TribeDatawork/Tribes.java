package TribeDatawork;

import Helpers.AllHuntResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tribes {
    private static final Logger logger = Logger.getLogger(Tribes.class.getName());

    private final List<TribeEntry> tribes = new ArrayList<>();

    public void getTribesFromFile(String path) {
        File file = new File(path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))  {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                TribeEntry tribe = TribeEntry.getTribeFromString(line);
                if (tribe == null) {
                    logger.log(Level.SEVERE, "Tribe is Null");
                    break;
                }
                tribes.add(tribe);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Файл не найден", e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка чтения", e.getMessage());
        }
    }

    public HashMap<String, AllHuntResult> getStatsForHunters() {
        if (tribes.isEmpty()) {
            logger.log(Level.INFO, "Tribes is Null");
            return null;
        }

        HashMap<String, AllHuntResult> huntersResult = new HashMap<>();

        for (var tribe : tribes) {
            AllHuntResult huntResult = huntersResult.get(tribe.getHunterName());
            if (huntResult != null) {
                huntersResult.put(tribe.getHunterName(), huntResult.addHunt(tribe.getMammothWeight()));
            } else {
                huntResult = new AllHuntResult(tribe.getMammothWeight(), 1);
                huntersResult.put(tribe.getHunterName(), huntResult);
            }
        }

        return huntersResult;
    }

    public HashMap<String, Integer> getMammothKillersStats() {
        if (tribes.isEmpty()) {
            logger.log(Level.INFO, "Tribes is Null");
            return null;
        }

        HashMap<String, Integer> huntersResult = new HashMap<>();

        for (var tribe : tribes) {
            Integer mammoth = huntersResult.get(tribe.getHunterName());
            huntersResult.put(tribe.getHunterName(), tribe.getMammothWeight() + (mammoth == null ? 0 : mammoth));
        }

        return huntersResult;
    }

    public HashMap<String, Integer> getMonthHuntStat() {
        if (tribes.isEmpty()) {
            logger.log(Level.INFO, "Tribes is Null");
            return null;
        }

        HashMap<String, Integer> monthKillerStat = new HashMap<>();

        for (var tribe : tribes) {
            String month = tribe.getDate().getYear() + "-" + tribe.getDate().getMonthValue();
            Integer stat = monthKillerStat.get(month);
            monthKillerStat.put(month, tribe.getMammothWeight() + (stat == null ? 0 : stat));
        }

        return monthKillerStat;
    }
}
