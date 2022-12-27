package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ListOrder {
    public static final int SIZE = 12712;
    List<Order> orders;

    public ListOrder(List<Order> orders) {

        this.orders = orders;
    }

    public void fillingInTheList() {

        for (int i = 0; i < SIZE; i++) {
            Order order = new Order();
            order.fillingInformationOfOrder();
            orders.add(order);
        }
    }

    //среднее время доставки за последние 6 месяцев
    public LocalTime averageDeliveryTimeFor6Month() {
        int countOfOrders = 0;
        long sec = 0;
        LocalDate date = LocalDate.now().minusMonths(6);
        for (int i = 0; i < SIZE; i++) {
            LocalDate date1 = orders.get(i).getDateOfOrder();
            if (!date1.isBefore(date)) {
                countOfOrders++;
                sec += (orders.get(i).getTimeOfDelivery().getHour() * 3600 +
                        orders.get(i).getTimeOfDelivery().getMinute() * 60);
            }
        }
        long averageTime = sec / countOfOrders;
        return LocalTime.of(0, 0).plusSeconds(averageTime);

    }

    //день (дни) с максимальным количеством заказов в день за последний месяц
    public List<LocalDate> listOfDaysWithMaxCountOfOrders(int k) {
        List<LocalDate> answerList;
        if (k == 1) {
            answerList = new ArrayList<>();
        } else {
            answerList = new LinkedList<>();
        }
        boolean[] isChecked = new boolean[SIZE];
        for (int i = 0; i < SIZE; i++) {
            isChecked[i] = false;
        }
        int maxCountOfOrders = 0;
        LocalDate date = LocalDate.now().minusMonths(1);
        for (int i = 0; i < SIZE; i++) {
            int countOfOrders = 0;
            LocalDate date1 = orders.get(i).getDateOfOrder();
            if (!date1.isBefore(date) && !isChecked[i]) {
                countOfOrders++;
                if (i != SIZE - 1) {
                    for (int j = i + 1; j < SIZE; j++) {
                        if (orders.get(j).getDateOfOrder().equals(date1)) {
                            countOfOrders++;
                            isChecked[j] = true;
                        }
                    }
                }
            }
            if (countOfOrders > maxCountOfOrders) {
                maxCountOfOrders = countOfOrders;
                answerList.clear();
                answerList.add(date1);
            } else if (countOfOrders == maxCountOfOrders) {
                answerList.add(date1);
            }
        }
        return answerList;
    }

    //общий вес доставленных товаров
    public int totalWeightOfGoods() {
        int totalWeight = 0;
        for (int i = 0; i < SIZE; i++) {
            totalWeight += orders.get(i).getWeight();
        }
        return totalWeight;
    }
}
