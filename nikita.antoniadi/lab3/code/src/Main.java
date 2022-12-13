import Classes.RaceResult;
import Classes.RaceResultsArrayList;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RaceResultsArrayList raceResults = new RaceResultsArrayList();
        List<Integer> awardeesForLastThreeYears = raceResults.getAwardeesForLastThreeYears();
        List<RaceResult> awardeesForLastThreeYearsResults = new LinkedList<>();
        List<Integer> lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYears =
                raceResults.lastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears();
        List<RaceResult> lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYearsResults = new LinkedList<>();

        for (int awardee : awardeesForLastThreeYears) {
            awardeesForLastThreeYearsResults.addAll(raceResults.getCyclistsResults(awardee));
        }

        for (RaceResult raceResult : awardeesForLastThreeYearsResults) {
            System.out.println(raceResult);
        }

        System.out.println("---------------------------\n");
        System.out.println(raceResults.countWinners());
        System.out.println("---------------------------\n");

        for (int awardee : lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYears) {
            lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYearsResults.addAll(raceResults.getCyclistsResults(awardee));
        }

        for (RaceResult raceResult : lastYearAwardeesThatHasNotBeenAwardeeForLastFiveYearsResults) {
            System.out.println(raceResult);
        }
    }
}