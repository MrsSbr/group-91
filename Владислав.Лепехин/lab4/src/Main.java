import helper.Reader;
import loger.Messages;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        logger.log(Level.INFO, "Start working");
        Scanner scan = new Scanner(System.in);
        int choice = -1;
        String input = "";
        Messages messages = new Messages();
        Reader.readFile(messages);

        while (!"0".equals(input)) {

            System.out.println("1. Найти человека которому отправлено самое длинное сообщение");
            System.out.println("2. Найти в какой день недели слово встречалось чаще всего");
            System.out.println("3. Найти наименее употребимую эмоцию( на выбор ')','(' )");
            System.out.println("0. Exit\n");
            input = scan.next();

            try {

                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println("Incorrect input");
                logger.log(Level.WARNING, "Incorrect input " + Arrays.toString(e.getStackTrace()));

            }

            choice = switch (choice) {

                case 1 -> {

                    System.out.println(messages.mostLenghtMessage());
                    yield 1;

                }
                case 2 -> {
                    System.out.println("Введите слово ");
                    String word = scan.next();
                    System.out.println(messages.mostPopularDayForWord(word));
                    yield 2;

                }
                case 3 -> {

                    System.out.println(messages.worstPopularEmodgi());
                    yield 3;
                }
                default -> {

                    System.out.println("Error");
                    yield -1;

                }

            };

        }

        logger.log(Level.OFF, "End of working");

    }

}