import Classes.ListOrder;

import java.util.*;

public class Main {
    private static final int SIZE = 12712;
    public static void main(String[] args) {
        ListOrder listArray=new ListOrder(new ArrayList<>(SIZE));
        listArray.fillingInTheList();

        System.out.println("Среднее время доставки за последние 6 месяцев: ");
        System.out.println(listArray.averageDeliveryTimeFor6Month());

        System.out.println();

        System.out.println("День (дни) с максимальным количеством заказов за последний месяц: ");
        System.out.println(listArray.listOfDaysWithMaxCountOfOrders());

        System.out.println();

        System.out.println("Общий вес доставленных товаров: ");
        System.out.println(listArray.totalWeightOfGoods());
    }
}