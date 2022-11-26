package repository.getters;

import java.util.InputMismatchException;

public class ReaderList extends GetterList {


    @Override
    protected int nextInt() throws InputMismatchException {
        return ReaderInteger.nextInt();
    }

    @Override
    public void fillList() {
        try {
            saveAnswers();
        } catch (InputMismatchException e) {
            System.out.println("Некорректные данные");
            saveAnswers();
        }
    }
}
