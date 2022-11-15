package Classes;

import java.util.Scanner;

public class InputHelper {
    public static int getCorrectInt() {
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                return Integer.parseInt(in.next());
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public static int getCorrectIntInRange(int bottomNum, int topNum) {
        int result = 0;
        boolean isExit = false;

        while (true) {
            while (!isExit) {
                result = getCorrectInt();
                if (result >= bottomNum && result <= topNum) {
                    isExit = true;
                } else {
                    System.out.println("Invalid input, number must be between " + bottomNum + " and " + topNum);
                }
            }
            return result;
        }
    }
}
