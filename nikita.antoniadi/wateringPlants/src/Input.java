import java.util.Scanner;

public class Input {

    public static int getCapacity() {
        System.out.println("Введите объем лейки");
        return getInt();
    }

    public static int getInt() {
        int num;
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();
        return num;
    }

    public static int getPlantsAmount() {
        System.out.println("Введите количество цветов");
        return getInt();
    }

    public static int[] getWaterNeeded(int amount, int capacity) {
        int[] plants = new int[amount];
        int i = 0;
        while (i < amount) {
            System.out.println("Введите количество воды для полива " + (i + 1) + "-го цветка");
            plants[i] = getInt();
            if (plants[i] <= capacity) {
                i++;
            } else {
                System.out.println("В лейке нет столько воды!");
            }
        }
        return plants;
    }
}
