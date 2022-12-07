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

    public static void main(String[] args) {
        System.out.println("Введите целое число:");
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.next());
        System.out.println("Ответ: " + lastRemaining(num));
    }
}
