package Repository.Getters;

import Repository.Help.DataChecker;
import java.util.*;

public abstract class GetterList {
    protected final static int AMOUNT_OF_PLAYERS = 22;
    protected final static int AMOUNT_OF_MATCHES = 1000;

    private List<Integer> results = null;

    protected abstract int nextInt();
    protected abstract void fillList();

    private void survey(Set<Integer> resultOfOneMatch){
        int check = 0;
        while (resultOfOneMatch.size() != 2){
            int player = nextInt();
            if (DataChecker.isCorrectNumber(player)){
                if (resultOfOneMatch.size() == 0){
                    resultOfOneMatch.add(player);
                    check = player;
                }
                else {
                    if (!(player == check || (player > 11 && check > 11) || (player < 11 && check < 11))){
                        resultOfOneMatch.add(player);
                    }
                }
            }  else {
                System.out.println("Player is not found");
            }
        }
    }

    private void saveAnswers(Set<Integer> resultOfOneMatch){
        for (Integer result: resultOfOneMatch){
            results.set(result-1, results.get(result - 1) + 1);
        }
    }

    protected void saveAnswers(){
        Set<Integer> resultsOfOneMatch = new HashSet<>();

        for (int i = 0; i < AMOUNT_OF_MATCHES; i++) {
            survey(resultsOfOneMatch);
            saveAnswers(resultsOfOneMatch);
            resultsOfOneMatch.clear();
        }
    }

    public void createArrayList(){
        results = new ArrayList<>(Collections.nCopies(AMOUNT_OF_PLAYERS,0));
        fillList();
    }

    public void createLinkedList(){
        results = new LinkedList<>(Collections.nCopies(AMOUNT_OF_PLAYERS,0));
        fillList();
    }

    public List<Integer> getResults() {
        return results;
    }
}
