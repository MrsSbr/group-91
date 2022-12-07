/*Бариста ведет записи о приготовленных напитках: название, дата и время приготовления.
Известно, что за все время его работы, накопилось 10 000 таких записей (подумайте, как можно смоделировать ввод
с помощью случайных чисел)
Необходимо вывести следующую информацию:
• Список напитков, которые заказывают по утрам с 7 до 9
• Узнать, какие напитки не заказывали за последние 3 месяца
• Узнать, сколько раз бариста готовил капучино
В задаче должны использоваться коллекции (за исключением Map),
необходимо сравнить производительность различных коллекций для этой задачи, выбрать наиболее подходящую
(подходящие), аргументировать свой выбор*/

import classes.ListInformation;

import java.util.*;

public class CoffeeShop {

    private static final int SIZE = 10000;

    public static void main(String[] args) {

        ListInformation list = new ListInformation(new ArrayList<>(SIZE));

        list.fillingInTheList();

        System.out.println();
        System.out.println("Cписок напитков, которые заказывают по утрам с 7 до 9");

        List<String> listAnswer1 = list.listOfDrinksOrderedFrom7To9Morning();

        for (String answer : listAnswer1) {
            System.out.println(answer);
        }

        System.out.println();
        System.out.println("Список напитков, которые не заказывали последние 3 месяца");
        List<String> listAnswer2 = list.listOfDrinksThatHaveNotBeenOrderedFor3Months();

        if (listAnswer2.size() != 0) {

            for (String answer : listAnswer2) {
                System.out.println(answer);
            }
        } else {
            System.out.println("Таких напитков нет");
        }

        System.out.println();
        System.out.println("Количество приготовленных капучино за все время");
        System.out.println(list.countOfPreparedCappuccinoForAllTime());
    }

}