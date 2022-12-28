package Classes;

import Work.Processing;

import java.util.List;
import java.util.stream.IntStream;

public class MonthsList {
    private final boolean timeCheckState;
    private final List<Month> monthNotes;

    public MonthsList(boolean timeCheckState, List<Month> list) {
        this.timeCheckState = timeCheckState;
        monthNotes = list;
    }

    public void generateMonths() {

        IntStream.range(0, 60)
                .mapToObj(y -> new Month(y % 12 + 1, y / 12))
                .forEach(monthNotes::add);

    }

    public void proc(int year, double low) {
        long startTime = System.nanoTime();

        generateMonths();

        if (!timeCheckState) {
            List<Month> result = Processing.getLowerTemp(monthNotes, low);
            System.out.println("Месяцы, когда температура была ниже указанной: ");

            result.forEach(Month::print);

            result = Processing.getHighestTemp(monthNotes);
            System.out.println("Самый(е) жаркий(е) месяц(ы): ");

            result.forEach(Month::print);


            System.out.println("Средняя температура за введённый год: " + Processing.getAverageTemperature(monthNotes, year));
        }

        startTime = System.nanoTime() - startTime;
        if (timeCheckState) {
            System.out.printf("Времени затрачено:%,9.4f мс\n", startTime / 1000000.0);
        }
    }
}
