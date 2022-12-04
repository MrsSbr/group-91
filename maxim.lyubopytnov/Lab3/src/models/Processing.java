package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Processing {
    private static final int ALCOHOL_NOTES_NUM = 20;

    public static Set<String> getListTastedDrinks(List<AlcoholNotes> alcoholNotes) {
        Set<String> tastedDrinks = new HashSet<>();
        for (AlcoholNotes an : alcoholNotes) {
            tastedDrinks.add(an.getAlcoholType());
        }
        return tastedDrinks;
    }

    public static double getAmountAlcoholConsumed(List<AlcoholNotes> alcoholNotes) {
        double sum = 0;
        for (AlcoholNotes an : alcoholNotes) {
            sum += an.getNumDrinkingAlcohol();
        }
        return sum;
    }

    public static double getAvgDrunkAlcoholToPeakBallmer(List<AlcoholNotes> alcoholNotes) {
        double drunkAlcohol = 0.0;
        int countAlcohol = 0;
        for (AlcoholNotes tp : alcoholNotes) {
            if (tp.getResultPeakBallmer()) {
                drunkAlcohol += tp.getNumDrinkingAlcohol();
                ++countAlcohol;
            }
        }
        return drunkAlcohol / countAlcohol;
    }

    public void mainProcessing(boolean timeCheckState) {
        System.out.println("ArrayList: ");
        AlcoholArrayList aal = new AlcoholArrayList(timeCheckState, ALCOHOL_NOTES_NUM);
        aal.Proc();

        System.out.println();

        System.out.println("LinkedList: ");
        AlcoholLinkedList all = new AlcoholLinkedList(timeCheckState, ALCOHOL_NOTES_NUM);
        all.Proc();
    }
}
