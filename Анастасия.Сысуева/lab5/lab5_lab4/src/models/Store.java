package models;

import java.time.LocalDate;
import java.util.*;

public class Store {
    private final Map<Integer, Box> beautyBoxes;


    public Store(Map<Integer, Box> beautyBoxes) {
        this.beautyBoxes = beautyBoxes;
    }


    public Integer countProductInBoxes(String productName) {
        final Long[] count = {0L};
        beautyBoxes.forEach((key, value) -> count[0] += value.getProductList().stream().filter(i -> i.equals(productName)).count());
        return count[0].intValue();
    }

    public Map<String, Integer> getCountProductInBoxes() {
        Map<String, Integer> productStatistic = new HashMap<>();
        beautyBoxes.forEach((key, value) -> value.getProductList().forEach(product -> productStatistic.put(product,
                countProductInBoxes(product))));
        return productStatistic;
    }

    public Integer countSumForMonth(LocalDate date) {
        final Integer[] sum = {0};
        beautyBoxes.forEach((key, value) -> value.getStock().getDataCountStatistic().forEach((keyDate, countValue) ->
        {
            if (keyDate.compareTo(date) == 0) {
                sum[0] += countValue;
            }
        }));
        return sum[0];
    }

    public Map.Entry<LocalDate, Integer> findMonthWithMaxCount(Map<LocalDate, Integer> monthStatistics) {
        return monthStatistics.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }

    public Integer getMonthWithBiggestSale() {
        Map<LocalDate, Integer> monthStatistics = new HashMap<>();
        beautyBoxes.forEach((key, value) -> value.getStock().getDataCountStatistic().keySet().forEach(keyData ->
                monthStatistics.put(keyData, countSumForMonth(keyData))));

        return findMonthWithMaxCount(monthStatistics).getKey().getMonthValue();
    }

    public Map<String, Box> findFirstBox() {
        Map<String, Box> keyFirst = new HashMap<>();
        beautyBoxes.forEach((key, value) -> value.getProductList().forEach(product -> {
                    if (!keyFirst.containsKey((product))) {
                        keyFirst.put(product, value);
                    }
                }
        ));
        return keyFirst;
    }

    public Map.Entry<LocalDate, Integer> findMinDate(Map<LocalDate, Integer> monthStatistics) {
        return monthStatistics.entrySet().stream()
                .min(Map.Entry.comparingByKey())
                .orElse(null);
    }

    public Map<String, Integer> getFirstMonthForProducts() {
        Map<String, Integer> productFirstMonth = new HashMap<>();
        findFirstBox().forEach((key, value) -> productFirstMonth.put(key,
                findMinDate(value.getStock().getDataCountStatistic()).getKey().getMonthValue()));
        return productFirstMonth;
    }

}
