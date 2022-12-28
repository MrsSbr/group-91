package classes;

import java.time.LocalDate;
import java.util.*;

public class SalesAccounting {
    private List<BedSheetInfo> bedSheetInfoList;

    private List<Integer> getSizesForMaterials(String material) {
        List<Integer> sizes = new ArrayList<>();
        for (BedSheetInfo info : bedSheetInfoList) {
            if (info.getMaterial().equals(material)) {
                sizes.add(info.getSize());
            }
        }
        return sizes;
    }

    //вывести для каждой ткани все размеры
    private Map<String, List<Integer>> getListOfSizesForMaterials() {
        Map<String, List<Integer>> materialsWithSizes = new HashMap<>();
        for (BedSheetInfo info : bedSheetInfoList) {
            materialsWithSizes.put(info.getMaterial(), getSizesForMaterials(info.getMaterial()));
        }
        return materialsWithSizes;
    }

    private List<String> getListOfAllColors() {
        List<String> colors = new ArrayList<>();
        for (BedSheetInfo info : bedSheetInfoList) {
            if (!colors.contains(info.getColor())) {
                colors.add(info.getColor());
            }
        }
        return colors;
    }

    private List<String> getColorsForNames(String name) {
        List<String> colors = new ArrayList<>();
        for (BedSheetInfo info : bedSheetInfoList) {
            if (info.getName().equals(name)) {
                colors.add(info.getColor());
            }
        }
        return colors;
    }

    //вывести названия комплектов, которые представлены во всех цветах
    private List<String> getListOfBeddingSetsThatHaveAllColors() {
        List<String> allColors = getListOfAllColors(); //все существующие цвета
        List<String> names = new ArrayList<>(); //список с названиями-результатами
        List<List<String>> colorsForNames = new ArrayList<>();//список списков цветов для каждого имени
        int i = 0;
        for (BedSheetInfo info : bedSheetInfoList) {
            colorsForNames.add(getColorsForNames(info.getName()));
            if (colorsForNames.get(i).containsAll(allColors) && !names.contains(info.getName())) {
                names.add(info.getName());
            }
            i++;
        }
        return names;
    }


    private int getQuarterByMonth(LocalDate date) {
        int result = 0;
        if (date.getMonthValue() == 1 || date.getMonthValue() == 2 || date.getMonthValue() == 3) {
            result = 1;
        } else if (date.getMonthValue() == 4 || date.getMonthValue() == 5 || date.getMonthValue() == 6) {
            result = 2;
        } else if (date.getMonthValue() == 7 || date.getMonthValue() == 8 || date.getMonthValue() == 9) {
            result = 3;
        } else if (date.getMonthValue() == 10 || date.getMonthValue() == 11 || date.getMonthValue() == 12) {
            result = 4;
        }
        return result;
    }

    private int getCountForQuarter(int quarter) {
        int count = 0;
        for (BedSheetInfo info : bedSheetInfoList) {
            if (getQuarterByMonth(info.getDate()) == quarter) {
                count++;
            }
        }
        return count;
    }

    //для каждого квартала, найти количество проданных комплектов
    private Map<Integer, Integer> getListOfCountForQuarters() {
        Map<Integer, Integer> quartersWithCount = new HashMap<>();
        for (BedSheetInfo info : bedSheetInfoList) {
            quartersWithCount.put(getQuarterByMonth(info.getDate()),
                    getCountForQuarter(getQuarterByMonth(info.getDate())));
        }
        return quartersWithCount;
    }

    public void task(List<BedSheetInfo> sportsmenInfoList) {
        this.bedSheetInfoList = sportsmenInfoList;
        System.out.println("Task 1. List of sizes for each kind of materials: ");
        for (Map.Entry<String, List<Integer>> entry : getListOfSizesForMaterials().entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }
        System.out.print("Task 2. List of names of bedding sets which have all colors: "
                + getListOfBeddingSetsThatHaveAllColors());
        System.out.println("Task 3. Count of bedding sets for each quarter: ");
        for (Map.Entry<Integer, Integer> entry : getListOfCountForQuarters().entrySet()) {
            System.out.println(entry.getKey() + " quarter — " + entry.getValue());
        }
    }
}
