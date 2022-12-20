package service;

import models.FarmStatistics;
import models.MonthStats;

import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static models.FarmStatistics.randomGenerate;

public class Task {
    public static final int RECORDS_COUNT = 8517;

    public void randomGenerateRecords(List<FarmStatistics> list) {
        for (int i = 0; i < RECORDS_COUNT; i++) {
            list.add(randomGenerate());
        }
    }


    public Month bestMonth(List<FarmStatistics> list) {
        // Найти лучший месяц по соотношению количество корма/количество молока

        List<MonthStats> ratio = new ArrayList<>();
        for (int i = 0; i < 12; i++) { // все месяцы инициализируются нулями
            ratio.add(new MonthStats(Month.of(i + 1), 0, 0));
        }

        for (int i = 0; i < RECORDS_COUNT; i++) {
            for (int j = 0; j < 12; j++) { // поиск месяца
                if (Month.of(j + 1) == list.get(i).getDate().getMonth()) { // в каждый месяц записывается еда и молоко за все записи
                    ratio.get(j).cntMilk += list.get(i).getMilkQuantity();
                    ratio.get(j).cntFeed += list.get(i).getFeedQuantity();
                    break;
                }
            }
        }

        Month maxMonthRatio = Month.JANUARY;
        double maxRatio = 0;
        for (int i = 0; i < 12; i++) {
            if (ratio.get(i).getRatio() > maxRatio) { // нахождение лучшего соотношения
                maxRatio = ratio.get(i).getRatio();
                maxMonthRatio = ratio.get(i).month;
            }
        }

        return maxMonthRatio;
    }

    public double avgMilk(List<FarmStatistics> list) {
        // Сколько, в среднем, в неделю коровы дают молока
        double cntMilk = 0;
        for (int i = 0; i < RECORDS_COUNT; i++) {
            cntMilk += list.get(i).getMilkQuantity();
        }
        return cntMilk / (double) RECORDS_COUNT;
    }

    public double inTotalFood(List<FarmStatistics> list) {
        // Суммарный объем съеденного корма
        double cntFood = 0;
        for (int i = 0; i < RECORDS_COUNT; i++) {
            cntFood += list.get(i).getFeedQuantity();
        }
        return cntFood;
    }

    public void compareOnArray() {
        System.out.println("С помощью массива:");
        long startTime = System.currentTimeMillis();

        List<FarmStatistics> farmStatistics = new ArrayList<>();
        randomGenerateRecords(farmStatistics);
        test(farmStatistics);

        long endTime = System.currentTimeMillis();

        System.out.println("Это заняло " + (endTime - startTime) + " миллисекунд");
    }

    public void compareOnList() {
        System.out.println("С помощью связанного списка:");
        long startTime = System.currentTimeMillis();

        List<FarmStatistics> farmStatistics = new LinkedList<>();
        randomGenerateRecords(farmStatistics);
        test(farmStatistics);

        long endTime = System.currentTimeMillis();

        System.out.println("Это заняло " + (endTime - startTime) + " миллисекунд");
    }

    private void test(List<FarmStatistics> list) {
        for (int i = 0; i < 5; i++) {
            bestMonth(list);
            avgMilk(list);
            inTotalFood(list);
        }
    }
}
