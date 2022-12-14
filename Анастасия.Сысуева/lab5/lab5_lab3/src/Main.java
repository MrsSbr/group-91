import classes.TransportModelList;
import classes.TransportStatistic;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

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
            List<Integer> inputList = new ArrayList<>();
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
                System.out.println("Array list:");
                startTime = System.currentTimeMillis();
                TransportModelList listArray = new TransportModelList(new ArrayList<>());
                listArray.createBus();
                listArray.busWorksInput(inputList);
                listArray.outWeekReport();
                timeSpeedLinkedList = System.currentTimeMillis() - startTime;

                System.out.println("Linked list:");
                startTime = System.currentTimeMillis();
                TransportModelList listLinked = new TransportModelList(new LinkedList<>());
                listLinked.createBus();
                listLinked.busWorksInput(inputList);
                listLinked.outWeekReport();
                timeSpeedArrayList = System.currentTimeMillis() - startTime;

                System.out.println("Скорость для LinkedList: " + timeSpeedLinkedList);
                System.out.println("Скорость для ArrayList: " + timeSpeedArrayList);
            }
        }
    }

    public static boolean inputCheck(final int minValue, final int maxValue, int inputValue) {
        return (minValue > inputValue || inputValue > maxValue);
    }

    public static Integer inputInt() {
        int temp;
        Scanner input = new Scanner(System.in);
        System.out.println("Введите выручку для маршута (от 0 до 15000)");
        while (!input.hasNextInt()) {
            System.out.println("Введено не int значение");
            input.next();
        }
        temp = input.nextInt();
        while (inputCheck(TransportStatistic.MIN_CONST, TransportStatistic.MAX_CONST, temp)) {
            System.out.println("Введите выручку для маршута (от 0 до 15000)");
            while (!input.hasNextInt()) {
                System.out.println("Введено не int значение");
                input.next();
                temp = input.nextInt();
            }
        }
        return temp;
    }

    public static List<Integer> inputConsole() {
        List<Integer> inputConsole = new ArrayList<>();
        Stream.iterate(0, i -> i + 1).limit(TransportStatistic.ROUTE_CONST * TransportStatistic.DAYS_CONST).
                collect(Collectors.toList()).forEach(t -> inputConsole.add(inputInt()));
        return inputConsole;
    }

    public static List<Integer> inputRandom() {
        return new Random().ints(TransportStatistic.DAYS_CONST * TransportStatistic.ROUTE_CONST,
                TransportStatistic.MIN_CONST, TransportStatistic.MAX_CONST + 1).boxed().collect(Collectors.toList());
    }
}
