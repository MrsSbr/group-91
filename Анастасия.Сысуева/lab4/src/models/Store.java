package src.models;

import java.time.LocalDate;
import java.util.*;

public class Store {
    private final Map<Integer, Box> beautyBoxes;


    public Store(Map<Integer, Box> beautyBoxes) {
        this.beautyBoxes = beautyBoxes;
    }

    public Integer countProductInBoxes(String productName) {
        Integer count = 0;
        for (Box value : beautyBoxes.values()) {
            for (String product : value.getProductList()) {
                if (product.equals(productName)) {
                    count++;
                }
            }
        }
        return count;
    }

    public Map<String, Integer> getCountProductInBoxes() {
        Map<String, Integer> productStatistic = new HashMap<>();
        for (Box value : beautyBoxes.values()) {
            for (String product : value.getProductList()) {
                productStatistic.put(product, countProductInBoxes(product));
            }
        }
        return productStatistic;
    }

    public Integer countSumForMonth(LocalDate date) {
        Integer sum = 0;
        for (Box value : beautyBoxes.values()) {
            for (LocalDate key : value.getStock().getDataCountStatistic().keySet()) {
                if (key.compareTo(date) == 0) {
                    sum += value.getStock().getDataCountStatistic().get(key);
                }
            }
        }
        return sum;
    }

    public Map.Entry<LocalDate, Integer> findMonthWithMaxCount(Map<LocalDate, Integer> monthStatistics) {
        Map.Entry<LocalDate, Integer> maxEntry = null;
        for (Map.Entry<LocalDate, Integer> entry : monthStatistics.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    public Integer getMonthWithBiggestSale() {
        Map<LocalDate, Integer> monthStatistics = new HashMap<>();
        for (Box value : beautyBoxes.values()) {
            for (LocalDate key : value.getStock().getDataCountStatistic().keySet()) {
                monthStatistics.put(key, countSumForMonth(key));
            }
        }
        return findMonthWithMaxCount(monthStatistics).getKey().getMonthValue();
    }

    public Map<String, Box> findFirstBox() {
        Map<String, Box> keyFirst = new HashMap<>();
        for (Box value : beautyBoxes.values()) {
            for (String product : value.getProductList()) {
                if (!keyFirst.containsKey((product))) {
                    keyFirst.put(product, value);
                }
            }
        }
        return keyFirst;
    }

    public Map.Entry<LocalDate, Integer> findMinDate(Map<LocalDate, Integer> monthStatistics) {
        Map.Entry<LocalDate, Integer> minEntry = null;
        for (Map.Entry<LocalDate, Integer> entry : monthStatistics.entrySet()) {
            if (minEntry == null || entry.getKey().isBefore(minEntry.getKey())) {
                minEntry = entry;
            }
        }

        return minEntry;
    }

    public Map<String, Integer> getFirstMonthForProducts() {
        Map<String, Integer> productFirstMonth = new HashMap<>();
        for (String key : findFirstBox().keySet()) {
            Box box = findFirstBox().get(key);
            productFirstMonth.put(key, findMinDate(box.getStock().getDataCountStatistic()).getKey().getMonthValue());
        }
        return productFirstMonth;
    }

}
