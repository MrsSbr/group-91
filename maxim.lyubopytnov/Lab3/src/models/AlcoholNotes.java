package models;

import java.util.*;

public class AlcoholNotes {
    private static final double MIN_QUANTITY = 0.1;
    private static final double MAX_QUANTITY = 5.0;
    private final String alcoholType;
    private final double numDrinkingAlcohol;
    private final boolean resultPeakBallmer;
    private String[] alcoholTypeList = new String[]{"Водка", "Коньяк", "Виски", "Ром", "Пиво", "Вино"};

    public AlcoholNotes() {
        Random rnd = new Random();
        this.alcoholType = alcoholTypeList[rnd.nextInt(0, alcoholTypeList.length)];
        this.numDrinkingAlcohol = rnd.nextDouble(MIN_QUANTITY, MAX_QUANTITY);
        this.resultPeakBallmer = rnd.nextBoolean();
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
