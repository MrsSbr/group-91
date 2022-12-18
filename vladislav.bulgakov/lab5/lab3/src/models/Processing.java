package models;

import java.util.*;
import java.util.stream.IntStream;

public class Processing {
    private static final int PACKAGE_NUM = 10000;
    private static final int MIN_YEAR = 2010;
    private static final int MAX_YEAR = 2023;
    private static final double MIN_WEIGHT = 10;
    private static final double MAX_WEIGHT = 20;
    private static final String[] teaSorts = new String[]{"Keemun", "Huangshan Maofeng", "Lu'an Quapian", "Tai Ping Hou Kui"};
    private List<TeaPackage> teaPackages;

    private String getRandomSort() {
        Random rnd = new Random();
        return teaSorts[rnd.nextInt(0, teaSorts.length)];
    }

    private int getRandomYear() {
        Random rnd = new Random();
        return rnd.nextInt(MIN_YEAR, MAX_YEAR);
    }

    private double getRandomMas() {
        Random rnd = new Random();
        return rnd.nextDouble(MIN_WEIGHT, MAX_WEIGHT);
    }

    private double getMasOfCurrentSortByYear(String sortName, int year) {
        return teaPackages.stream().filter(tp -> sortName.equals(tp.getSort()) && year == tp.getHarvestYear()).mapToDouble(TeaPackage::getMas).sum();
    }

    private int getMostProductiveYearByCurrentSort(String sortName) {
        int mostProductiveYear = MIN_YEAR;
        double maxProduction = 0;
        for (int i = MIN_YEAR; i < MAX_YEAR; i++) {
            double currentYearProduction = getMasOfCurrentSortByYear(sortName, i);
            if (maxProduction < currentYearProduction) {
                maxProduction = currentYearProduction;
                mostProductiveYear = i;
            }
        }
        return mostProductiveYear;
    }

    private Set<String> getSortsFrom2018Year() {
        Set<String> sorts = new HashSet<>();
        teaPackages.stream().filter(tp -> tp.getHarvestYear() == 2018).forEach(tp -> sorts.add(tp.getSort()));
        return sorts;
    }

    private double getHeaviestPackageOfCurrentSort(String sortName) {
        return teaPackages.stream().filter(tp -> sortName.equals(tp.getSort())).mapToDouble(TeaPackage::getMas).max().getAsDouble();
    }

    public void mainProcessing(List<TeaPackage> teaPackagesList, boolean timeCheckState) {
        teaPackages = teaPackagesList;
        IntStream.range(0, PACKAGE_NUM).mapToObj(tp -> new TeaPackage(getRandomSort(), getRandomYear(), getRandomMas())).forEach(teaPackages::add);

        long startTime = System.nanoTime();
        for (String name : teaSorts) {
            int mostProductiveYear = getMostProductiveYearByCurrentSort(name);
            double maxWeight = getHeaviestPackageOfCurrentSort(name);
            if (!timeCheckState) {
                System.out.println("Сорт: " + name);
                System.out.println("Самый урожайный год: " + mostProductiveYear);
                System.out.printf("Самая большая масса пакета:%,9.4f\n", maxWeight);
                System.out.println("---");
            }
        }
        Set<String> sortsSet = getSortsFrom2018Year();
        if (!timeCheckState) {
            System.out.println("Сорты чая, собранные в 2018г: ");
            sortsSet.forEach(System.out::println);
        }

        startTime = System.nanoTime() - startTime;
        if (timeCheckState) {
            System.out.printf("Времени затрачено:%,9.4f мс\n", startTime / 1000000.0);
        }
    }
}