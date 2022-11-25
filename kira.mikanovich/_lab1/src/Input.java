import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static int getInt() {
        boolean isCorrect = false;
        int intNumber = 0;
        Scanner scanner = new Scanner(System.in);
        while (!isCorrect) {
            try {
                intNumber = scanner.nextInt();
                isCorrect = true;
            } catch (InputMismatchException ime) {
                System.out.println("Вводимое значение должно быть целым числом");
                ime.printStackTrace();
                scanner.nextLine();
            }
        }
        return intNumber;
    }

    public static int getIntInRange(int minValue, int maxValue) {
        boolean isCorrect = false;
        int intNumber = 0;
        while (!isCorrect) {
            System.out.println("Введите число в диапазоне от " + minValue + " до " + maxValue);
            intNumber = getInt();
            isCorrect = intNumber >= minValue && intNumber <= maxValue;
            if (!isCorrect) {
                System.out.println("Число вне диапазона");
            }
        }
        return intNumber;
    }

    public static int[] getIntArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = getIntInRange(0, 106);
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}