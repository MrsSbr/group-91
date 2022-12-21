package classes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListOlympiads {

    public static final int SIZE = 10000;

    List<Olympiad> listOlympiads;

    public ListOlympiads(List<Olympiad> listOlympiad) {

        this.listOlympiads = listOlympiad;
    }

    public void fillingInTheList() {
        Stream.iterate(0, i -> i + 1).limit(SIZE).collect(Collectors.toList()).forEach(olympiad ->
                listOlympiads.add(new Olympiad()));
    }

    public Olympiad getListOlympiadByIndex(int i) {
        return listOlympiads.get(i);
    }
}
