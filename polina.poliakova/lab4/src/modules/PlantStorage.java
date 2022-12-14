package modules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlantStorage {
    private static final Logger logger = Logger.getLogger(PlantStorage.class.getName());
    private final List<Plant> plants = new ArrayList<>();

    public void fillFromFile(String filePath) {
        logger.log(Level.INFO, "Start read file");
        File file = new File(filePath);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                Plant plant = Plant.parse(line);
                if (plant == null) {
                    logger.log(Level.SEVERE, "Parse plant error. Stop read");
                } else {
                    plants.add(plant);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Read file exception", e);
        }

        logger.log(Level.INFO, "End read file");
    }

    private List<Plant> getPlantsAfterDate(LocalDate date) {
        List<Plant> plantsAfterDate = new ArrayList<>();
        for (var plant : plants) {
            if (plant.getDate().compareTo(date) >= 0) {
                plantsAfterDate.add(plant);
            }
        }
        return plantsAfterDate;
    }

    private void putMapEntryOnStringBuilder(StringBuilder stringBuilder, Map.Entry<String, Integer> entry) {
        stringBuilder
                .append(entry.getKey())
                .append(" : ")
                .append(entry.getValue())
                .append("\n");
    }


    private StringBuilder getMaxValueString(Map<String, Integer> statistics) {
        Integer maxAmount = -1;

        for (var entry : statistics.entrySet()) {
            if (entry.getValue() > maxAmount) {
                maxAmount = entry.getValue();
            }
        }

        StringBuilder maxAmountEntriesStringBuilder = new StringBuilder();

        for (var entry : statistics.entrySet()) {
            if (entry.getValue().equals(maxAmount)) {
                putMapEntryOnStringBuilder(maxAmountEntriesStringBuilder, entry);
            }
        }

        return maxAmountEntriesStringBuilder;
    }

    // - ??????????, ?? ?????????????? ???????? ?????????????? ???????????? ?????????? ???????????????? ????????????????
    public String getMonthsForCurrentYearWhenMostFloweringPlantsSold() {
        if (plants.isEmpty()) {
            return "?????? ???????????????? :(";
        }
        Map<String, Integer> monthsSoldFloweringPlants = new HashMap<>();
        var yearSoldPlants = getPlantsAfterDate(LocalDate.now().minusYears(1));

        for (var plant : yearSoldPlants) {
            String month = plant.getDate().getMonth().toString();
            Integer floweringPlantAmount = monthsSoldFloweringPlants.get(month);
            monthsSoldFloweringPlants.put(month,
                    (plant.getTypeName() == PlantType.FLOWERING ? 1 : 0) + (floweringPlantAmount != null ? floweringPlantAmount : 0));
        }

        return getMaxValueString(monthsSoldFloweringPlants).toString();
    }

    //- ?????? ?????????????? ???????????????? ?????????? ?????? ?????????????? ??????????????, ?? ?????????????? ?????? ??????????????????
    public String getAllSizesPotsForEveryPlant() {
        if (plants.isEmpty()) {
            return "?????? ???????????????? :(";
        }

        Map<String, Set<Double>> plantsWithSizePots = new HashMap<>();

        for (var plant : plants) {
            String plantName = plant.getPlantName();
            Set<Double> potsSizes = plantsWithSizePots.get(plantName) == null ? new HashSet<>() : plantsWithSizePots.get(plantName);
            potsSizes.add(plant.getSizePot());
            plantsWithSizePots.put(plantName, potsSizes);
        }

        return mapStringBuilder(plantsWithSizePots);
    }

    public String mapStringBuilder(Map<String, Set<Double>> map) {
        StringBuilder result = new StringBuilder();

        for (var i : map.keySet()) {
            result.append(i).append(" ").append(map.get(i).toString()).append("\n");
        }

        return result.toString();
    }

    //?????????? ????????????????(????????????????), ???? ?????????????? ????????????????(-????) ?????????????? ?????????????????? ???????????? ??????????;
    public String getLessProfitablePlant() {
        if (plants.isEmpty()) {
            return "?????? ???????????????? :(";
        }

        Map<String, Double> eachPlantAllIncome = new HashMap<>();
        for (var plant : plants) {
            String name = plant.getPlantName();
            eachPlantAllIncome.put(name, eachPlantAllIncome.get(name) == null ? 0 : eachPlantAllIncome.get(name) + plant.getPrice());
        }

        String plantWithMinIncome = "";

        Double minIncome = eachPlantAllIncome.get(plants.get(0).getPlantName());
        for (var plant : eachPlantAllIncome.keySet()) {
            if (eachPlantAllIncome.get(plant) < minIncome) {
                minIncome = eachPlantAllIncome.get(plant);
                plantWithMinIncome = plant;
            }
        }

        return plantWithMinIncome;
    }
}