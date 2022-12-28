package Repository.Getters;


public class GeneratorList extends GetterList{

    @Override
    protected int nextInt(){
        return (int) (Math.random() * AMOUNT_OF_PLAYERS) + 1;
    }

    @Override
    protected void fillList(){
         saveAnswers();
    }
}
