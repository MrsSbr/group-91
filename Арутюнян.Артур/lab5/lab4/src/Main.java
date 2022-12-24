import order.OrderStorage;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String path = "D:/Artooorcheck/group-91/Арутюнян.Артур/lab5/lab4/data.txt";

    public static void main(final String[] args) {
        logger.log(Level.INFO, "started");

        OrderStorage orderStorage = new OrderStorage();
        orderStorage.read(path);

        System.out.println(orderStorage.getCouriersForEachRestaurants());
        System.out.println(orderStorage.getMaxPopularRestForLastYear());
        System.out.println(orderStorage.getMaxSumTimeOrders());

        logger.log(Level.OFF, "program_finished");
    }
}
