package repository.getters;

import java.util.List;

public class GeneratorList extends GetterList {

    @Override
    protected int nextInt() {
        return (int) (Math.random() * COUNT_PLAYERS) + 1;
    }

    @Override
    protected void fillList() {
        saveAnswers();
    }
}
