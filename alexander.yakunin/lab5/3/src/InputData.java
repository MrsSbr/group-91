import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class InputData {
    private static final int COUNT_OF_STUDENTS = 10000;
    public static final int countOfShows = 10;
    public static boolean isArrayList = true;

    private static int getCountOfSelectedShows() {
        return HelpFuncs.getRandomNumber(countOfShows - 1) + 1;
    }

    private static List<Integer> getListOfStudentTickets() {
        int countOfSelectedShows = getCountOfSelectedShows();
        List<Integer> showTickets = isArrayList ? new ArrayList<>() : new LinkedList<>();

        IntStream
                .range(0, countOfSelectedShows)
                .mapToObj(i -> HelpFuncs.getRandomNumber(countOfShows))
                .forEach(showTickets::add);

        return showTickets;
    }

    public static List<Integer> getInputData() {
        List<Integer> data = isArrayList ? new ArrayList<>() : new LinkedList<>();

        IntStream
                .range(0, COUNT_OF_STUDENTS)
                .mapToObj(i -> getListOfStudentTickets())
                .forEach(data::addAll);

        return data;
    }
}
