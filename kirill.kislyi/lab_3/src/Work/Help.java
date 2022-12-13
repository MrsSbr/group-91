package Work;

import java.util.Scanner;

public class Help {

    public static double getRandomTemp() {
        double temp = Math.random() * 60 - 30;
        return temp;
    }

    public static double getRandomTempInDiapason(double start, double end) {
        double temp = Math.random() * (end - start) + start;
        return temp;
    }

    public static int getInt() {
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                return Integer.parseInt(in.next());
            } catch (Exception var3) {
                var3.printStackTrace();
                System.out.println("Некорректный ввод. Повторите!");
            }
        }
    }

    public static int getIntInDiapason(int start, int end) {
        int result = 0;
        boolean exitFlag = false;

        while (true) {
            while (!exitFlag) {
                result = getInt();
                if (result >= start && result <= end) {
                    exitFlag = true;
                } else {
                    System.out.println("Число должно находится в диапазоне от " + start + " до " + end);
                }
            }
            return result;
        }
    }

    public static double getDouble() {
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                return Double.parseDouble(in.next());
            } catch (Exception var4) {
                var4.printStackTrace();
                System.out.println("Некорректный ввод. Повторите!");
            }
        }
    }


    public static double getPositiveDouble() {
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                double result = Double.parseDouble(in.next());
                if (result < 0.0) {
                    throw new IllegalStateException();
                }

                return result;
            } catch (Exception var4) {
                var4.printStackTrace();
                System.out.println("Некорректный ввод. Повторите!");
            }
        }
    }
}
