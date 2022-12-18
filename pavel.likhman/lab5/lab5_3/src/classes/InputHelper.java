package classes;

import java.util.Scanner;

public class InputHelper {
    public static int getCorrectInt() {
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

    public static int getCorrectIntInRange(int bottomNum, int topNum) {
        int result;

        while (true) {
            result = getCorrectInt();
            if (result >= bottomNum && result <= topNum) {
                return result;
            } else {
                System.out.println("Invalid input, number must be between " + bottomNum + " and " + topNum);
            }
        }
    }
}
