import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Какой контейнер хотите использовать?");
        System.out.println("0. Выход");
        System.out.println("1. ArrayList");
        System.out.println("2. LinkedList");

        int ans = in.nextInt();

        while (ans < 0 || ans > 2) {
            System.out.println("Ошибка ввода, попробуйте ещеё раз...");
            ans = in.nextInt();
        }

        if (ans == 0) {
            return;
        }
        InputData.isArrayList = ans == 1;

        List<Integer> tickets = InputData.getInputData();
        ShowsInformation infos = new ShowsInformation(tickets);

        do {
            System.out.println("Что вы хотите вывести?");
            System.out.println("0. Выход");
            System.out.println("1. Количество билетов, заказанных на каждый спектакль");
            System.out.println("2. Самый популярный спектакль");
            System.out.println("3. Спектакли, на которые решили приобрести билеты");
            System.out.println("4. Я хочу протестировать скорость работы");
            ans = in.nextInt();

            while (ans < 0 || ans > 4) {
                System.out.println("Ошибка ввода, попробуйте ещеё раз...");
                ans = in.nextInt();
            }

            switch (ans) {
                case 0:
                    return;
                case 1:
                    System.out.println(infos.getAllTicketsCount(true));
                    break;
                case 2:
                    System.out.println(infos.getPopularShows(true));
                    break;
                case 3:
                    System.out.println(infos.getPurchasedTickets(true));
                    break;
                default:
                    int countOfTest = 500;

                    for (int i = 0; i < 20; ++i) {
                        infos.testMethod(); //прогрев
                    }

                    long startTime = System.currentTimeMillis();
                    for (int i = 0; i < countOfTest; ++i) {
                        infos.testMethod();
                    }
                    long time = System.currentTimeMillis() - startTime;

                    System.out.println("Время выполнения " + countOfTest + " тестов: " + time + " мс");
                    System.out.println("Среднее время выполнения: " + ((double) (time) / countOfTest) + " мс");
                    break;
            }

            System.out.println("Вы хотите вывести еще информацию?");
            System.out.println("1. Да");
            System.out.println("2. Нет");
            ans = in.nextInt();
        } while (ans != 2);
    }
}
