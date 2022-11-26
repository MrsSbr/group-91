package UI;

import java.util.List;
import java.util.Set;

public final class Printer {
    private Printer() {
    }

    public static void printResults(Set<Integer> results) {
        if (results.isEmpty()) {
            System.out.println("Нет таких игроков");
            return;
        }
        System.out.println(results);
    }
}
