package Repository.Getters;

import java.util.List;

public class GeneratorList extends GetterList {

    @Override
    protected int nextInt() {
        return (int) (Math.random() * AMOUNT_OF_PLAYERS) + 1;
    }

    @Override
    public void fillList() {
        saveAnswers();
    }
}
