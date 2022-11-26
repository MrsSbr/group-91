package UI;

import repository.getters.GeneratorList;
import repository.getters.GetterList;
import repository.getters.ReaderInteger;
import repository.getters.ReaderList;
import handler.AnswersHandler;

import java.util.List;
import java.util.Map;

public class Menu {

    private static void printMainMenu() {
        System.out.println("Опрос болельщиков");
        System.out.println("""
                1. Заполнение с консоли
                2. Генерация
                0. Выход""");
    }

    private static void printHandlerMenu() {
        System.out.println("""
                Вывод:
                1. Номер самого популярного игрока(-ов)
                2. Номер игрока(-ов), не получившего ни одного голоса
                3. Список игроков, за которых голосовали
                4. Время работы
                0. Выход""");
    }

    private static void handlerMenu(Map<Integer, Integer> answers) {
        AnswersHandler handler = new AnswersHandler(answers);
        printHandlerMenu();
        int item = ReaderInteger.nextInt();

        switch (item) {
            case 0 -> {
                System.out.println("Завершение...");
                return;
            }
            case 1 -> Printer.printResults(handler.getPopularPlayers());
            case 2 -> Printer.printResults(handler.getLoserPlayers());
            case 3 -> Printer.printResults(handler.getSelectedPlayers());
            case 4 -> System.out.println("Время выполнения 1000 итераций " + handler.getWorkTime() + " ms");
            default -> System.out.println("Ошибка ввода");
        }
        handlerMenu(answers);
    }

    public static void mainMenu() {
        GetterList getterList = null;
        printMainMenu();
        int item = ReaderInteger.nextInt();

        switch (item) {

            case 1 -> getterList = new ReaderList();
            case 2 -> getterList = new GeneratorList();
            case 0 -> {
                System.out.println("Завершение...");
                return;
            }
            default -> {
                System.out.println("Ошибка ввода");
                mainMenu();
            }
        }

        getterList.fillList();
        handlerMenu(getterList.getAnswers());

    }
}


