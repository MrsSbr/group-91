package Classes;

import Interfaces.Race;

import java.util.Objects;

public abstract class Humanoid implements Race {

    protected String name;
    protected int height;

    public Humanoid(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public abstract void printDescription();

    public void printName() {
        System.out.println("\nМеня (Его) зовут " + name);
    }

    @Override
    public String toString() {
        return "\nИмя: " + name +
                "\nРост: " + height;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Humanoid humanoidObject = (Humanoid) obj;
        return name.equals(humanoidObject.name) &&
                height == humanoidObject.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height);
    }
}
