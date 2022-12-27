import Classes.ListOrder;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final int SIZE = 12712;

    public static void main(String[] args) {
        long startTime;
        long timeSpeedLinkedList;
        long timeSpeedArrayList;

        startTime = System.currentTimeMillis();

        ListOrder listArray = new ListOrder(new ArrayList<>(SIZE));
        listArray.fillingInTheList();
        listArray.averageDeliveryTimeFor6Month();
        listArray.listOfDaysWithMaxCountOfOrders(1);
        listArray.totalWeightOfGoods();
        timeSpeedArrayList = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();

        ListOrder listLinked = new ListOrder(new LinkedList<>());
        listLinked.fillingInTheList();
        listLinked.listOfDaysWithMaxCountOfOrders(2);
        listLinked.totalWeightOfGoods();
        timeSpeedLinkedList = System.currentTimeMillis() - startTime;

        System.out.println("Скорость для ArrayList: " + timeSpeedArrayList);
        System.out.println("Скорость для LinkedList: " + timeSpeedLinkedList);

        int k = timeSpeedArrayList > timeSpeedLinkedList ? 2 : 1;

        System.out.println();
        System.out.println("Среднее время доставки за последние 6 месяцев: ");
        if (k == 1) {
            System.out.println(listArray.averageDeliveryTimeFor6Month());
        } else {
            System.out.println(listLinked.averageDeliveryTimeFor6Month());
        }

        System.out.println();
        System.out.println("День (дни) с максимальным количеством заказов за последний месяц: ");
        List<LocalDate> listAnswer = k == 1 ?
                listArray.listOfDaysWithMaxCountOfOrders(k) :
                listLinked.listOfDaysWithMaxCountOfOrders(k);

        for (LocalDate answer : listAnswer) {
            System.out.println(answer);
        }

        System.out.println();
        System.out.println("Общий вес доставленных товаров: ");
        if (k == 1) {
            System.out.println(listArray.totalWeightOfGoods() + " г");
        } else {
            System.out.println(listLinked.totalWeightOfGoods() + " г");
        }
    }
}