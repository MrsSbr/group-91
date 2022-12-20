import classes.ListInformation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CoffeeShopNew {
    private static final int SIZE = 10000;

    public static void main(String[] args) {

        long startTime;
        long timeSpeedLinkedList;
        long timeSpeedArrayList;

        startTime = System.currentTimeMillis();

        ListInformation listArray = new ListInformation(new ArrayList<>(SIZE));
        listArray.fillingInTheList();
        listArray.listOfDrinksOrderedFrom7To9Morning(1);
        listArray.listOfDrinksThatHaveNotBeenOrderedFor3Months(1);
        listArray.countOfPreparedCappuccinoForAllTime();
        timeSpeedArrayList = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();

        ListInformation listLinked = new ListInformation(new LinkedList<>());
        listLinked.fillingInTheList();
        listLinked.listOfDrinksOrderedFrom7To9Morning(2);
        listLinked.listOfDrinksThatHaveNotBeenOrderedFor3Months(2);
        listLinked.countOfPreparedCappuccinoForAllTime();
        timeSpeedLinkedList = System.currentTimeMillis() - startTime;

        System.out.println("Скорость для ArrayList: " + timeSpeedArrayList);
        System.out.println("Скорость для LinkedList: " + timeSpeedLinkedList);

        int k = timeSpeedArrayList > timeSpeedLinkedList ? 2 : 1;

        System.out.println();
        System.out.println("Список напитков, которые заказывают по утрам с 7 до 9");

        List<String> listAnswer1 = k == 1 ?
                listArray.listOfDrinksOrderedFrom7To9Morning(k) :
                listLinked.listOfDrinksOrderedFrom7To9Morning(k);

        listAnswer1.forEach(System.out::println);

        System.out.println();
        System.out.println("Список напитков, которые не заказывали последние 3 месяца");
        List<String> listAnswer2 = k == 1 ?
                listArray.listOfDrinksThatHaveNotBeenOrderedFor3Months(k) :
                listLinked.listOfDrinksThatHaveNotBeenOrderedFor3Months(k);

        if (listAnswer2.size() != 0) {
            listAnswer2.forEach(System.out::println);
        } else {
            System.out.println("Таких напитков нет");
        }

        System.out.println();
        System.out.println("Количество приготовленных капучино за все время");

        if (k == 1) {
            System.out.println(listArray.countOfPreparedCappuccinoForAllTime());
        } else {
            System.out.println(listLinked.countOfPreparedCappuccinoForAllTime());
        }
    }
}