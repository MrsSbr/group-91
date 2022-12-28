package Service;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnswersHandler {
    private final Map<Integer, Integer> results;
    private Predicate<Integer> predicate;

    public AnswersHandler(Map<Integer, Integer> results) {
        this.results = results;
    }

    private void predicateForBestPlayers() {
        int maxMVP = results.values().stream().max(Integer::compare).orElse(0);
        predicate = x -> x == maxMVP;
    }

    private void predicateForOneTimeMVP() {
        predicate = x -> x == 1;
    }

    private void predicateForAwayMVP() {
        predicate = x -> x != 0;
    }

    private Set<Integer> getPlayers() {
        return results.entrySet().stream().filter(pair -> predicate.test(pair.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).keySet();
    }

    public Set<Integer> getBestPlayers() {
        predicateForBestPlayers();
        return getPlayers();
    }

    public Set<Integer> getOneTimeMVPPlayers() {
        predicateForOneTimeMVP();
        return getPlayers();
    }

    public Set<Integer> getAwayMVPPlayers() {
        predicateForAwayMVP();
        return getPlayers();
    }

    public void lifeCycle() {
        predicateForBestPlayers();
        getBestPlayers();
        predicateForOneTimeMVP();
        getOneTimeMVPPlayers();
        predicateForAwayMVP();
        getAwayMVPPlayers();
    }

    public long getWorkTime() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            lifeCycle();
        }

        return System.currentTimeMillis() - start;
    }
}
