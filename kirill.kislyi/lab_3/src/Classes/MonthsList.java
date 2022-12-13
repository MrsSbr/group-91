package Classes;

import Work.Processing;

import java.util.List;

public class MonthsList {
    private final boolean timeCheckState;
    private final List<Month> monthNotes;

    public MonthsList(boolean timeCheckState, List<Month> list) {
        this.timeCheckState = timeCheckState;
        monthNotes = list;
    }

    public void proc(int year, double low) {
        long startTime = System.nanoTime();

        for (int y = 0; y < 5; y++) {
            for (int m = 1; m <= 12 ; m++) {
                monthNotes.add(new Month(m, y));
            }
        }

        if (!timeCheckState) {
            List<Month> result = Processing.getLowerTemp(monthNotes, low);
            System.out.println("Месяцы, когда температура была ниже указанной: ");
            for (Month m : result) {
            m.print();
            }

            result = Processing.getHighestTemp(monthNotes);
            System.out.println("Самый(е) жаркий(е) месяц(ы): ");
            for (Month m : result) {
                m.print();
            }

            System.out.println("Средняя температура за введённый год: " + Processing.getAverageTemperature(monthNotes, year));
        }

        startTime = System.nanoTime() - startTime;
        if (timeCheckState) {
            System.out.printf("Времени затрачено:%,9.4f мс\n", startTime / 1000000.0);
        }
    }
}
