package Classes;

import Enums.FluidType;

import java.util.Objects;

public class Pilule extends Pill {

    private final FluidType requiredFluid;

    public Pilule(String name, int countPerKilo, float price, FluidType requiredFluid) {
        super(name, countPerKilo, price);
        this.requiredFluid = requiredFluid;
    }

    public String getRequiredFluid() {
        return requiredFluid.toString();
    }

    @Override
    public String toString() {
        return super.toString() + "need: " + getRequiredFluid() + '\n';
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Pilule drug)) {
            return false;
        }

        return super.equals(drug) && requiredFluid == drug.requiredFluid;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode() + requiredFluid.hashCode());
    }
}
