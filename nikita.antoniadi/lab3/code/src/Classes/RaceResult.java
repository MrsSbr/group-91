package Classes;

import java.time.LocalDate;
import java.util.Objects;

public class RaceResult {
    private final int cyclistNumber;
    private final LocalDate raceDate;
    private final int place;

    public RaceResult(int cyclistNumber, LocalDate raceDate, int place) {
        this.cyclistNumber = cyclistNumber;
        this.raceDate = raceDate;
        this.place = place;
    }

    @Override
    public String toString() {
        return String.format("Дата гонки: " + raceDate + "\nНомер велосипедиста: %d\nМесто: %d", cyclistNumber, place);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceResult that = (RaceResult) o;
        return cyclistNumber == that.cyclistNumber && place == that.place && raceDate.equals(that.raceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistNumber, raceDate, place);
    }

    public LocalDate getRaceDate() {
        return raceDate;
    }

    public int getPlace() {
        return place;
    }

    public int getCyclistNumber() {
        return cyclistNumber;
    }
}
