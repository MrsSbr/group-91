package services.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AnswersHandler {
    private final List<Integer> answers;
    private Predicate<Integer> predicate;

    public AnswersHandler(List<Integer> answers) {
        this.answers = answers;
    }

    private void initialPredicateForPopularPlayers() {
        int maxCountVoices = answers.stream().max(Integer::compare).orElseThrow();
        predicate = x -> x == maxCountVoices;
    }

    private void initialPredicateForLoserPlayers() {
        predicate = x -> x == 0;
    }

    private void initialPredicateForSelectedPlayers() {
        predicate = x -> x != 0;
    }

    private List<Integer> getPlayers() {
        List<Integer> players = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            int countVoices = answers.get(i);
            if (predicate.test(countVoices)) {
                players.add(i + 1);
            }
        }
        return players;
    }

    public List<Integer> getPopularPlayers() {
        initialPredicateForPopularPlayers();
        return getPlayers();
    }

    public List<Integer> getLoserPlayers() {
        initialPredicateForLoserPlayers();
        return getPlayers();
    }

    public List<Integer> getSelectedPlayers() {
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
