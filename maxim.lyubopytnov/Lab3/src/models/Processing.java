package models;

import java.util.*;

public class Processing {
    private static final int ALCOHOL_NOTES_NUM = 20;
    private static List<AlcoholNotes> alcoholNotes;

    public static Set<String> getListTastedDrinks() {
        Set<String> tastedDrinks = new HashSet<>();
        for (AlcoholNotes an : alcoholNotes) {
            tastedDrinks.add(an.getAlcoholType());
        }
        return tastedDrinks;
    }

    public static double getAmountAlcoholConsumed() {
        double sum = 0;
        for (AlcoholNotes an : alcoholNotes) {
            sum += an.getNumDrinkingAlcohol();
        }
        return sum;
    }

    public static double getAvgDrunkAlcoholToPeakBallmer() {
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

    public void mainProcessing(List<AlcoholNotes> alcoholNotesList, boolean timeCheckState) {
        alcoholNotes = alcoholNotesList;
        AlcoholList aal = new AlcoholList(alcoholNotes, timeCheckState, ALCOHOL_NOTES_NUM);
        aal.process();
    }
}
