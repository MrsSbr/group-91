package classes;

import java.util.LinkedList;
import java.util.List;

public class TransportModelLinkedList {

    public static final int ROUTE_MIN_NUMBER_CONST = 1;
    public static final int ROUTE_MAX_NUMBER_CONST = 200;

    final List<TransportStatistic> transportStatistics = new LinkedList<>();

    public void createBus() {
        int numberBus;
        for (int i = 0; i < TransportStatistic.ROUTE_CONST; i++) {
            numberBus = (int) ((Math.random() * (ROUTE_MAX_NUMBER_CONST - ROUTE_MIN_NUMBER_CONST)) + ROUTE_MIN_NUMBER_CONST);
            transportStatistics.add(new TransportStatistic(new Bus("№" + numberBus)));
        }
    }

    public void busWorksInput(List<Integer> inputConsole) {
        for (int i = 0, j = 0, listCount = 0;
             listCount < TransportStatistic.ROUTE_CONST * TransportStatistic.DAYS_CONST; j++, listCount++) {
            if (j == 7) {
                j = 0;
                i++;
            }
            transportStatistics.get(i).bus.setCash(inputConsole.get(listCount));
            transportStatistics.get(i).pushCash(j);
        }
    }

    public void outWeekReport() {
        int weekCash = 0;
        for (TransportStatistic transport : transportStatistics) {
            for (int i = 1; i < TransportStatistic.DAYS_CONST + 1; i++) {
                weekCash += transport.takeCash(i);
            }
            System.out.println("Недельная выручка " + "маршрута "
                    + transport.bus.busName + " составляет: " + weekCash);
            weekCash = 0;
        }
    }
}
