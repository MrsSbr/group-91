package lab3.src.checkValidations;

import java.util.Scanner;

public class InputValidations {

    private static boolean verifyName(String name) {
        name = name.trim();

        if (name.equals("")) {
            return false;
        }

        return name.matches("[a-zA-Z-']*");
    }

    public static String checkName() {
        Scanner in = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter name: ");
            name = in.nextLine();
        } while (!verifyName(name));

        return name;
    }

    public static int getNumberInRange(int left, int right) {
        Scanner in = new Scanner(System.in);
        int grade = 0;
        do {
            System.out.print("Enter number: ");
            try {
                grade = Integer.parseInt(in.next());
            } catch (NumberFormatException e) {
                System.out.println("Input ERROR!");
                e.printStackTrace();
            }

        } while (grade > right || grade < left);

        return grade;
    }
}
