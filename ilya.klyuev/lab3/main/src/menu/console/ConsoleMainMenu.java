package menu.console;

import controllers.PerformancesEmployeesController;
import input.console.ConsoleInput;
import menu.MainMenu;
import models.Performance;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleMainMenu implements MainMenu {
    private static final int MIN_MENU_ITEM = 1;
    private static final int END_MENU_ITEM = 6;
    private final PerformancesEmployeesController performancesEmployeesController;
    private final Logger logger;

    public ConsoleMainMenu(PerformancesEmployeesController performancesEmployeesController, Logger logger) {
        this.performancesEmployeesController = performancesEmployeesController;
        this.logger = logger;
    }

    @Override
    public void show() {
        int menuItem = 0;
        ConsoleInput consoleInput = new ConsoleInput(logger);

        while (menuItem != END_MENU_ITEM) {
            showMenu();
            menuItem = consoleInput.inputIntInRange("Выберите пункт", MIN_MENU_ITEM, END_MENU_ITEM);

            switch (menuItem) {
                case 1 -> performancesEmployeesController.addEmployeesTicketsFromInputStream();
                case 2 -> performancesEmployeesController.addEmployeesTicketsByRandom();
                case 3 -> showPerformances();
                case 4 -> showMaxPopularPerformances();
                case 5 -> showPerformancesNotTickets();
            }
            waitUser();
        }
    }

    private void showMenu() {
        System.out.println("1: Добавить выбранные билеты работников с консоли");
        System.out.println("2: Добавить выбранные билеты работников рандомно");
        System.out.println("3: Показать количество билетов на каждом спектале");
        System.out.println("4: Показать самые популярные спектакли");
        System.out.println("5: Показать спектали, на которые никто не купил билет");
        System.out.println("6: Выход");
    }

    private void showPerformances() {
        System.out.println("Спектакли:");
        for (var performance : Performance.values()) {
            System.out.println(performance);
        }
    }

    private void showMaxPopularPerformances() {
        System.out.println("Самые популярые спектакли:");
        for (var performance : performancesEmployeesController.getMaxPopularPerformances()) {
            System.out.println(performance);
        }
    }

    private void showPerformancesNotTickets() {
        var performancesNotTickets = performancesEmployeesController.getPerformancesNotTickets();

        if (performancesNotTickets.isEmpty()) {
            System.out.println("Нет спектаклей, на которые не купили блеты");
            return;
        }

        System.out.println("Спектали, на которые не были куплены билеты");
        for (var performance : performancesNotTickets) {
            System.out.println(performance);
        }
    }

    private void waitUser() {
        System.out.println("Введите <ENTER>");
        try {
            System.in.read();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "wait press enter", e);
        }
    }
}
