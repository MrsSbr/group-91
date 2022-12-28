package Repository.Getters;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ReaderInteger implements AutoCloseable {
    private final static Scanner in = new Scanner(System.in);

    public static int nextInt() throws InputMismatchException {
        return in.nextInt();
    }

    @Override
    public void close() {
        in.close();
    }
}
