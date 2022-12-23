package data;

import enums.StationType;

import java.time.LocalDate;
import java.util.Objects;

public class Note {
    private final LocalDate date;
    private final StationType type;
    private final int power;

    public Note(LocalDate date, StationType type, int power) {
        this.date = date;
        this.type = type;
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public LocalDate getDate() {
        return date;
    }

    public StationType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "power: " + power
                + "\ndate: " + date
                + "\ntype: " + type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, type, date);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return power == note.power && date.equals(note.date) && type == note.type;
    }
}