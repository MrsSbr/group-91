package UI;

import java.util.List;

public final class Printer {
    private Printer() {
    }

    public static void printResults(List<Integer> results) {
        if (results.isEmpty()) {
            System.out.println("Нет таких игроков");
            return;
        }
        System.out.println(results);
    }
}
