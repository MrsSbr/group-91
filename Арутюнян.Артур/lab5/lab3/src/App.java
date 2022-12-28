import data.Generator;
import data.Note;
import task.Statistic;

import java.util.List;

public class App {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Statistic statistic = new Statistic();
        List<Note> list = Generator.generateNotes(25000);
        System.out.println(statistic.getLastYearSumPower(list));
        System.out.println(statistic.getAvgPowerForLastThreeMonth(list));
        System.out.println(statistic.getCountMoreFifty(list));
        System.out.println("LinkedList time: " + (System.currentTimeMillis() - time) + "mm");
    }
}