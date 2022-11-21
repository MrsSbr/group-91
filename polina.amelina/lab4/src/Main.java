import OrderProcessing.Order;
import OrderProcessing.OrderReader;
import Tasks.Tasks;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) {

        try {
            List<Order> orders = new OrderReader().readOrders("polina.amelina/lab4/orders.txt");
            Tasks tasks = new Tasks(orders);

            String task1 = tasks.getAveragePrepTimeByDrink();
            String task2 = tasks.getBusiestWeekdayHours();
            String task3 = tasks.getMostPopularDrinks();
            String task4 = tasks.getOptimalDrink();

            System.out.println(task1 + task2 + task3 + task4);

        } catch (Exception e) {
            logger.severe(e.toString());
        }
    }
}