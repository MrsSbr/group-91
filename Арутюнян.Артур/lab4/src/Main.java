import order.OrderStorage;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(final String[] args) {
        logger.log(Level.INFO, "started");

        Scanner in = new Scanner(System.in);
        System.out.println("Input file name");
        String path = in.next();

        OrderStorage orderStorage = new OrderStorage();
        orderStorage.read(path);

        System.out.println(orderStorage.getCouriersForEachRestaurants());
        System.out.println(orderStorage.getMaxPopularRestForLastYear());
        System.out.println(orderStorage.getMaxSumTimeOrders());

        logger.log(Level.OFF, "program_finished");
    }
}
