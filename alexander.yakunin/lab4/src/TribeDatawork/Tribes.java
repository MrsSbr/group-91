package TribeDatawork;

import Helpers.AllHuntResult;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tribes {
    private static final Logger logger = Logger.getLogger(Tribes.class.getName());

    private final List<TribeEntry> tribes = new ArrayList<>();

    public void getTribesFromFile(String path) {
        try (var bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
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
            logger.log(Level.SEVERE, "Файл не нашелся", e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка чтения", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Что-то пошло не так!", e.getMessage());
        }

    }

    public String getAverageHuntResultForHunters() {
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

        String resultString = "";
        for (var entry : huntersResult.entrySet()) {
            var entryValue = entry.getValue();
            resultString += entry.getKey().toString() + ": " + (double) entryValue.getWeight() / entryValue.getCountOfHunt() + "\n";
        }

        return resultString;
    }

    public String getBestMammothKiller() {
        if (tribes.isEmpty()) {
            logger.log(Level.INFO, "Tribes is Null");
            return null;
        }

        HashMap<String, Integer> huntersResult = new HashMap<>();

        for (var tribe : tribes) {
            Integer mammoth = huntersResult.get(tribe.getHunterName());
            huntersResult.put(tribe.getHunterName(), tribe.getMammothWeight() + (mammoth == null ? 0 : mammoth));
        }

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

    public String getMostKillerMonth() {
        if (tribes.isEmpty()) {
            logger.log(Level.INFO, "Tribes is Null");
            return null;
        }

        HashMap<String, Integer> monthKillerStat = new HashMap<>();

        for (var tribe : tribes) {
            String month = Integer.toString(tribe.getDate().getYear()) + "-" + Integer.toString(tribe.getDate().getMonthValue());
            Integer stat = monthKillerStat.get(month);
            monthKillerStat.put(month, tribe.getMammothWeight() + (stat == null ? 0 : stat));
        }

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
}
