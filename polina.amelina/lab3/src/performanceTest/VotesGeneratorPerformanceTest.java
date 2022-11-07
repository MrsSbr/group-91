package performanceTest;

import simulation.VotesGenerator;

import java.util.*;

public final class VotesGeneratorPerformanceTest extends AbstractPerformanceTest {

    private VotesGeneratorPerformanceTest() {}

    public static Collection<Integer> chooseCollection(VotesGenerator votesGenerator) {

        System.out.printf("--------%n%s: тестируем каждую коллекцию %s раз%n", VotesGenerator.class, BENCHMARK_SIZE);

        long arrayListPerformance = test(() -> votesGenerator.generateToCollection(new ArrayList<>()));
        System.out.printf("%s: %s ms%n", ArrayList.class, arrayListPerformance);

        long linkedListPerformance = test(() -> votesGenerator.generateToCollection(new LinkedList<>()));
        System.out.printf("%s: %s ms%n", LinkedList.class, linkedListPerformance);

        long priorityQueuePerformance = test(() -> votesGenerator.generateToCollection(new PriorityQueue<>()));
        System.out.printf("%s: %s ms%n", PriorityQueue.class, priorityQueuePerformance);

        long arrayDequePerformance = test(() -> votesGenerator.generateToCollection(new ArrayDeque<>()));
        System.out.printf("%s: %s ms%n", ArrayDeque.class, arrayDequePerformance);

        Collection<Integer> collection = arrayListPerformance < linkedListPerformance ?
                arrayListPerformance < priorityQueuePerformance ?
                        arrayListPerformance < arrayDequePerformance ?
                                new ArrayList<>() :
                                new ArrayDeque<>() :
                        priorityQueuePerformance < arrayDequePerformance ?
                                new PriorityQueue<>() :
                                new ArrayDeque<>() :
                linkedListPerformance < priorityQueuePerformance ?
                        linkedListPerformance < arrayDequePerformance ?
                                new LinkedList<>() :
                                new ArrayDeque<>() :
                        priorityQueuePerformance < arrayDequePerformance ?
                                new PriorityQueue<>() :
                                new ArrayDeque<>();

        System.out.printf("Выбираем %s%n", collection.getClass());
        return collection;
    }
}