import models.FarmStatistics;
import service.Task;

import java.util.ArrayList;
import java.util.List;

import static service.ConsoleReader.getIntInRange;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        int ch = 1;
        while (ch != 0) {
            System.out.println("[1] выполнить таски");
            System.out.println("[2] Сравнить производительность");
            System.out.println("[0] Выход");
            ch = getIntInRange(0, 2);
            switch (ch) {
                case (1) -> {
                    List<FarmStatistics> farmStatistics = new ArrayList<>();
                    task.randomGenerateRecords(farmStatistics);

                    System.out.println("Лучший месяц по соотношению: ");
                    System.out.println(task.bestMonth(farmStatistics));

                    System.out.println("Сколько, в среднем, в неделю коровы дают молока: ");
                    System.out.println(task.avgMilk(farmStatistics));

                    System.out.println("Суммарный объем съеденного корма: ");
                    System.out.println(task.inTotalFood(farmStatistics));
                }
                case (2) -> {
                    task.compareOnArray();
                    task.compareOnList();
                }
            }
        }
    }
}