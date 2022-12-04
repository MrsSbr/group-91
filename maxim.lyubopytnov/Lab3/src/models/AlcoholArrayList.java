package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AlcoholArrayList {
    private final boolean timeCheckState;
    private final int ALCOHOL_NOTES_NUM;
    private final List<AlcoholNotes> alcoholNotes = new ArrayList<>();

    AlcoholArrayList(boolean timeCheckState, int ALCOHOL_NOTES_NUM) {
        this.timeCheckState = timeCheckState;
        this.ALCOHOL_NOTES_NUM = ALCOHOL_NOTES_NUM;
    }

    void Proc() {
        long startTime = System.nanoTime();

        for (int i = 0; i < ALCOHOL_NOTES_NUM; i++) {
            alcoholNotes.add(new AlcoholNotes());
        }

        Set<String> alcoholSet = Processing.getListTastedDrinks(alcoholNotes);
        if (!timeCheckState) {
            System.out.println("Список продегустированных напитков: ");
            for (String alcohol : alcoholSet) {
                System.out.println(alcohol);
            }
            System.out.println("Объем выпитого алкоголя: " + Processing.getAmountAlcoholConsumed(alcoholNotes));
            System.out.println("Среднее количество выпитого спирта для достижения пика Балмера: " + Processing.getAvgDrunkAlcoholToPeakBallmer(alcoholNotes));
        }

        startTime = System.nanoTime() - startTime;
        if (timeCheckState) {
            System.out.printf("Времени затрачено:%,9.4f мс\n", startTime / 1000000.0);
        }
    }
}
