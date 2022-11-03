package repository.getters;

import repository.helper.CheckerData;

import java.util.*;

public abstract class GetterList {
    protected final static int COUNT_PLAYERS = 22;
    protected final static int COUNT_FUNS = 3000;

    private List<Integer> answers = null;

    protected abstract int nextInt();

    protected abstract void fillList();

    private void survey(Set<Integer> answersOnePerson) {
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
    }

    private void saveAnswers(Set<Integer> answersOnePerson) {
        for (Integer answer : answersOnePerson) {
            answers.set(answer - 1, answers.get(answer - 1) + 1);
        }
    }


    protected void saveAnswers() {
        Set<Integer> answersOnePerson = new HashSet<>();

        for (int i = 0; i < COUNT_FUNS; i++) {
            survey(answersOnePerson);
            saveAnswers(answersOnePerson);
            answersOnePerson.clear();
        }
    }

    public void createArrayList() {
        answers = new ArrayList<>(Collections.nCopies(COUNT_PLAYERS, 0));
        fillList();
    }


    public void createLinkedList() {
        answers = new LinkedList<>(Collections.nCopies(COUNT_PLAYERS, 0));
        fillList();
    }

    public List<Integer> getAnswers() {
        return answers;
    }
}
