import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InputData {
    static private final int COUNT_OF_STUDENTS = 10000; // порядок private static final
    static public final int countOfShows = 10; // в константу
    static public boolean isArrayList = true;

    static private int getCountOfSelectedShows() {
        return HelpFuncs.getRandomNumber(countOfShows - 1) + 1;
    }

    static private List<Integer> getListOfStudentTickets() {
        int countOfSelectedShows = getCountOfSelectedShows();
        List<Integer> showTickets = isArrayList ? new ArrayList<>() : new LinkedList<>();

        for (int i = 0; i < countOfSelectedShows; ++i) {
            showTickets.add(HelpFuncs.getRandomNumber(countOfShows));
        }

        return showTickets;
    }

    static public List<Integer> getInputData() {
        List<Integer> data = isArrayList ? new ArrayList<>() : new LinkedList<>();

        for (int i = 0; i < COUNT_OF_STUDENTS; ++i) {
            data.addAll(getListOfStudentTickets());
        }
        return data;
    }
}
