import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input count of commands");
        int result = in.nextInt();
        in.close();
        System.out.println(Palindrom.checkPalindrom(result));
    }
}
