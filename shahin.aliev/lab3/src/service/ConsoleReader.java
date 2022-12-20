package service;

import java.util.Scanner;

public class ConsoleReader {

    public static int getInt() {
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                return Integer.parseInt(in.next());
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public static int getIntInRange(int left, int right) {
        int result;

        while (true) {
            result = getInt();
            if (result >= left && result <= right) {
                return result;
            } else {
                System.out.println("Invalid input, number must be between " + left + " and " + right);
            }
        }
    }
}
