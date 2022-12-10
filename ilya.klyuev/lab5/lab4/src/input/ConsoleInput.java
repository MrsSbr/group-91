package input;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleInput {

    private static final Logger logger = Logger.getLogger(ConsoleInput.class.getName());

    private ConsoleInput() {

    }

    public static int inputIntInRange(String message, int leftBound, int rightBound) throws InvalidBoundsException {
        if (leftBound > rightBound) {
            throw new InvalidBoundsException("Left bound greater right bound");
        }

        System.out.println(message);

        int number = 0;
        boolean isCorrectInput = false;
        Scanner scanner = new Scanner(System.in);

        while (!isCorrectInput) {
            try {
                System.out.printf("Введите число от %d до %d\n", leftBound, rightBound);
                number = scanner.nextInt();
                if (number >= leftBound && number <= rightBound) {
                    isCorrectInput = true;
                } else {
                    logger.log(Level.WARNING, String.format("Number not in [%d; %d]", leftBound, rightBound));
                }
            } catch (Exception e) {
                scanner.nextLine();
                logger.log(Level.SEVERE, "Input exception", e);
            }
        }

        return number;
    }
}
