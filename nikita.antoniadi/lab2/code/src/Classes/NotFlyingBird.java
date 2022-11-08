package Classes;

import java.util.Objects;

public class NotFlyingBird extends Bird {

    protected final String speciesName;

    public NotFlyingBird(double weight, double wingspan, String speciesName) {
        super(weight, wingspan);
        this.speciesName = speciesName;
    }

    @Override
    public void walk() {
        System.out.println(speciesName + " walks");
    }

    @Override
    public String toString() {
        return "Вид: " + speciesName + "\nСредний вес: " + averageWeight + "\nСредний размах крыльев: " + averageWingspan;
    }

    @Override
    public int hashCode() {
        return speciesName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return Objects.equals(this.speciesName, ((NotFlyingBird) obj).speciesName);
    }
}