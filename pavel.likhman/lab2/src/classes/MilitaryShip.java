package classes;

import interfaces.Ship;

import java.util.Objects;

public abstract class MilitaryShip implements Ship {
    protected int length;
    protected int width;
    protected int draft;
    protected int displacement;
    protected String model;

    public MilitaryShip(int length, int width, int draft, int displacement, String model) {
        this.length = length;
        this.width = width;
        this.draft = draft;
        this.displacement = displacement;
        this.model = model;
    }

    public abstract void info();

    @Override
    public int getDisplacement() {
        return displacement;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Length: " + length + "\nWidth: " + width + "\nDraft: " + draft +
                "\nDisplacement: " + displacement + "\nModel: " + model;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        MilitaryShip ship = (MilitaryShip) obj;
        return length == ship.length && width == ship.width && draft == ship.draft &&
                displacement == ship.displacement && Objects.equals(model, ship.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, draft, displacement, model);
    }
}
