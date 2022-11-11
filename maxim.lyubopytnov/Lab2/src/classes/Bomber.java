package classes;

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
                "Процент скрытности %f %\n" +
                "Грузоподъемность бомб %f\n" +
                isTakeOffToString(),
                name,
                obscurity,
                bombCapacity
        );
    }
}
