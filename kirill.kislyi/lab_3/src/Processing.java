import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Processing {

    //Найти месяцы, когда температура была ниже указанной пользователем
    public static String getLowerTemp(List<Month> monthNotes, double lowRange) {
        String result = " ";
        for (int i = 0; i < 60; i++) {
            if (monthNotes.get(i).getAVG() < lowRange) {
                int y = i / 12;
                result += monthNotes.get(i).name + " " + y + " года; ";
            }
        }

        if (result == " ") {
            result = " Не было таких месяцев! ";
        }
        return result;
    }

    //Найти самый жаркий месяц (месяцы)
    public static String getHighestTemp(List<Month> monthNotes) {
        double[] temps = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (Month m : monthNotes) {
            temps[m.number - 1] += m.getAVG();
        }

        double highest = -100;
        for (int i =0; i< 12; i++) {
            if (temps[i] > highest) {
                highest = temps[i];
            }
        }

        String result = " ";

        for (int i =0; i< 12; i++) {
            if (temps[i] == highest) {
                Month ex = new Month(i);
                result += ex.name + " ";
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
        MonthsArrayList mal = new MonthsArrayList(timeCheckState);
        mal.Proc(year, low);

        System.out.println();

        System.out.println("LinkedList: ");
        MonthsLinkedList mll = new MonthsLinkedList(timeCheckState);
        mll.Proc(year, low);
    }
}
