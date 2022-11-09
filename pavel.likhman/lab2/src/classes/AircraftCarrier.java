package classes;

import java.util.Objects;

public class AircraftCarrier extends MilitaryShip {
    private int aircraftCount;

    public AircraftCarrier(int length, int width, int draft, int displacement, String model, int aircraftCount) {
        super(length, width, draft, displacement, model);
        this.aircraftCount = aircraftCount;
    }

    public void launchAircraft() {
        System.out.println("Производится запуск самолёта с авианосца " + model);
    }

    @Override
    public void info() {
        System.out.println("Авианосец — класс боевых кораблей, предназначенный для базирования на борту...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nAircraftCount: " + aircraftCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AircraftCarrier ship)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return aircraftCount == ship.aircraftCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), aircraftCount);
    }
}
