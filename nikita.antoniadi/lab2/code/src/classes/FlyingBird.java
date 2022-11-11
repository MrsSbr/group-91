package classes;

import interfaces.Flying;

import java.util.Objects;

public class FlyingBird extends NotFlyingBird implements Flying {

    protected final int flightSpeed;
    public FlyingBird(double weight, double wingspan, String speciesName, int flightSpeed) {
        super(weight, wingspan, speciesName);
        this.flightSpeed = flightSpeed;
    }

    @Override
    public void fly() {
        System.out.println(speciesName + " flies");
    }

    @Override
    public String toString() {
        return super.toString() + "\nСкорость полёта: " + flightSpeed;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Integer.hashCode(flightSpeed);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return Objects.equals(this.speciesName, ((FlyingBird) obj).speciesName);
    }
}