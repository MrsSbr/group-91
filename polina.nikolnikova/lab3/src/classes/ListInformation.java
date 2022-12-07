package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<String> listOfDrinksOrderedFrom7To9Morning() {

        List<String> answerList = new ArrayList<>();
        LocalTime firstTime = LocalTime.of(7, 0);
        LocalTime secondTime = LocalTime.of(9, 0);

        for (int i = 0; i < SIZE; i++) {

            LocalTime time = informationLists.get(i).timeOfPreparationOfTheDrink;

            if (time.isAfter(secondTime) && firstTime.isBefore(time) &&
                    !answerList.contains(informationLists.get(i).nameOfTheDrink)) {
                answerList.add(informationLists.get(i).nameOfTheDrink);
            }
        }

        return answerList;
    }

    public List<String> listOfDrinksThatHaveNotBeenOrderedFor3Months() {

        List<String> answerList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        LocalDate date = LocalDate.now().minusMonths(3);

        for (int i = 0; i < SIZE; i++) {

            LocalDate date1 = informationLists.get(i).dateOfPreparationOfTheDrink;

            if (!date1.isBefore(date) && !answerList.contains(informationLists.get(i).nameOfTheDrink)) {

                answerList.add(informationLists.get(i).nameOfTheDrink);
            }
        }

        return answerList;
    }

    public int countOfPreparedCappuccinoForAllTime() {

        int countOfPreparedCappuccino = 0;

        for (int i = 0; i < SIZE; i++) {

            if (Objects.equals(informationLists.get(i).nameOfTheDrink, "Капучино")) {
                countOfPreparedCappuccino++;
            }
        }
        return countOfPreparedCappuccino;
    }
}
