package input.console;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleInput {
    private final Logger logger;

    public ConsoleInput(Logger logger) {
        this.logger = logger;
    }

    public int inputIntInRange(String message, int leftBound, int rightBound) {
        if (leftBound > rightBound) {
            logger.log(Level.SEVERE, "inputInRange левая граница больше правой");
            return leftBound - 1;
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
                    logger.log(Level.WARNING, "inputIntInRange число не входит в заданный диапазон");
                }

            } catch (Exception e) {
                scanner.nextLine();
                logger.log(Level.SEVERE, "Ошибка ввода", e);
            }
        }

        return number;
    }
}
