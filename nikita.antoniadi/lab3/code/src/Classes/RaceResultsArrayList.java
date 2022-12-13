package Classes;

import java.time.LocalDate;
import java.util.*;

public class RaceResultsArrayList {
    private final List<RaceResult>  raceResults = new ArrayList<>(RACE_RESULTS_AMOUNT);


    private static final int PARTICIPANTS = 17;
    private static final int RACES_AMOUNT = 731;
    private static final int RACE_RESULTS_AMOUNT = PARTICIPANTS * RACES_AMOUNT;

    public RaceResultsArrayList() {
        for (int i = 0; i < RACES_AMOUNT; i++) {
            Random random = new Random();
            int yearRange = random.nextInt(12);
            int monthRange = random.nextInt(12);
            int dayRange = random.nextInt(31);

            LocalDate date = LocalDate.now()
                    .minusYears(yearRange)
                    .minusMonths(monthRange)
                    .minusDays(dayRange);

            for (int j = 0; j < PARTICIPANTS; j++) {
                raceResults.add(new RaceResult(random.nextInt(1000), date, j + 1));
            }
        }
    }

    //Участников гонок, которые занимали призовые места за последние 3 года
    public List<Integer> getAwardeesForLastThreeYears() {
        List<Integer> result = new LinkedList<>();
        int i = 0;
        LocalDate threeYearsAgo = LocalDate.now().minusYears(3);

        while (i < RACE_RESULTS_AMOUNT) {
            RaceResult raceResult = raceResults.get(i);
            if (!result.contains(raceResult.getCyclistNumber()) && raceResult.getRaceDate().isAfter(threeYearsAgo)) {
                result.add(raceResult.getCyclistNumber());
            }

            if (raceResult.getPlace() == 3) {
                i += 15;
            } else {
                i++;
            }
        }
        return result;
    }

    //Посчитать количество спортсменов, которые выигрывали гонку
    public int countWinners() {
        int count = 0;
        LinkedList<Integer> winners = new LinkedList<>();

        for (int i = 0; i < RACE_RESULTS_AMOUNT; i += 17) {
            if (!winners.contains(raceResults.get(i).getCyclistNumber())) {
                winners.add(raceResults.get(i).getCyclistNumber());
                count++;
            }
        }
        return count;
    }

    //Найти всех спортсменов, которые занимали места за последний год, при чем до этого 5 лет участвовали в гонках,
    //но не занимали мест

    public List<Integer> lastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears() {
        List<Integer> result = new LinkedList<>();
        LocalDate yearAgo = LocalDate.now().minusYears(1);
        int i = 0;

        while (i < RACE_RESULTS_AMOUNT) {
            RaceResult raceResult = raceResults.get(i);
            if (!result.contains(raceResult.getCyclistNumber()) && raceResult.getRaceDate().isAfter(yearAgo) &&
                    hasNotBeenAwardeeForLastFiveYearsExceptLastYear(raceResult.getCyclistNumber())) {
                result.add(raceResult.getCyclistNumber());
            }

            if (raceResult.getPlace() == 3) {
                i += 15;
            } else {
                i++;
            }
        }
        return result;
    }

    public boolean hasNotBeenAwardeeForLastFiveYearsExceptLastYear(int cyclistNumber) {
        List<RaceResult> cyclistResults = getCyclistsResults(cyclistNumber);
        LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
        LocalDate yearAgo = LocalDate.now().minusYears(1);

        for (RaceResult raceResult : cyclistResults) {
            if (raceResult.getRaceDate().isBefore(yearAgo) && raceResult.getRaceDate().isAfter(fiveYearsAgo)
                    && raceResult.getPlace() < 4) {
                return false;
            }
        }
        return true;
    }

    public List<RaceResult> getCyclistsResults(int cyclistNumber) {
        List<RaceResult> cyclistsResults = new LinkedList<>();

        for (RaceResult raceResult : raceResults) {
            if (cyclistNumber == raceResult.getCyclistNumber())
                cyclistsResults.add(raceResult);
        }
        return cyclistsResults;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (RaceResult raceResult : raceResults) {
            result.append(raceResult)
                    .append("\n");
        }
        return result.toString();
    }
}
