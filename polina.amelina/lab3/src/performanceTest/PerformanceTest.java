package performanceTest;

import simulation.IntegerCollectionSupplier;
import simulation.Testable;

public final class PerformanceTest {
    public static final int BENCHMARK_SIZE = 1000000;

    private static long benchmark(Runnable whatToTest) {

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < BENCHMARK_SIZE; i++) {
            whatToTest.run();
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static IntegerCollectionSupplier chooseCollection(Testable testable) {

        System.out.printf("--------%n%s: тестируем каждую коллекцию %s раз%n", testable.getClass(), BENCHMARK_SIZE);
        IntegerCollectionSupplier[] collectionSuppliers = testable.getAllowedCollections();
        PerformanceHolder[] performanceHolders = new PerformanceHolder[collectionSuppliers.length];

        for (int i = 0; i < collectionSuppliers.length; i++) {

            IntegerCollectionSupplier collectionSupplier = collectionSuppliers[i];
            long performance = benchmark(() -> testable.test(collectionSupplier));
            System.out.printf("%s: %s ms%n", collectionSupplier.get().getClass(), performance);
            performanceHolders[i] = new PerformanceHolder(performance, collectionSupplier);
        }

        PerformanceHolder fastest = performanceHolders[0];

        for (PerformanceHolder performanceHolder : performanceHolders) {
            if (performanceHolder.performance() < fastest.performance()) {
                fastest = performanceHolder;
            }
        }

        System.out.printf("Выбираем %s%n", fastest.collectionSupplier().get().getClass());
        return fastest.collectionSupplier();
    }
}