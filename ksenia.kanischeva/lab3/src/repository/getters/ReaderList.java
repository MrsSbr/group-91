package repository.getters;

import java.util.*;

public class ReaderList extends GetterList {


    @Override
    protected int nextInt() throws InputMismatchException {
        return ReaderInteger.nextInt();
    }

    @Override
    protected void fillList() {
        try {
            saveAnswers();
        } catch (InputMismatchException e) {
            System.out.println("Некорректные данные");
            saveAnswers();
        }
    }
}
