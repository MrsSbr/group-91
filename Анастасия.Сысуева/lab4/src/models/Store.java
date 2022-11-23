package models;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Store {
    private final Map<Integer, Box> beautyBoxes;


    public Store(Map<Integer, Box> beautyBoxes) {
        this.beautyBoxes = beautyBoxes;
    }

    public Integer countProductInBoxes(String productName) {
        AtomicReference<Integer> count = new AtomicReference<>(0);
        beautyBoxes.forEach((key, value) -> value.getProductList().forEach(product -> {
            if (product.equals(productName)) {
                count.getAndSet(count.get() + 1);
            }
        }));
        return count.get();
    }

    public Map<String, Integer> getCountProductInBoxes() {
        Map<String, Integer> productStatistic = new HashMap<>();
        beautyBoxes.forEach((key, value) -> value.getProductList().forEach(product ->
                productStatistic.put(product, countProductInBoxes(product))));
        return productStatistic;
    }

    public Integer countSumForMonth(Calendar calendar) {
        AtomicInteger sum = new AtomicInteger();
        beautyBoxes.forEach((key, value) -> value.getDataCountStatistic().forEach((secondKey, secondValue) -> {
                    if (secondKey.compareTo(calendar) == 0) {
                        sum.addAndGet(secondValue);
                    }
                }
        ));
        return sum.get();
    }

    public Map.Entry<Calendar, Integer> findMonthWithMaxCount(Map<Calendar, Integer> monthStatistics) {
        Map.Entry<Calendar, Integer> maxEntry = null;
        for (Map.Entry<Calendar, Integer> entry : monthStatistics.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    public Integer getMonthWithBiggestSale() {
        Map<Calendar, Integer> monthStatistics = new HashMap<>();
        beautyBoxes.forEach((key, value) -> value.getDataCountStatistic().forEach((secondKey, secondValue) ->
                monthStatistics.put(secondKey, countSumForMonth(secondKey))));
        return findMonthWithMaxCount(monthStatistics).getKey().get(Calendar.MONTH) + 1;
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

    public Map.Entry<Calendar, Integer> findMinDate(Map<Calendar, Integer> monthStatistics) {
        Map.Entry<Calendar, Integer> minEntry = null;
        for (Map.Entry<Calendar, Integer> entry : monthStatistics.entrySet()) {
            if (minEntry == null || entry.getKey().before(minEntry.getKey())) {
                minEntry = entry;
            }
        }

        return minEntry;
    }

    public Map<String, Integer> getFirstMonthForProducts() {
        Map<String, Integer> productFirstMonth = new HashMap<>();
        findFirstBox().forEach((key, value) -> productFirstMonth.put(key,
                findMinDate(value.getDataCountStatistic()).getKey().get(Calendar.MONTH) + 1));
        return productFirstMonth;
    }

}
