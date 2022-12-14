package models;

import java.util.*;

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
        double sum = 0;
        for (TeaPackage tp : teaPackages) {
            if (sortName.equals(tp.getSort()) && year == tp.getHarvestYear()) {
                sum += tp.getMas();
            }
        }
        return sum;
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
        for (TeaPackage tp : teaPackages) {
            if (tp.getHarvestYear() == 2018) {
                sorts.add(tp.getSort());
            }
        }
        return sorts;
    }

    private double getHeaviestPackageOfCurrentSort(String sortName) {
        double maxMas = 0;
        for (TeaPackage tp : teaPackages) {
            if (sortName.equals(tp.getSort()) && tp.getMas() > maxMas) {
                maxMas = tp.getMas();
            }
        }
        return maxMas;
    }

    public void mainProcessing(List<TeaPackage> teaPackagesList, boolean timeCheckState) {
        teaPackages = teaPackagesList;
        for (int i = 0; i < PACKAGE_NUM; i++) {
            teaPackages.add(new TeaPackage(getRandomSort(), getRandomYear(), getRandomMas()));
        }
        long startTime = System.nanoTime();
        for (String name : teaSorts) {
            int mostProductiveYear = getMostProductiveYearByCurrentSort(name);
            double maxWeight = getHeaviestPackageOfCurrentSort(name);
            if (!timeCheckState) {
                System.out.println("????????: " + name);
                System.out.println("?????????? ?????????????????? ??????: " + mostProductiveYear);
                System.out.printf("?????????? ?????????????? ?????????? ????????????:%,9.4f\n", maxWeight);
                System.out.println("---");
            }
        }
        Set<String> sortsSet = getSortsFrom2018Year();
        if (!timeCheckState) {
            System.out.println("?????????? ??????, ?????????????????? ?? 2018??: ");
            for (String sort : sortsSet) {
                System.out.println(sort);
            }
        }
        startTime = System.nanoTime() - startTime;
        if (timeCheckState) {
            System.out.printf("?????????????? ??????????????????:%,9.4f ????\n", startTime / 1000000.0);
        }
    }
}