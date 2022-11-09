package classes;

import java.util.Objects;

public class Destroyer extends MilitaryShip {
    private int minesCount;

    public Destroyer(int length, int width, int draft, int displacement, String model, int minesCount) {
        super(length, width, draft, displacement, model);
        this.minesCount = minesCount;
    }

    @Override
    public void info() {
        System.out.println("Эскадренный миноносец — класс многоцелевых боевых быстроходных маневренных кораблей...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nMinesCount: " + minesCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Destroyer ship)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return minesCount == ship.minesCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minesCount);
    }
}
