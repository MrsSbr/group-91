package lab4.src.modules;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Stream<Plant> getPlantsAfterDate(LocalDate date) {
        return plants.stream()
                .filter(plant -> plant.getDate()
                        .compareTo(date) >= 0);
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

        Map<String, List<Plant>> yearSoldPlants =
                getPlantsAfterDate(LocalDate.now().minusYears(1))
                        .collect(Collectors.groupingBy(x -> x.getDate().getMonth().toString()));

        Map<String, Integer> monthsSoldFloweringPlants = new HashMap<>();

        yearSoldPlants.forEach((month, list) -> monthsSoldFloweringPlants.put(month,
                (int) list.stream()
                .filter(plant -> plant.getTypeName() == PlantType.FLOWERING)
                .count()));

        return getMaxValueString(monthsSoldFloweringPlants).toString();
    }

    //для каждого растения найти все размеры горшков, в которых оно продается
    public Map<String, Set<Double>> getAllSizesPotsForEveryPlant() {
        if (plants.isEmpty()) {
            return null;
        }

        Map<String, List<Plant>> groupedByNamePlants = plants.stream()
                .collect(Collectors.groupingBy(Plant::getPlantName));

        return groupedByNamePlants.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, item -> item.getValue().stream()
                        .map(Plant::getSizePot)
                        .collect(Collectors.toSet()), (a, b) -> b));
    }

    //найти растение(растения), на продаже которого(-ых) магазин заработал меньше всего;
    public Map<String, Double> getLessProfitablePlant() {
        if (plants.isEmpty()) {
            return null;
        }

        Map<String, List<Plant>> groupedByPlantsName = plants.stream().collect(Collectors.groupingBy(Plant::getPlantName));
        Map<String, Double> eachNameAllIncome = new HashMap<>();
        groupedByPlantsName.forEach((name, list) -> eachNameAllIncome.put(name,
                list.stream().mapToDouble(Plant::getPrice).sum()));

        return eachNameAllIncome.entrySet().stream()
                .filter(elem -> elem.getValue() == (eachNameAllIncome.values()
                        .stream()
                        .mapToDouble(value -> value)
                        .min()
                        .orElse(0)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}