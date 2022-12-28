import Classes.RaceResult;
import Classes.RaceResultsArrayList;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        run(true);
        long end = System.currentTimeMillis();
        long arrayListTimeDelta = end - start;

        start = System.currentTimeMillis();
        run(false);
        end = System.currentTimeMillis();
        long linkedListTimeDelta = end - start;

        System.out.println(String.format("Время выполнения с ArrayList: %d\nВремя выполнения с LinkedList: %d",
                arrayListTimeDelta, linkedListTimeDelta));
    }

    public static void run(boolean useArrayList) {
        RaceResultsArrayList raceResults = new RaceResultsArrayList(useArrayList);
        Set<Integer> awardeesForLastThreeYears = raceResults.effectiveGetAwardeesForLastThreeYears();
        Set<RaceResult> awardeesForLastThreeYearsResults = new HashSet<>();
        Set<Integer> lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYears =
                raceResults.effectiveLastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears();
        Set<RaceResult> lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYearsResults = new HashSet<>();

        for (int awardee : awardeesForLastThreeYears) {
            awardeesForLastThreeYearsResults.addAll(raceResults.getCyclistsResultsTest(awardee));
        }

        for (RaceResult raceResult : awardeesForLastThreeYearsResults) {
            System.out.println(raceResult);
        }

        System.out.println("---------------------------\n");
        System.out.println(raceResults.countWinners());
        System.out.println("---------------------------\n");

        for (int awardee : lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYears) {
            lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYearsResults.addAll(raceResults.getCyclistsResultsTest(awardee));
        }

        for (RaceResult raceResult : lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYearsResults) {
            System.out.println(raceResult);
        }
    }
}