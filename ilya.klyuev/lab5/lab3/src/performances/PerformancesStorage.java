package performances;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PerformancesStorage {

    private static final int EMPLOYEES_COUNT = 12000;

    private final Map<Performance, Integer> ticketsCountForPerformances =
            Arrays.stream(Performance.values())
                    .collect(Collectors.toMap(x -> x, x -> 0));

    private static Performance generateRandomPerformance() {
        return Performance.fromIndex((int) (Math.random() * Performance.getPerformancesCount()));
    }

    private void addTicketForPerformance(Performance performance) {
        ticketsCountForPerformances.put(performance, ticketsCountForPerformances.get(performance) + 1);
    }

    public void fillRandom() {
        for (int i = 0; i < EMPLOYEES_COUNT; i++) {
            addTicketForPerformance(generateRandomPerformance());
            addTicketForPerformance(generateRandomPerformance());
        }
    }

    private String getPerformancesTicketsString(Stream<Map.Entry<Performance, Integer>> stream) {
        return stream
                .map(x -> x.getKey() + " : " + x.getValue())
                .collect(Collectors.joining("\n"));
    }

    public String getPerformancesTicketsString() {
        return getPerformancesTicketsString(ticketsCountForPerformances.entrySet().stream());
    }

    public String getMaxPopularPerformancesString() {
        int maxTickets = ticketsCountForPerformances.values().stream()
                .max(Integer::compare)
                .orElse(0);

        return getPerformancesTicketsString(
                ticketsCountForPerformances.entrySet().stream()
                        .filter(x -> x.getValue() == maxTickets)
        );
    }

    public String getPerformancesNotTicketsString() {
        return getPerformancesTicketsString(
                ticketsCountForPerformances.entrySet().stream()
                        .filter(x -> x.getValue() == 0)
        );
    }
}
