package models;

import java.util.*;

public class AlcoholNotes {
    private static final double MIN_QUANTITY = 0.1;
    private static final double MAX_QUANTITY = 5.0;
    private final String alcoholType;
    private final double numDrinkingAlcohol;
    private final boolean resultPeakBallmer;
    private final ArrayList<String> alcoholTypeList;

    public AlcoholNotes() {
        Random rnd = new Random();
        this.alcoholTypeList = new ArrayList<>();
        PushAlcoholList();
        this.alcoholType = alcoholTypeList.get(rnd.nextInt(0, alcoholTypeList.size()));
        this.numDrinkingAlcohol = rnd.nextDouble(MIN_QUANTITY, MAX_QUANTITY);
        this.resultPeakBallmer = rnd.nextBoolean();
    }

    public void PushAlcoholList() {
        alcoholTypeList.add("Водка");
        alcoholTypeList.add("Коньяк");
        alcoholTypeList.add("Виски");
        alcoholTypeList.add("Ром");
        alcoholTypeList.add("Пиво");
        alcoholTypeList.add("Вино");
    }

    public String getAlcoholType() {
        return alcoholType;
    }

    public double getNumDrinkingAlcohol() {
        return numDrinkingAlcohol;
    }

    public boolean getResultPeakBallmer() {
        return resultPeakBallmer;
    }

    @Override
    public String toString() {
        return "Тип алкоголя:" + alcoholType +
                "Выпитое количество: " + numDrinkingAlcohol +
                "Достигнут пик?" + resultPeakBallmer + "\n";
    }
}
