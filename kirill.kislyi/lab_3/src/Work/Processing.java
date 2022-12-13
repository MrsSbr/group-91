package Work;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import Classes.Month;
import Classes.MonthsList;

public class Processing {

    //Найти месяцы, когда температура была ниже указанной пользователем
    public static List<Month>  getLowerTemp(List<Month> monthNotes, double lowRange) {
        List<Month> result = new ArrayList<Month>();

        for (Month m : monthNotes) {
            if (m.getAVG() < lowRange) {
                result.add(m);
            }
        }
        return result;
    }

    //Найти самый жаркий месяц (месяцы)
    public static List<Month> getHighestTemp(List<Month> monthNotes) {

        double highest = -100;
        for (Month m : monthNotes) {
            if (m.getAVG() > highest) {
                highest = m.getAVG();
            }
        }

        List<Month> result = new ArrayList<Month>();

        for (Month m : monthNotes) {
            if (m.getAVG() == highest) {
                result.add(m);
            }
        }
        return result;
    }

    //Посчитать среднюю температуру за введенный пользователем год
    public static double getAverageTemperature(List<Month> monthNotes, int year) {
        double sum = 0;
        for (int i = year * 12; i < (year + 1) * 12; i++) {
            sum +=  monthNotes.get(i).getAVG();
        }

        return sum / 12;
    }

    public void mainProcessing(boolean timeCheckState) {

        System.out.println("Введите год для поиска средней температуры");
        int year = Help.getIntInDiapason(0, 4);
        System.out.println("Введите температуру, для поиска месяцев, в которые она была ниже");
        double low = Help.getDouble();


        System.out.println("ArrayList: ");
        List<Month> al = new ArrayList<Month>();
        MonthsList mal = new MonthsList(timeCheckState, al);
        mal.proc(year, low);

        System.out.println();

        System.out.println("LinkedList: ");
        List<Month> ll = new LinkedList<Month>();
        MonthsList mll = new MonthsList(timeCheckState, ll);

        mll.proc(year, low);
    }
}
