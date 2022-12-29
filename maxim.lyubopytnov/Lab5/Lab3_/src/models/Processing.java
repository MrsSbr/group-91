package models;

import java.util.*;
import java.util.stream.*;

public class Processing {
    private static final int ALCOHOL_NOTES_NUM = 20;
    private static List<AlcoholNotes> alcoholNotes;

    public static Set<String> getListTastedDrinks() {
        Set<String> tastedDrinks = new HashSet<>();
        alcoholNotes.stream().forEach(an -> tastedDrinks.add(an.getAlcoholType()));
        return tastedDrinks;
    }

    public static double getAmountAlcoholConsumed() {
        return alcoholNotes.stream()
                .mapToDouble(AlcoholNotes::getNumDrinkingAlcohol)
                .sum();
    }

    public static double getAvgDrunkAlcoholToPeakBallmer() {
        return alcoholNotes.stream()
                .filter(AlcoholNotes::getResultPeakBallmer)
                .collect(Collectors.averagingDouble(AlcoholNotes::getNumDrinkingAlcohol));
    }

    public void mainProcessing(List<AlcoholNotes> alcoholNotesList, boolean timeCheckState) {
        alcoholNotes = alcoholNotesList;
        AlcoholList aal = new AlcoholList(alcoholNotes, timeCheckState, ALCOHOL_NOTES_NUM);
        aal.process();
    }
}
