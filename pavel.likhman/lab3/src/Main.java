import Classes.InputHelper;
import Classes.Task;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        boolean isEnd = false;
        Task task = new Task();
        while (!isEnd) {
            System.out.println("Select a menu item: ");
            System.out.println("1. Task");
            System.out.println("2. Compare performance");
            System.out.println("0. Exit");

            int answer = InputHelper.getCorrectIntInRange(0, 2);
            switch (answer) {
                case 1:
                    System.out.println("Input year: ");
                    int date = InputHelper.getCorrectInt();
                    task.task(new ArrayList<>(), date, false);
                    break;
                case 2:
                    System.out.println("LinkedList: ");
                    task.task(new LinkedList<>(), -12500, true);
                    System.out.println("ArrayList: ");
                    task.task(new ArrayList<>(), -12500, true);
                    break;
                case 0:
                    isEnd = true;
                default:
                    System.out.println("Invalid input, please try again");
                    break;
            }
        }
    }


}
