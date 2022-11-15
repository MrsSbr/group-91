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
            System.out.println("[1] Выполнение с выводом");
            System.out.println("[2] Оценить производительность");
            System.out.println("[Любой иной символ] Выход");

            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    proc.mainProcessing(new ArrayList<>(), false);
                    break;
                case "2":
                    System.out.println("LinkedList: ");
                    proc.mainProcessing(new LinkedList<>(), true);
                    System.out.println("ArrayList: ");
                    proc.mainProcessing(new ArrayList<>(), true);
                    break;
                default:
                    isEnd = true;
                    break;
            }
        }
    }

}
