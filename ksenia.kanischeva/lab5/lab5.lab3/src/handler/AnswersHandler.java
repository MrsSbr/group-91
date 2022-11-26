package handler;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnswersHandler {
    private final Map<Integer, Integer> answers;
    private Predicate<Integer> predicate;

    public AnswersHandler(Map<Integer, Integer> answers) {
        this.answers = answers;
    }

    private void initialPredicateForPopularPlayers() {
        int maxCountVoices = answers.values().stream().max(Integer::compare).orElse(0);
        predicate = x -> x == maxCountVoices;
    }

    private void initialPredicateForLoserPlayers() {
        predicate = x -> x == 0;
    }

    private void initialPredicateForSelectedPlayers() {
        predicate = x -> x != 0;
    }

    private Set<Integer> getPlayers() {
        return answers.entrySet().stream()
                .filter(pair -> predicate.test(pair.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).keySet();
    }

    public Set<Integer> getPopularPlayers() {
        initialPredicateForPopularPlayers();
        return getPlayers();
    }

    public Set<Integer> getLoserPlayers() {
        initialPredicateForLoserPlayers();
        return getPlayers();
    }

    public Set<Integer> getSelectedPlayers() {
        initialPredicateForSelectedPlayers();
        return getPlayers();
    }

    private void lifeCircle() {
        initialPredicateForPopularPlayers();
        getPopularPlayers();

        initialPredicateForLoserPlayers();
        getSelectedPlayers();

        initialPredicateForSelectedPlayers();
        getSelectedPlayers();
    }

    public long getWorkTime() {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 1000; i++) {
            lifeCircle();
        }

        return System.currentTimeMillis() - start;
    }

}
