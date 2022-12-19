import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        int n = readN();
        interval[] intervals = readInterval(n);
        interval[] notIntersectIntervals = new interval[n];
        int countNotIntresect = 0;

        int i;
        for(i = 0; i < n; ++i) {
            boolean isIntersect = false;
            int j = 0;

            while(!isIntersect & j < countNotIntresect) {
                if (notIntersectIntervals[j].isIntersects(intervals[i].getStart(), intervals[i].getEnd())) {
                    isIntersect = true;
                } else {
                    ++j;
                }
            }

            if (isIntersect) {
                if (intervals[i].getStart() < notIntersectIntervals[j].getStart()) {
                    notIntersectIntervals[j].setStart(intervals[i].getStart());
                }

                if (intervals[i].getEnd() > notIntersectIntervals[j].getEnd()) {
                    notIntersectIntervals[j].setEnd(intervals[i].getEnd());
                }
            } else {
                notIntersectIntervals[j] = intervals[i];
                ++countNotIntresect;
            }
        }

        for(i = 0; i < countNotIntresect; ++i) {
            if (notIntersectIntervals[i] != null) {
                PrintStream var10000 = System.out;
                double var10001 = notIntersectIntervals[i].getStart();
                var10000.println(" " + var10001 + " " + notIntersectIntervals[i].getEnd());
            }
        }

    }

    private static int readN() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество интервалов ");

        int num;
        try {
            num = in.nextInt();
        } catch (InputMismatchException var3) {
            var3.printStackTrace();
            System.out.print("error on the element\n");
            num = readN();
        }

        return num;
    }

    private static interval[] readInterval(int n) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите интервалы(n*2 чисел через пробел) ");
        String str = in.nextLine();
        String[] strArr = str.split(" ");
        interval[] intervals = new interval[n];
        int j = 0;

        for(int i = 0; i < strArr.length; i += 2) {
            try {
                intervals[j] = new interval((double)Integer.parseInt(strArr[i]), (double)Integer.parseInt(strArr[i + 1]));
            } catch (NumberFormatException var8) {
                System.out.printf("error on the element, its value is equal to 0 %s\n", strArr[i]);
                var8.printStackTrace();
            }

            ++j;
        }

        return intervals;
    }
}