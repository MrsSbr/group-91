package src.models;

import models.Stock;

import java.util.*;

public class Box {
    private final List<String> productList = new ArrayList<>();

    private final Stock stock = new Stock();

    public void addProduct(String product) {
        productList.add(product);
    }

    public List<String> getProductList() {
        return productList;
    }

    public Stock getStock() {
        return stock;
    }

    public boolean compareProduct(String[] products) {
        return Arrays.equals((getProductList().toArray(new String[0])), products);
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
