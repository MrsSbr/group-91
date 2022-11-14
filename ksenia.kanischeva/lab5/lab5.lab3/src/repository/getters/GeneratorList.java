package repository.getters;

public class GeneratorList extends GetterList {

    @Override
    protected int nextInt() {
        return (int) (Math.random() * COUNT_PLAYERS) + 1;
    }

    @Override
    public void fillList() {
        saveAnswers();
    }
}
