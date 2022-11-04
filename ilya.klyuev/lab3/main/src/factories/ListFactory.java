package factories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListFactory {
    private ListFactory() {

    }

    private static ListChoice selectedList = ListChoice.ARRAYLIST;

    public static <T> List<T> createList() {
        if (selectedList == ListChoice.LINKEDLIST) {
            return new LinkedList<>();
        }
        return new ArrayList<>();
    }

    public static void selectList(ListChoice listChoice) {
        selectedList = listChoice;
    }
}
