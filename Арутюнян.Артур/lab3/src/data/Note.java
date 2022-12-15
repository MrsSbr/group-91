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
    public boolean equals(Object obj) {
        if (obj instanceof Note) {
            Note note = (Note) obj;
            return power == note.power
                    && date == note.date
                    && type == note.type;
        }
        return false;
    }
}