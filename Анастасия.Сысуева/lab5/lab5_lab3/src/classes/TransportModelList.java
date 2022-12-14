package classes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TransportModelList {
    public static final int ROUTE_MIN_NUMBER_CONST = 1;
    public static final int ROUTE_MAX_NUMBER_CONST = 200;

    private final List<TransportStatistic> transportStatistics;

    public TransportModelList(List<TransportStatistic> transportStatistics) {
        this.transportStatistics = transportStatistics;
    }

    public int numberBus() {
        return (int) ((Math.random() * (ROUTE_MAX_NUMBER_CONST - ROUTE_MIN_NUMBER_CONST)) + ROUTE_MIN_NUMBER_CONST);
    }

    public void createBus() {
        Stream.iterate(0, i -> i + 1).limit(TransportStatistic.ROUTE_CONST).
                collect(Collectors.toList()).forEach(t -> transportStatistics.add(new TransportStatistic(new Bus("№" + numberBus()))));
    }

    public void busWorksInput(List<Integer> inputList) {
        final int[] listCount = {0};
        final int[] daysCount = {1};
        transportStatistics.forEach(transport -> {
            daysCount[0] = 1;
            IntStream.range(0, TransportStatistic.DAYS_CONST).forEach(t -> {
                transport.getBus().setCash(inputList.get(listCount[0]));
                transport.pushCash(daysCount[0]);
                listCount[0]++;
                daysCount[0]++;
            });
        });
    }

    public void outWeekReport() {
        final int[] weekCash = {0};
        final int[] daysCount = {1};
        transportStatistics.forEach(transport -> {
            daysCount[0] = 1;
            IntStream.range(0, TransportStatistic.DAYS_CONST).forEach(t -> {
                weekCash[0] += transport.takeCash(daysCount[0]);
                daysCount[0]++;
            });
            System.out.println("Недельная выручка " + "маршрута "
                    + transport.getBus().getBusName() + " составляет: " + Arrays.toString(weekCash));
            weekCash[0] = 0;
        });
    }
}
