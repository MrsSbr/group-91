import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

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
        for (int i = 0; i < size; ) {
            int current = getIntInRange(0, 106);
            if (i > 0 && current == arr[i - 1]) {
                System.out.println("Число равны");
            } else {
                arr[i] = current;
                i++;
            }
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int isPossible(int[] arr, int size) {
        int i = 0;
        for (; i < size - 1 && arr[i + 1] > arr[i]; i++) {

        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println("Введите размер массива");
        int size = getIntInRange(3, 105);
        int[] arr = getIntArr(size);

        printArr(arr);
        int isPossible = isPossible(arr, size);
        System.out.println(isPossible);
    }
}
