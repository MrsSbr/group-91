package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListInformation {

    public static final int SIZE = 10000;

    List<Information> informationLists;

    public ListInformation(List<Information> listInformation) {

        this.informationLists = listInformation;
    }

    public void fillingInTheList() {

        Stream.iterate(0, i -> i + 1).limit(SIZE).toList().forEach(inform ->
                informationLists.add(new Information()));
    }

    public List<String> listOfDrinksOrderedFrom7To9Morning(int k) {

        List<String> answerList = k == 1 ? new ArrayList<>() : new LinkedList<>();

        LocalTime firstTime = LocalTime.of(7, 0);
        LocalTime secondTime = LocalTime.of(9, 0);

        informationLists.forEach(information -> {
            LocalTime time = informationLists.get(informationLists.indexOf(information)).
                    getTimeOfPreparationOfTheDrink();

            if (time.isAfter(secondTime) && firstTime.isBefore(time) &&
                    !answerList.contains(informationLists.get(informationLists.indexOf(information)).
                            getNameOfTheDrink())) {
                answerList.add(informationLists.get(informationLists.indexOf(information)).getNameOfTheDrink());
            }
        });

        return answerList;
    }

    public List<String> listOfDrinksThatHaveNotBeenOrderedFor3Months(int k) {

        List<String> answerList = k == 1 ? new ArrayList<>() : new LinkedList<>();

        LocalDate date = LocalDate.now().minusMonths(3);

        informationLists.forEach(information -> {
            LocalDate date1 = informationLists.get(informationLists.indexOf(information)).
                    getDateOfPreparationOfTheDrink();

            if (!date1.isBefore(date) && !answerList.contains(informationLists.get(informationLists.
                    indexOf(information)).getNameOfTheDrink())) {

                answerList.add(informationLists.get(informationLists.indexOf(information)).getNameOfTheDrink());
            }
        });

        return answerList;
    }

    public int countOfPreparedCappuccinoForAllTime() {

        int[] countOfPreparedCappuccino = {0};

        informationLists.forEach(information -> {

            if (Objects.equals(informationLists.get(informationLists.indexOf(information)).getNameOfTheDrink(),
                    "Капучино")) {
                countOfPreparedCappuccino[0]++;
            }
        });

        return countOfPreparedCappuccino[0];
    }
}