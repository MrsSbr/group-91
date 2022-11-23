import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input number");
        try {
            int result = in.nextInt();
            System.out.println(Palindrom.checkPalindrom(result));
        } catch (Exception ex) {
            System.out.println("Is not number");
        }
        in.close();
    }
}
