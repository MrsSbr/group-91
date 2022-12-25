package Prints;

import java.util.Map;

public class Prints {
    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("[0]. Выход.");
        System.out.println("[1]. Все ответы, выданные 3-мя и более супермышепутерами.");
        System.out.println("[2]. Суммарное время вычисления каждого ответа.");
        System.out.println("[3]. Вывести супермышепутеры в порядке возрастания их времени работы.");
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
