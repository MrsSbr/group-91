package classes;

public class TransportStatistic {
    public static final int DAYS_CONST = 7;
    public static final int ROUTE_CONST = 10;
    public static final int MIN_CONST = 0;
    public static final int MAX_CONST = 15000;

    public Bus bus;
    private int mondayCash;
    private int tuesdayCash;
    private int wednesdayCash;
    private int thursdayCash;
    private int fridayCash;
    private int saturdayCash;
    private int sundayCash;

    public TransportStatistic(Bus bus) {
        this.bus = bus;
        mondayCash = 0;
        tuesdayCash = 0;
        wednesdayCash = 0;
        thursdayCash = 0;
        fridayCash = 0;
        saturdayCash = 0;
        sundayCash = 0;
    }

    public void pushCash(int day) {
        switch (day) {
            case (1):
                mondayCash = bus.dayEnds();
                break;
            case (2):
                tuesdayCash = bus.dayEnds();
                break;
            case (3):
                wednesdayCash = bus.dayEnds();
                break;
            case (4):
                thursdayCash = bus.dayEnds();
                break;
            case (5):
                fridayCash = bus.dayEnds();
                break;
            case (6):
                saturdayCash = bus.dayEnds();
                break;
            case (7):
                sundayCash = bus.dayEnds();
                break;
            default:
                break;
        }
    }

    public int takeCash(int day) {
        switch (day) {
            case (1):
                return mondayCash;
            case (2):
                return tuesdayCash;
            case (3):
                return wednesdayCash;
            case (4):
                return thursdayCash;
            case (5):
                return fridayCash;
            case (6):
                return saturdayCash;
            case (7):
                return sundayCash;
            default:
                return 0;
        }
    }
}


