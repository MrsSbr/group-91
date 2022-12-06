import orderProcessing.Order;
import orderProcessing.OrderReader;
import orderProcessing.OrderReadingException;
import tasks.TasksFormatter;
import tasks.TasksService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getGlobal();
    private static final String FILE_PATH = "polina.amelina/lab4/orders.txt";

    public static void main(String[] args) {

        List<Order> orders;

        try {
            orders = new OrderReader().readOrders(FILE_PATH);
        } catch (OrderReadingException | FileNotFoundException e) {
            logger.severe(e.toString());
            return;
        }

        TasksService tasksService = new TasksService(orders);
        TasksFormatter tasksFormatter = new TasksFormatter();

        String task1 = tasksFormatter.getAvgPrepTimeByDrinkFormattedResult(tasksService.getAvgPrepTimeByDrink());
        String task2 = tasksFormatter.getBusiestWeekdayHoursFormattedResult(tasksService.getBusiestWeekdayHours());
        String task3 = tasksFormatter.getMostPopularDrinksFormattedResult(tasksService.getMostPopularDrinks());
        String task4 = tasksFormatter.getOptimalDrinkFormattedResult(tasksService.getOptimalDrink());

        for (String task : new String[] {task1, task2, task3, task4}) {
            System.out.println(task);
        }
    }
}