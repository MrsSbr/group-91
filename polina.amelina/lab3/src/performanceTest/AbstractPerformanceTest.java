package performanceTest;

public abstract class AbstractPerformanceTest {
    public static final int BENCHMARK_SIZE = 1000000;

    protected static long test(Runnable whatToTest) {

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < BENCHMARK_SIZE; i++) {
            whatToTest.run();
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}