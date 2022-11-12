package classes;

import java.util.List;

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
        for (int i = 0; i < TransportStatistic.ROUTE_CONST; i++) {
            transportStatistics.add(new TransportStatistic(new Bus("№" + numberBus())));
        }
    }

    public void busWorksInput(List<Integer> inputList) {
        int listCount = 0;
        for (TransportStatistic transport : transportStatistics) {
            for (int day = 0; day < TransportStatistic.DAYS_CONST; day++) {
                transport.getBus().setCash(inputList.get(listCount));
                transport.pushCash(day);
                listCount++;
            }
        }
    }

    public void outWeekReport() {
        int weekCash = 0;
        for (TransportStatistic transport : transportStatistics) {
            for (int i = 1; i < TransportStatistic.DAYS_CONST + 1; i++) {
                weekCash += transport.takeCash(i);
            }
            System.out.println("Недельная выручка " + "маршрута "
                    + transport.getBus().getBusName() + " составляет: " + weekCash);
            weekCash = 0;
        }
    }
}
