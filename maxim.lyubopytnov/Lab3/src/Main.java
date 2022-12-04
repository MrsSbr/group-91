import models.Processing;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Processing proc = new Processing();
        String choice = "";
        boolean isEnd = false;
        while (!isEnd) {
            System.out.println("\nВыберите пункт меню: ");
            System.out.println("1. Выполнение с выводом");
            System.out.println("2. Оценить производительность");
            System.out.println("Выход (Любой иной символ) ");

            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    proc.mainProcessing(false);
                    break;
                case "2":
                    proc.mainProcessing(true);
                    break;
                default:
                    isEnd = true;
                    break;
            }
        }
    }
}