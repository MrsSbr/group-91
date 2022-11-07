package performanceTest;

import simulation.Election;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ElectionPerformanceTest extends AbstractPerformanceTest {

    private ElectionPerformanceTest() {}

    public static List<Integer> chooseCollection(Election election) {

        System.out.printf("--------%n%s: тестируем каждую коллекцию %s раз%n", Election.class, BENCHMARK_SIZE);

        long arrayListPerformance = test(() -> election.countVotes(new ArrayList<>()));
        System.out.printf("%s: %s ms%n", ArrayList.class, arrayListPerformance);

        long linkedListPerformance = test(() -> election.countVotes(new LinkedList<>()));
        System.out.printf("%s: %s ms%n", LinkedList.class, linkedListPerformance);

        List<Integer> list = arrayListPerformance < linkedListPerformance ?
                new ArrayList<>() :
                new LinkedList<>();
        System.out.printf("Выбираем %s%n", list.getClass());

        return list;
    }
}