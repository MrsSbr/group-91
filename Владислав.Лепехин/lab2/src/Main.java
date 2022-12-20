import service.Cinema;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int choise = -1;
        String input = "";
        Cinema cinema = new Cinema();

        while (!"0".equals(input)) {
            System.out.println("1. Вывести все фильмы");
            System.out.println("2. Добавить комедию");
            System.out.println("3. Добавить ужасы");
            System.out.println("4. Показать комедию");
            System.out.println("5. Показать ужасы\n");
            input = scan.next();

            try {
                choise = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
                e.printStackTrace();
            }

            switch (choise) {
                case 1 -> System.out.println(cinema.getMovies().toString());
                case 2 -> cinema.addComedy();
                case 3 -> cinema.addScary();
                case 4 -> cinema.showComedy();
                case 5 -> cinema.showScary();
            }
        }

        System.out.println("Конец работы...");

    }

}