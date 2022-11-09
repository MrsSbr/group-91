package classes;

import java.util.Objects;

public class Battleship extends MilitaryShip {
    private int gunsCount;

    public Battleship(int length, int width, int draft, int displacement, String model, int gunsCount) {
        super(length, width, draft, displacement, model);
        this.gunsCount = gunsCount;
    }

    @Override
    public void info() {
        System.out.println("Линейный корабль — русское название класса самых мощных артиллерийских бронированных...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nGunsCount: " + gunsCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Battleship ship)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return gunsCount == ship.gunsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gunsCount);
    }
}
