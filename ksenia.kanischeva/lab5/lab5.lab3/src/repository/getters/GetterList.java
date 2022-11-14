package repository.getters;

import repository.helper.CheckerData;

import java.util.*;

public abstract class GetterList {
    protected final static int COUNT_PLAYERS = 22;
    protected final static int COUNT_FUNS = 3000;

    private final Map<Integer, Integer> answers = new HashMap<>();

    protected abstract int nextInt();

    public abstract void fillList();

    private Set<Integer> survey() {
        Set<Integer> answersOnePerson = new HashSet<>();
        while (answersOnePerson.size() != 3) {
            int player = nextInt();
            if (CheckerData.isCorrectPlayer(player)) {
                if (answersOnePerson.contains(player)) {
                    System.out.println("Игрок вами уже был выбран");
                }
                answersOnePerson.add(player);
            } else {
                System.out.println("Игрок не найден");
            }
        }

        return answersOnePerson;
    }

    private void saveAnswers(Set<Integer> answersOnePerson) {
        answersOnePerson.forEach(ans -> {
            if (answers.containsKey(ans)) {
                answers.put(ans, answers.get(ans) + 1);
            } else {
                answers.put(ans, 1);
            }
        });
    }

    protected void saveAnswers() {
        for (int i = 0; i < COUNT_FUNS; i++) {
            Set<Integer> answersOnePerson = survey();
            saveAnswers(answersOnePerson);
            answersOnePerson.clear();
        }
    }

    public Map<Integer, Integer> getAnswers() {
        return answers;
    }
}
