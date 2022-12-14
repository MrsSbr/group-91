package Tasks;

import Models.Miceputers;
import Prints.Prints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tasks {
    public static void Task1(Miceputers miceputers) {
        List<String> answers = miceputers.getAnswersMore3();
        answers.forEach(System.out::println);
    }

    public static void Task2(Miceputers miceputers) {
        HashMap<String, Integer> mps = miceputers.getTimeForAnswers();
        Prints.printMap(mps);
    }

    public static void Task3(Miceputers miceputers) {
        HashMap<String, Integer> mps = miceputers.getTimeForMiceputers();
        mps.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }

}