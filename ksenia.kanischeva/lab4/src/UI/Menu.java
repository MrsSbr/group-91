package UI;

import models.DepartmentEmployees;
import repository.ReaderFile;
import repository.ReaderInteger;
import service.DepartmentHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {
    private static final Logger logger = Logger.getLogger(Menu.class.getName());


    private static void printMenu() {
        System.out.println("""
                Вывод:
                1. Распечатать ведомости по отделам
                2. Найти отдел с самой высокой средней заработной платой
                3. Найти отдел с самой большой общей суммой выплаты
                0. Выход""");
    }

    private static void printStatement(String statement) {
        System.out.println(statement);
    }

    private static void printDeps(Set<Integer> deps) {
        System.out.println(deps);
    }

    public static void mainMenu() throws IOException {
        FileHandler fh = new FileHandler("ksenia.kanischeva/lab4/logs/logs.txt");
        logger.addHandler(fh);

        Map<Integer, DepartmentEmployees> departments = ReaderFile.readFile();
        printMenu();
        int item = ReaderInteger.nextInt();

        switch (item) {

            case 1 -> printStatement(DepartmentHandler.getStatement(departments));
            case 2 -> printDeps(DepartmentHandler.getDepartmentsWithHighAvg(departments));
            case 3 -> printDeps(DepartmentHandler.getDepartmentsWithHighSum(departments));
            case 0 -> {
                System.out.println("Завершение...");
                return;
            }
            default -> {
                logger.log(Level.SEVERE, "Ошибка ввода");
                System.out.println("Ошибка ввода");
                mainMenu();
            }
        }
        mainMenu();
    }


}
