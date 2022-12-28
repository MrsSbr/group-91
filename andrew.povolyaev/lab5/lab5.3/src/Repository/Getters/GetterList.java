package Repository.Getters;

import Repository.Help.DataChecker;

import java.util.*;

public abstract class GetterList {
    protected final static int AMOUNT_OF_PLAYERS = 22;
    protected final static int AMOUNT_OF_MATCHES = 1000;

    private final Map<Integer, Integer> results = new HashMap<>();

    protected abstract int nextInt();

    public abstract void fillList();

    private Set<Integer> survey() {
        int check = 0;
        Set<Integer> resultOfOneMatch = new HashSet<>();
        while (resultOfOneMatch.size() != 2) {
            int player = nextInt();
            if (DataChecker.isCorrectNumber(player)) {
                if (resultOfOneMatch.size() == 0) {
                    resultOfOneMatch.add(player);
                    check = player;
                } else {
                    if (!(player == check || (player > 11 && check > 11) || (player < 11 && check < 11))) {
                        resultOfOneMatch.add(player);
                    }
                }
            } else {
                System.out.println("Player is not found");
            }
        }
        return resultOfOneMatch;
    }

    private void saveAnswers(Set<Integer> resultOfOneMatch) {
        resultOfOneMatch.forEach(ans -> {
            if (results.containsKey(ans)) {
                results.put(ans, results.get(ans) + 1);
            } else {
                results.put(ans, 1);
            }
        });
    }

    protected void saveAnswers() {
        for (int i = 0; i < AMOUNT_OF_MATCHES; i++) {
            Set<Integer> resultsOfOneMatch = survey();
            saveAnswers(resultsOfOneMatch);
            resultsOfOneMatch.clear();
        }
    }

    public Map<Integer, Integer> getResults() {
        return results;
    }
}
