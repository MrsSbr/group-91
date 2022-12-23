package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AnswersHandler {
    private final List<Integer> results;
    private Predicate<Integer> predicate;

    public AnswersHandler(List<Integer> results){
        this.results = results;
    }

    private void predicateForBestPlayers(){
        int maxMVP = results.stream().max(Integer::compare).orElseThrow();
        predicate = x -> x == maxMVP;
    }

    private void predicateForOneTimeMVP(){
        predicate = x -> x == 1;
    }

    private void predicateForAwayMVP(){
        predicate = x -> x != 0;
    }

    private List<Integer> getPlayers(){
        List<Integer> players = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            int countMVP = results.get(i);
            if (predicate.test(countMVP)){
                players.add(i+1);
            }
        }
        return players;
    }

    public List<Integer> getBestPlayers(){
        predicateForBestPlayers();
        return getPlayers();
    }

    public List<Integer> getOneTimeMVPPlayers() {
        predicateForOneTimeMVP();
        return getPlayers();
    }

    public List<Integer> getAwayMVPPlayers(){
        predicateForAwayMVP();
        return getPlayers();
    }

    public void lifeCycle(){
        predicateForBestPlayers();
        getBestPlayers();
        predicateForOneTimeMVP();
        getOneTimeMVPPlayers();
        predicateForAwayMVP();
        getAwayMVPPlayers();
    }

    public long getWorkTime(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            lifeCycle();
        }

        return System.currentTimeMillis() - start;
    }
}
