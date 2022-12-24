        //В постоянных попытках найти ответ на главный вопрос жизни, вселенной и всего такого,
        //мыши регулярно строят суперкомпьютеры, и анализируют их работу, записывая результаты в файл в следующем формате:
        //название суперкомпьютера;время его работы;ответ
        //Найти все ответы, которые были выданы 3-мя и более суперкомпьютерами
        //Для каждого ответа найти суммарное время его вычисления
        //Вывести суперкомпьютеры в порядке возрастания их времени работы
        //в задаче запрещено использовать элементы функционального программирования


import Help.Helper;
import Models.Miceputers;

import java.util.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final String DATASET_PATH = "kirill.kislyi/lab_4/src/info/info.txt";
    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("[0]. Выход.");
        System.out.println("[1]. Все ответы, выданные 3-мя и более супермышепутерами.");
        System.out.println("[2]. Суммарное время вычисления каждого ответа.");
        System.out.println("[3]. Вывести супермышепутеры в порядке возрастания их времени работы.");
    }

    private static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }


    private static void Task1(Miceputers miceputers) {
        HashMap<String, Integer> mps = miceputers.getAnswersMore3();
        Object[] keys = mps.keySet().toArray();
        for (int i=0; i<mps.size(); i++) {
            if (mps.get(keys[i]) >= 3) {
                System.out.println(keys[i] + " : " + mps.get(keys[i]));
            }
        }
        mps.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    private static void Task2(Miceputers miceputers) {
        HashMap<String, Integer> mps = miceputers.getTimeForAnswers();
        printMap(mps);
    }

    private static void Task3(Miceputers miceputers) {
        HashMap<String, Integer> mps = miceputers.getTimeForMiceputers();
        mps.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
        logger.log(Level.INFO, "Start micegram");
        Miceputers miceputers = new Miceputers();
        miceputers.fileToMiceputers(DATASET_PATH);
        printMenu();

        int answer = Helper.getIntInDiapason(0, 3);
        switch (answer) {

            case 1 -> Task1(miceputers);
            case 2 -> Task2(miceputers);
            case 3 -> Task3(miceputers);
            case 0 -> {
                System.out.println("Завершение");
                return;
            }
        }



    }
}
