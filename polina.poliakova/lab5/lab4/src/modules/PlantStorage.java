package lab4.src.modules;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        return plants.stream().filter(plant -> plant.getDate().compareTo(date) >= 0).collect(Collectors.toList());
    }

    private void putMapEntryOnStringBuilder(StringBuilder stringBuilder, Map.Entry<String, Integer> entry) {
        stringBuilder
                .append(entry.getKey())
                .append(" : ")
                .append(entry.getValue())
                .append("\n");
    }


    private StringBuilder getMaxValueString(Map<String, Integer> statistics) {
        int maxAmount = statistics.values().stream().max(Integer::compare).orElse(-1);

        StringBuilder maxAmountEntriesStringBuilder = new StringBuilder();

        statistics.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxAmount))
                .forEach(entry -> putMapEntryOnStringBuilder(maxAmountEntriesStringBuilder, entry));

        return maxAmountEntriesStringBuilder;
    }

    //месяц, в котором было продано больше всего цветущих растений
    public String getMonthsForCurrentYearWhenMostFloweringPlantsSold() {
        if (plants.isEmpty()) {
            return "Нет растений :(";
        }
        Map<String, Integer> monthsSoldFloweringPlants = new HashMap<>();
        var yearSoldPlants = getPlantsAfterDate(LocalDate.now().minusYears(1));

        yearSoldPlants.forEach(plant -> {
            String month = plant.getDate().getMonth().toString();
            Integer floweringPlantAmount = monthsSoldFloweringPlants.get(month);
            monthsSoldFloweringPlants.put(month,
                    (plant.getTypeName() == PlantType.FLOWERING ? 1 : 0) + (floweringPlantAmount != null ? floweringPlantAmount : 0));
        });

        return getMaxValueString(monthsSoldFloweringPlants).toString();
    }

    //- для каждого растения найти все размеры горшков, в которых оно продается
    public String getAllSizesPotsForEveryPlant() {
        if (plants.isEmpty()) {
            return "Нет растений :(";
        }

        Map<String, Set<Double>> plantsWithSizePots = new HashMap<>();

        plants.forEach(plant -> {
            String plantName = plant.getPlantName();
            Set<Double> potsSizes = plantsWithSizePots.get(plantName) == null ? new HashSet<>() : plantsWithSizePots.get(plantName);
            potsSizes.add(plant.getSizePot());
            plantsWithSizePots.put(plantName, potsSizes);
        });

        return mapStringBuilder(plantsWithSizePots);
    }

    public String mapStringBuilder(Map<String, Set<Double>> map) {
        return map.keySet().stream().map(i -> i + " " + map.get(i).toString() + "\n").collect(Collectors.joining());
    }

    //найти растение(растения), на продаже которого(-ых) магазин заработал меньше всего;
    public String getLessProfitablePlant() {
        if (plants.isEmpty()) {
            return "Нет растений :(";
        }

        Map<String, Double> eachPlantAllIncome = new HashMap<>();
        plants.forEach(plant -> {
            String name = plant.getPlantName();
            eachPlantAllIncome.put(name, eachPlantAllIncome.get(name) == null ? 0 : eachPlantAllIncome.get(name) + plant.getPrice());
        });

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