import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int lastRemaining(int n) {
        List<Integer> arr = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }
        while (arr.size() != 1) {
            int i = 0;
            arr.remove(i);

            while (arr.size() != 1 && i + 1 < arr.size()) {
                i++;
                arr.remove(i);
            }

            if (arr.size() != 1) {
                i = arr.size() - 1;
                arr.remove(i);
                while (arr.size() != 1 && i - 2 >= 0) {
                    i -= 2;
                    arr.remove(i);
                }
            }
        }
        return arr.get(0);
    }

    private static int getPositiveInt() {
        Scanner in = new Scanner(System.in);
        int result;
        try {
            result = in.nextInt();
            while (result <= 0) {
                System.out.println("Ошибка ввода! Введите положительное число!");
                result = in.nextInt();
            }
        }
        catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода! Введите положительное число!");
            result = getPositiveInt();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Введите целое число:");
        int num = getPositiveInt();
        System.out.println("Ответ: " + lastRemaining(num));
    }
}
