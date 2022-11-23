package models;

import java.util.*;

public class Box {
    private final List<String> productList = new ArrayList<>();
    private final Map<Calendar, Integer> dataCountStatistic = new HashMap<>();

    public void addProduct(String product) {
        productList.add(product);
    }

    public void addDataCountStatistics(Calendar calendar, Integer count) {
        dataCountStatistic.put(calendar, count);
    }

    public List<String> getProductList() {
        return productList;
    }

    public Map<Calendar, Integer> getDataCountStatistic() {
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

        Box box = (Box) ob;

        return Objects.equals(this.productList, box.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList);
    }
}
