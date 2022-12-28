package Work;

import Classes.Month;
import Classes.MonthsList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Processing {

    //Найти месяцы, когда температура была ниже указанной пользователем
    public static List<Month>  getLowerTemp(List<Month> monthNotes, double lowRange) {
        return monthNotes.stream()
                .filter(m -> m.getAVG() < lowRange)
                .collect(Collectors.toList());
    }

    //Найти самый жаркий месяц (месяцы)
    public static List<Month> getHighestTemp(List<Month> monthNotes) {

        double highest = monthNotes.stream()
                .mapToDouble(Month::getAVG)
                .filter(m -> m >= -100).max()
                .orElse(-100);

        return monthNotes
                .stream().filter(m -> m.getAVG() == highest)
                .collect(Collectors.toList());
    }

    //Посчитать среднюю температуру за введенный пользователем год
    public static double getAverageTemperature(List<Month> monthNotes, int year) {
        double sum = IntStream.range(year * 12, (year + 1) * 12)
                .mapToDouble(i -> monthNotes.get(i).getAVG()).sum();

        return sum / 12;
    }

    public void mainProcessing(boolean timeCheckState) {

        System.out.println("Введите год для поиска средней температуры");
        int year = Help.getIntInDiapason(0, 4);
        System.out.println("Введите температуру, для поиска месяцев, в которые она была ниже");
        double low = Help.getDouble();


        System.out.println("ArrayList: ");
        List<Month> al = new ArrayList<>();
        MonthsList mal = new MonthsList(timeCheckState, al);
        mal.proc(year, low);

        System.out.println();

        System.out.println("LinkedList: ");
        List<Month> ll = new LinkedList<>();
        MonthsList mll = new MonthsList(timeCheckState, ll);

        mll.proc(year, low);
    }
}
