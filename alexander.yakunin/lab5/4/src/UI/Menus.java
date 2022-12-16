package UI;

import java.util.Scanner;

public class Menus {
    public static int todos() {
        Scanner in = new Scanner(System.in);

        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Вывести среднее значение добытого мяса для каждого охотника");
        System.out.println("2. Имя охотника, который убил больше всего мамонтов");
        System.out.println("3. Месяц, в который добыли больше всего мяса");
        System.out.println("4. Выход");

        return in.nextInt();
    }


}
