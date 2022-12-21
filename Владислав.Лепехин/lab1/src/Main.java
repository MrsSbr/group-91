import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = readN();
        Interval[] intervals = readInterval(n);
        Interval[] notIntersectIntervals = new Interval[n];
        int countNotIntresect = 0;
        for (int i = 0; i < n; i++) {
            boolean isIntersect = false;
            int j = 0;
            while (!isIntersect & j < countNotIntresect) {
                if (notIntersectIntervals[j].isIntersects(intervals[i].getStart(), intervals[i].getEnd())) {
                    isIntersect = true;
                } else {
                    j++;
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
                countNotIntresect++;
            }
        }
        for (int i = 0; i < countNotIntresect; i++) {
            if (notIntersectIntervals[i] != null) {
                System.out.println(" " + notIntersectIntervals[i].getStart() + " " + notIntersectIntervals[i].getEnd());
            }
        }
    }

    private static int readN() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество интервалов ");
        int num;

        try {
            num = in.nextInt();
        } catch (final InputMismatchException e) {
            e.printStackTrace();
            System.out.print("error on the element\n");
            num = readN();
        }
        return num;
    }

    private static Interval[] readInterval(int n) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите интервалы(n*2 чисел через пробел) ");
        String str = in.nextLine();
        String[] strArr = str.split(" ");
        Interval[] intervals = new Interval[n];
        int j = 0;
        for (int i = 0; i < strArr.length; i += 2) {
            try {
                intervals[j] = new Interval(Integer.parseInt(strArr[i]), Integer.parseInt(strArr[i + 1]));
            } catch (final NumberFormatException e) {
                System.out.printf("error on the element, its value is equal to 0 %s\n", strArr[i]);
                e.printStackTrace();
            }
            j++;
        }
        return intervals;
    }
}