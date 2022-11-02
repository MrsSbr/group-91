import classes.TransportModelArrayList;
import classes.TransportModelLinkedList;
import classes.TransportStatistic;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TransportModelLinkedList linkedList = new TransportModelLinkedList();
        TransportModelArrayList arrayList = new TransportModelArrayList();
        List<Integer> inputList = new ArrayList<>();

        long startTime;
        long timeSpeedLinkedList;
        long timeSpeedArrayList;
        boolean exit = false;
        int answer;


        while (!exit) {
            System.out.println("Выберите способ введения данных:");
            System.out.println("1. Ввесть данные с консоли.");
            System.out.println("2. Ввести рандомные данные.");
            System.out.println("Для выхода введите любое другое число.");

            Scanner input = new Scanner(System.in);
            while (!input.hasNextInt()) {
                System.out.println("Введено не int значение");
                input.next();
            }
            answer = input.nextInt();
            switch (answer) {
                case 1:
                    inputList = inputConsole();
                    break;
                case 2:
                    inputList = inputRandom();
                    break;
                default:
                    exit = true;
                    break;
            }
            if (!exit) {
                startTime = System.currentTimeMillis();
                linkedList.createBus();
                linkedList.busWorksInput(inputList);
                linkedList.outWeekReport();
                timeSpeedLinkedList = System.currentTimeMillis() - startTime;

                startTime = System.currentTimeMillis();
                arrayList.createBus();
                arrayList.busWorksInput(inputList);
                arrayList.outWeekReport();
                timeSpeedArrayList = System.currentTimeMillis() - startTime;

                System.out.println("Скорость для LinkedList: " + timeSpeedLinkedList);
                System.out.println("Скорость для ArrayList: " + timeSpeedArrayList);
            }
        }
    }

    public static boolean inputCheck(final int minValue, final int maxValue, int inputValue) {
        return (minValue > inputValue || inputValue > maxValue);
    }

    public static List<Integer> inputConsole() {
        List<Integer> consoleList = new ArrayList<>();
        int temp;
        for (int i = 0; i < TransportStatistic.ROUTE_CONST; i++) {
            for (int j = 0; j < TransportStatistic.DAYS_CONST; j++) {
                Scanner input = new Scanner(System.in);
                System.out.println("Введите выручку для " + (i + 1) + "-го маршута "
                        + "за " + (j + 1) + "-й день недели: (от 0 до 15000)");
                while (!input.hasNextInt()) {
                    System.out.println("Введено не int значение");
                    input.next();
                }
                temp = input.nextInt();
                while (inputCheck(TransportStatistic.MIN_CONST, TransportStatistic.MAX_CONST, temp)) {
                    System.out.println("Введите выручку для" + (i + 1) + "-го маршута "
                            + " за " + (j + 1) + "-й день недели: (от 0 до 15000)");
                    while (!input.hasNextInt()) {
                        System.out.println("Введено не int значение");
                        input.next();
                        temp = input.nextInt();
                    }
                }
                consoleList.add(temp);
            }
        }
        return consoleList;
    }

    public static List<Integer> inputRandom() {
        List<Integer> randomList = new ArrayList<>();
        int temp;
        for (int i = 0; i < TransportStatistic.ROUTE_CONST; i++) {
            for (int j = 0; j < TransportStatistic.DAYS_CONST; j++) {
                temp = ((int) ((Math.random()
                        * (TransportStatistic.MAX_CONST - TransportStatistic.MIN_CONST))
                        + TransportStatistic.MIN_CONST));
                randomList.add(temp);
            }
        }
        return randomList;
    }
}
