package classes;

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

        NotFlyingBird notFlyingBird = (NotFlyingBird) obj;
        return Objects.equals(this.speciesName, notFlyingBird.speciesName) && averageWeight == notFlyingBird.averageWeight
                && averageWingspan == notFlyingBird.averageWingspan;

    }
}