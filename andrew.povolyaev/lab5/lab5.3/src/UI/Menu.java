package UI;


import Repository.Getters.ReaderInteger;
import Repository.Getters.GetterList;
import Repository.Getters.GeneratorList;
import Repository.Getters.ReaderList;
import Service.AnswersHandler;

import java.util.List;
import java.util.Map;

public class Menu {

    public static void printMainMenu() {
        System.out.println("Results");
        System.out.println("""
                1. Console filling
                2. Random generation
                0. Exit""");
    }

    private static void printImplMenu() {
        System.out.println("""
                1. ArrayList
                2. LinkedList
                0. Exit""");
    }

    private static void printHandlerMenu() {
        System.out.println("""
                Information about:
                1. Best player(s)
                2. Player(s) with one MVP
                3. Player(s) with away MVPs
                4. Working time
                0. Exit""");
    }

    private static void handlerMenu(Map<Integer, Integer> results) {
        AnswersHandler handler = new AnswersHandler(results);
        printHandlerMenu();
        int choice = ReaderInteger.nextInt();

        switch (choice) {
            case 0 -> {
                System.out.println("Exit");
                return;
            }
            case 1 -> Printer.printResults(handler.getBestPlayers());
            case 2 -> Printer.printResults(handler.getOneTimeMVPPlayers());
            case 3 -> Printer.printResults(handler.getAwayMVPPlayers());
            case 4 -> System.out.println("1000 iterations are made in " + handler.getWorkTime() + " ms");
            default -> System.out.println("Error");
        }
        handlerMenu(results);
    }

    public static void mainMenu() {
        GetterList getterList = null;
        printMainMenu();
        int choice = ReaderInteger.nextInt();

        switch (choice) {
            case 1 -> getterList = new ReaderList();
            case 2 -> getterList = new GeneratorList();
            case 0 -> {
                System.out.println("exit");
                return;
            }
            default -> {
                System.out.println("error");
                mainMenu();
            }
        }

        getterList.fillList();
        handlerMenu(getterList.getResults());
    }
}
