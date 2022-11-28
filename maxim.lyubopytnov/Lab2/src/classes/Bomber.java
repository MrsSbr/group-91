package classes;

import java.util.Objects;

// Бомбардировщик
public class Bomber extends Plane{
    // Грузоподъемность
    private final float bombCapacity;

    public Bomber(String name, float obscurity, float bombCapacity) {
        super(name, obscurity);
        this.bombCapacity= bombCapacity;
    }

    @Override
    public String toString() {
        return String.format(
                "Бомбардировщик %s\n" +
                "Процент скрытности %f\n" +
                "Грузоподъемность бомб %f\n" +
                isTakeOffToString(),
                name,
                obscurity,
                bombCapacity
        );
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Bomber)) {
            return false;
        } else {
            Bomber objBomber = (Bomber) obj;
            return this.name.equalsIgnoreCase(objBomber.name)
                    && this.obscurity == objBomber.obscurity
                    && this.bombCapacity == objBomber.bombCapacity;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bombCapacity);
    }
}
