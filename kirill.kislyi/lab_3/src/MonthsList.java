import java.util.LinkedList;
import java.util.List;

public class MonthsList {
    private final boolean timeCheckState;
    private final List<Month> monthNotes;

    MonthsList(boolean timeCheckState, List<Month> list) {
        this.timeCheckState = timeCheckState;
        monthNotes = list;
    }

    void Proc(int year, double low) {
        long startTime = System.nanoTime();

        for (int y = 0; y < 5; y++) {
            for (int m = 1; m <= 12 ; m++) {
                monthNotes.add(new Month(m));
            }
        }

        if (!timeCheckState) {
            System.out.println("Месяцы, когда температура была ниже указанной: " + Processing.getLowerTemp(monthNotes, low));
            System.out.println("Самый(е) жаркий(е) месяц(ы): " + Processing.getHighestTemp(monthNotes));
            System.out.println("Средняя температура за введённый год: " + Processing.getAverageTemperature(monthNotes, year));
        }

        startTime = System.nanoTime() - startTime;
        if (timeCheckState) {
            System.out.printf("Времени затрачено:%,9.4f мс\n", startTime / 1000000.0);
        }
    }
}
