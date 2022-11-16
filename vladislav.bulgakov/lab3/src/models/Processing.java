package models;

import java.util.*;

public class Processing {
    private static final int PACKAGE_NUM = 10000;
    private static final int MIN_YEAR = 2010;
    private static final int MAX_YEAR = 2023;
    private static final double MIN_WEIGHT = 10;
    private static final double MAX_WEIGHT = 20;
    private static final String teaSorts[] = new String[]{"Keemun", "Huangshan Maofeng", "Lu'an Quapian", "Tai Ping Hou Kui"};

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

    private double getMasOfCurrentSortByYear(List<TeaPackage> packages, String sortName, int year) {
        double sum = 0;
        for (TeaPackage tp : packages) {
            if (sortName.equals(tp.getSort()) && year == tp.getHarvestYear()) {
                sum += tp.getMas();
            }
        }
        return sum;
    }

    private int getMostProductiveYearByCurrentSort(List<TeaPackage> packages, String sortName) {
        int mostProductiveYear = MIN_YEAR;
        double maxProduction = 0;
        for (int i = MIN_YEAR; i < MAX_YEAR; i++) {
            double currentYearProduction = getMasOfCurrentSortByYear(packages, sortName, i);
            if (maxProduction < currentYearProduction) {
                maxProduction = currentYearProduction;
                mostProductiveYear = i;
            }
        }
        return mostProductiveYear;
    }

    private Set<String> getSortsFrom2018Year(List<TeaPackage> packages) {
        Set<String> sorts = new HashSet<>();
        for (TeaPackage tp : packages) {
            if (tp.getHarvestYear() == 2018) {
                sorts.add(tp.getSort());
            }
        }
        return sorts;
    }

    private double getHeaviestPackageOfCurrentSort(List<TeaPackage> packages, String sortName) {
        double maxMas = 0;
        for (TeaPackage tp : packages) {
            if (sortName.equals(tp.getSort()) && tp.getMas() > maxMas) {
                maxMas = tp.getMas();
            }
        }
        return maxMas;
    }

    public void mainProcessing(List<TeaPackage> packages, boolean timeCheckState) {
        for (int i = 0; i < PACKAGE_NUM; i++) {
            packages.add(new TeaPackage(getRandomSort(), getRandomYear(), getRandomMas()));
        }
        long startTime = System.nanoTime();
        for (String name : teaSorts) {
            int mostProductiveYear = getMostProductiveYearByCurrentSort(packages, name);
            double maxWeight = getHeaviestPackageOfCurrentSort(packages, name);
            if (!timeCheckState) {
                System.out.println("Сорт: " + name);
                System.out.println("Самый урожайный год: " + mostProductiveYear);
                System.out.printf("Самая большая масса пакета:%,9.4f\n", maxWeight);
                System.out.println("---");
            }
        }
        Set<String> sortsSet = getSortsFrom2018Year(packages);
        if (!timeCheckState) {
            System.out.println("Сорты чая, собранные в 2018г: ");
            for (String sort : sortsSet) {
                System.out.println(sort);
            }
        }
        startTime = System.nanoTime() - startTime;
        if (timeCheckState) {
            System.out.printf("Времени затрачено:%,9.4f мс\n", startTime / 1000000.0);
        }
    }
}