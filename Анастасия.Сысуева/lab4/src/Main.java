package src;

import src.models.Store;
import src.models.Box;
import reader.ReaderFile;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String loggerPath = "Анастасия.Сысуева/lab4/logs/logs.txt";

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("[0]. Выход.");
        System.out.println("[1]. Сколько раз каждое средство попадало в наборы.");
        System.out.println("[2]. Месяц, в который было продано самое больше количество наборов.");
        System.out.println("[3]. Для каждого средства вывести месяцы, когда оно появилось в наборах первый раз.");
    }

    private static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    private static void printInteger(Integer integer) {
        System.out.println(integer);
    }


    public static void mainMenu() throws IOException {
        FileHandler fh = new FileHandler(loggerPath);
        logger.addHandler(fh);

        Map<Integer, Box> beautyBoxes = ReaderFile.readFile();
        Store store = new Store(beautyBoxes);
        printMenu();
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()) {
            System.out.println("Введено не int значение");
            input.next();
        }
        int answer = input.nextInt();

        switch (answer) {

            case 1 -> printMap(store.getCountProductInBoxes());
            case 2 -> printInteger(store.getMonthWithBiggestSale());
            case 3 -> printMap(store.getFirstMonthForProducts());
            case 0 -> {
                System.out.println("Завершение");
                return;
            }
            default -> {
                logger.log(Level.SEVERE, "Ошибка ввода");
                mainMenu();
            }
        }
        mainMenu();
    }

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler(loggerPath);
            logger.addHandler(fh);
            logger.log(Level.INFO, "Начало работы");

            mainMenu();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом");
        }
        logger.log(Level.INFO, "Завершение работы");

    }
}
