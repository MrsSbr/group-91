package classes;

import enums.ListOfNamesOfStudents;
import enums.ListOfPatronymicsOfStudents;
import enums.ListOfSurnamesOfStudents;

import java.util.List;

public class ListOlympiads {

    public static final int SIZE = 10000;

    List<Olympiad> listOlympiads;

    public ListOlympiads(List<Olympiad> listOlympiad) {

        this.listOlympiads = listOlympiad;
    }

    private int randBetween(int min, int max) {
        return (int) (Math.random() * (max - min) + 1) + min;
    }

    public void fillingInTheList() {

        for (int i = 0; i < SIZE - 11; i++) {
            Olympiad olympiad = new Olympiad();
            olympiad.fillingInDataAboutTheOlympiad();
            listOlympiads.add(olympiad);
        }

        for (int i = 0; i < 11; i++) {
            Olympiad olympiad = new Olympiad(2009 + i, "Математика",
                    i + 1, "Никольникова Полина Алексеевна",
                    ListOfSurnamesOfStudents.getById(randBetween(0, 35)) + " " +
                            ListOfNamesOfStudents.getById(randBetween(0, 35)) + " " +
                            ListOfPatronymicsOfStudents.getById(randBetween(0, 35)),
                    ListOfSurnamesOfStudents.getById(randBetween(0, 35)) + " " +
                            ListOfNamesOfStudents.getById(randBetween(0, 35)) + " " +
                            ListOfPatronymicsOfStudents.getById(randBetween(0, 35)));

            listOlympiads.add(olympiad);
        }
    }

    public Olympiad getListOlympiadByIndex(int i) {
        return listOlympiads.get(i);
    }
}
