/*
  В метеорологическом центре хранятся данные о температурах за последние 5 лет.
  Для каждого дня известна максимальная и минимальная температура (подумайте, как можно смоделировать ввод
  с помощью случайных чисел)
  Необходимо вывести следующую информацию:
        • Посчитать среднюю температуру за введенный пользователем год
        • Найти самый жаркий месяц (месяцы)
        • Найти месяцы, когда температура была ниже указанной пользователем

  В задаче должны использоваться коллекции (за исключением Map),
  необходимо сравнить производительность различных коллекций для этой задачи, выбрать наиболее подходящую
  (подходящие), аргументировать свой выбор
*/
import Work.Processing;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Processing proc = new Processing();
        String choice = "";
        boolean isEnd = false;
        while (!isEnd) {
            System.out.println("\nВыберите пункт меню: ");
            System.out.println("[1] Вывести результаты");
            System.out.println("[2] Рассчитать время работы для каждой коллекции");
            System.out.println("[Что-то ещё] Выйти из программы");

            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    proc.mainProcessing(false);
                    break;
                case "2":
                    proc.mainProcessing(true);
                    break;
                default:
                    isEnd = true;
                    break;
            }
        }
    }
}