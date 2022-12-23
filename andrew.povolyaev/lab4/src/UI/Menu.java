package UI;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.MoonshineKind;
import Repository.ReaderFile;
import Repository.ReaderInteger;
import Service.Handler;

import javax.print.attribute.standard.Severity;

public class Menu {
    private static final Logger logger = Logger.getLogger(Menu.class.getName());
    private static void printMenu() {
        System.out.println("""
                Info about:
                1. Moonshines
                2. Average time
                3. Longest list of ingredients
                4. Sum volume
                0. Exit""");
    }

    private static void printStatement(String statement) {
        System.out.println(statement);
    }

    private static void printResult(Set<String> result) {
        System.out.println(result);
    }

    private static void printRes(Set<Double> result){
        System.out.println(result);
    }

    public static void mainMenu() throws IOException{
        FileHandler fh = new FileHandler("andrew.povolyaev/lab4/Logs/logs.txt");
        logger.addHandler(fh);

        Map<String, MoonshineKind> moonshines = ReaderFile.readFile();
        Handler handler = new Handler(moonshines);
        printMenu();
        int choice = ReaderInteger.nextInt();
        switch (choice){
            case 1 -> printStatement(handler.getStatement());
            case 2 -> printRes(handler.getAverageDays());
            case 3 -> printResult(handler.getMaxMonth());
            case 4 -> printResult(handler.getSumVolume());
            case 0 -> {
                System.out.println("exit");
                return;
            }
            default -> {
                logger.log(Level.SEVERE, "input error");
                mainMenu();
            }
        }
    }
}
