package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ListInformation {

    public static final int SIZE = 10000;

    List<Information> informationLists;

    public ListInformation(List<Information> listInformation) {

        this.informationLists = listInformation;
    }

    public void fillingInTheList() {

        for (int i = 0; i < SIZE; i++) {
            Information information = new Information();
            information.fillingInInformation();
            informationLists.add(information);
        }
    }

    public List<String> listOfDrinksOrderedFrom7To9Morning(int k) {

        List<String> answerList;

        if (k == 1) {
            answerList = new ArrayList<>();
        } else {
            answerList = new LinkedList<>();
        }

        LocalTime firstTime = LocalTime.of(7, 0);
        LocalTime secondTime = LocalTime.of(9, 0);

        for (int i = 0; i < SIZE; i++) {

            LocalTime time = informationLists.get(i).getTimeOfPreparationOfTheDrink();

            if (time.isAfter(secondTime) && firstTime.isBefore(time) &&
                    !answerList.contains(informationLists.get(i).getNameOfTheDrink())) {
                answerList.add(informationLists.get(i).getNameOfTheDrink());
            }
        }

        return answerList;
    }

    public List<String> listOfDrinksThatHaveNotBeenOrderedFor3Months(int k) {

        List<String> answerList;

        if (k == 1) {
            answerList = new ArrayList<>();
        } else {
            answerList = new LinkedList<>();
        }

        LocalDate date = LocalDate.now().minusMonths(3);

        for (int i = 0; i < SIZE; i++) {

            LocalDate date1 = informationLists.get(i).getDateOfPreparationOfTheDrink();

            if (!date1.isBefore(date) && !answerList.contains(informationLists.get(i).getNameOfTheDrink())) {

                answerList.add(informationLists.get(i).getNameOfTheDrink());
            }
        }

        return answerList;
    }

    public int countOfPreparedCappuccinoForAllTime() {

        int countOfPreparedCappuccino = 0;

        for (int i = 0; i < SIZE; i++) {

            if (Objects.equals(informationLists.get(i).getNameOfTheDrink(), "Капучино")) {
                countOfPreparedCappuccino++;
            }
        }
        return countOfPreparedCappuccino;
    }
}