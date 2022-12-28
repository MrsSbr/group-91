package Classes;

import java.time.LocalDate;
import java.util.*;

public class RaceResultsArrayList {
    private final List<RaceResult>  raceResults;
    private static final int PARTICIPANTS = 17;
    private static final int RACES_AMOUNT = 731;
    private static final int RACE_RESULTS_AMOUNT = PARTICIPANTS * RACES_AMOUNT;

    public RaceResultsArrayList(boolean useArrayList) {
        if(useArrayList) {
            raceResults = new ArrayList<>(RACE_RESULTS_AMOUNT);
        } else {
            raceResults = new LinkedList<>();
        }

        Random random = new Random();
        int yearRange = random.nextInt(12);
        int monthRange = random.nextInt(12);
        int dayRange = random.nextInt(31);

        long start = System.currentTimeMillis();
        for (int i = 0; i < RACES_AMOUNT; i++) {
            LocalDate date = LocalDate.now()
                    .minusYears(yearRange)
                    .minusMonths(monthRange)
                    .minusDays(dayRange);

            for (int j = 0; j < PARTICIPANTS; j++) {
                raceResults.add(new RaceResult(random.nextInt(1000), date, j + 1));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (end - start));
    }

    public Set<Integer> effectiveGetAwardeesForLastThreeYears() {
        Set<Integer> linkedHashSet = new LinkedHashSet<>();

        getAwardeesForLastThreeYears(linkedHashSet);
        return linkedHashSet;
    }
    public Set<Integer> getAwardeesForLastThreeYearsTest() {
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        long start = System.currentTimeMillis();

        getAwardeesForLastThreeYears(hashSet);
        long end = System.currentTimeMillis();
        long timeDeltaForHashSet = end - start;

        start = System.currentTimeMillis();
        getAwardeesForLastThreeYears(linkedHashSet);
        end = System.currentTimeMillis();
        long timeDeltaForLinkedHashSet = end - start;

        System.out.println(timeDeltaForHashSet < timeDeltaForLinkedHashSet ?
                "HashSet отрбаотал быстрее" : "LinkedHashSet отработал быстрее");

        return timeDeltaForHashSet < timeDeltaForLinkedHashSet ? hashSet : linkedHashSet;
    }

    public Set<RaceResult> effectiveGetCyclistResults(int cyclistNumber) {
        Set<RaceResult> results = new LinkedHashSet<>();

        getCyclistsResults(cyclistNumber, results);
        return results;
    }
    public Set<RaceResult> getCyclistsResultsTest(int cyclistNumber) {
        Set<RaceResult> hashSet = new HashSet<>();
        Set<RaceResult> linkedHashSet = new LinkedHashSet<>();
        long start = System.currentTimeMillis();

        getCyclistsResults(cyclistNumber, hashSet);
        long end = System.currentTimeMillis();
        long timeDeltaForHashSet = end - start;

        start = System.currentTimeMillis();
        getCyclistsResults(cyclistNumber, linkedHashSet);
        end = System.currentTimeMillis();
        long timeDeltaForLinkedHashSet = end - start;

        System.out.println(timeDeltaForHashSet < timeDeltaForLinkedHashSet ?
                "HashSet отрбаотал быстрее" : "LinkedHashSet отработал быстрее");

        return timeDeltaForHashSet < timeDeltaForLinkedHashSet ? hashSet : linkedHashSet;
    }

    public Set<Integer> effectiveLastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears() {
        Set<Integer> result = new LinkedHashSet<>();

        lastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears(result);
        return result;
    }

    public Set<Integer> lastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYearsTest() {
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        long start = System.currentTimeMillis();

        lastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears(hashSet);
        long end = System.currentTimeMillis();
        long timeDeltaForHashSet = end - start;

        start = System.currentTimeMillis();
        lastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears(linkedHashSet);
        end = System.currentTimeMillis();
        long timeDeltaForLinkedHashSet = end - start;

        System.out.println(timeDeltaForHashSet < timeDeltaForLinkedHashSet ?
                "HashSet отрбаотал быстрее" : "LinkedHashSet отработал быстрее");

        return timeDeltaForHashSet < timeDeltaForLinkedHashSet ? hashSet : linkedHashSet;
    }

    //Участников гонок, которые занимали призовые места за последние 3 года
    public void getAwardeesForLastThreeYears(Set<Integer> result) {
        int i = 0;
        LocalDate threeYearsAgo = LocalDate.now().minusYears(3);

        while (i < RACE_RESULTS_AMOUNT) {
            RaceResult raceResult = raceResults.get(i);
            if (raceResult.getRaceDate().isAfter(threeYearsAgo)) {
                result.add(raceResult.getCyclistNumber());
            }

            if (raceResult.getPlace() == 3) {
                i += 15;
            } else {
                i++;
            }
        }
    }

    //Посчитать количество спортсменов, которые выигрывали гонку
    public int countWinners() {
        Set<Integer> winners = new HashSet<>();

        for (int i = 0; i < RACE_RESULTS_AMOUNT; i += 17) {
            winners.add(raceResults.get(i).getCyclistNumber());
        }
        return winners.size();
    }

    //Найти всех спортсменов, которые занимали места за последний год, при чем до этого 5 лет участвовали в гонках,
    //но не занимали мест

    public void lastYearAwardeesThatHaveNotBeenAwardeeForLastFiveYears(Set<Integer> result) {
        LocalDate yearAgo = LocalDate.now().minusYears(1);
        int i = 0;

        while (i < RACE_RESULTS_AMOUNT) {
            RaceResult raceResult = raceResults.get(i);
            if (raceResult.getRaceDate().isAfter(yearAgo) &&
                    hasNotBeenAwardeeForLastFiveYearsExceptLastYear(raceResult.getCyclistNumber())) {
                result.add(raceResult.getCyclistNumber());
            }

            if (raceResult.getPlace() == 3) {
                i += 15;
            } else {
                i++;
            }
        }
    }

    public boolean hasNotBeenAwardeeForLastFiveYearsExceptLastYear(int cyclistNumber) {
        Set<RaceResult> cyclistResults = effectiveGetCyclistResults(cyclistNumber);
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

    public void getCyclistsResults(int cyclistNumber, Set<RaceResult> cyclistsResults) {
        for (RaceResult raceResult : raceResults) {
            if (cyclistNumber == raceResult.getCyclistNumber())
                cyclistsResults.add(raceResult);
        }
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
