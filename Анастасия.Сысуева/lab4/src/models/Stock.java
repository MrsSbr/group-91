package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Stock {
    private final Map<LocalDate, Integer> dataCountStatistic = new HashMap<>();

    public void addDataCountStatistics(LocalDate date, Integer count) {
        dataCountStatistic.put(date, count);
    }

    public Map<LocalDate, Integer> getDataCountStatistic() {
        return dataCountStatistic;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }

        if (ob == null || ob.getClass() != getClass()) {
            return false;
        }

        Stock stock = (Stock) ob;

        return Objects.equals(this.dataCountStatistic, stock.dataCountStatistic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataCountStatistic);
    }
}
