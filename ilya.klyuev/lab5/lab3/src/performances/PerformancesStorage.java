package performances;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PerformancesStorage {

    private static final int EMPLOYEES_COUNT = 12000;

    private final Map<Performance, Integer> performancesTicketsStatistic =
            Arrays.stream(Performance.values())
                    .collect(Collectors.toMap(x -> x, x -> 0));

    private static Performance generateRandomPerformance() {
        return Performance.fromIndex((int) (Math.random() * Performance.getPerformancesCount()));
    }

    private void addTicketForPerformance(Performance performance) {
        performancesTicketsStatistic.put(performance, performancesTicketsStatistic.get(performance) + 1);
    }

    public void fillRandom() {
        for (int i = 0; i < EMPLOYEES_COUNT; i++) {
            addTicketForPerformance(generateRandomPerformance());
            addTicketForPerformance(generateRandomPerformance());
        }
    }

    public Map<Performance, Integer> getPerformancesTicketsStatistic() {
        return performancesTicketsStatistic;
    }

    public Set<Performance> getMaxPopularPerformances() {
        int maxTickets = performancesTicketsStatistic.values().stream()
                .max(Integer::compare)
                .orElse(0);

        return performancesTicketsStatistic.entrySet().stream()
                .filter(x -> x.getValue() == maxTickets)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Set<Performance> getPerformancesNotTickets() {
        return performancesTicketsStatistic.entrySet().stream()
                .filter(x -> x.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
