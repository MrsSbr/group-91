import OrderProcessing.Order;
import OrderProcessing.OrderReader;
import Task.Tasks;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Order> orders = new OrderReader().readOrders("text.txt");
            Tasks tasks = new Tasks(orders);

            System.out.print(tasks.getAveragePreparationTimeByBeverage());
            System.out.print(tasks.getBusiestWeekdayHours());
            System.out.print(tasks.getMostPopularDrinks());
            System.out.print(tasks.getOptimalDrink());

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}