package models;

public class TeaPackage {
    private String sort;
    private int harvestYear;
    private double mas;

    public TeaPackage(String sort, int harvestYear, double mas) {
        this.sort = sort;
        this.harvestYear = harvestYear;
        this.mas = mas;
    }

    public String getSort() {
        return sort;
    }

    public int getHarvestYear() {
        return harvestYear;
    }

    public double getMas() {
        return mas;
    }

    @Override
    public String toString() {
        return "Сорт:" + sort + "Год сбора: " + harvestYear + "Масса: " + mas + "\n";
    }
}