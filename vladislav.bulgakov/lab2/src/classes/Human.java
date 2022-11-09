package classes;

import java.util.Objects;

public class Human extends Humanoid {

    private String skinColor;

    public Human(String name, int height, String skinColor) {
        super(name, height);
        this.skinColor = skinColor;
    }

    @Override
    public void printDescription() {
        System.out.println("\nЧеловек по имени " + name + ", рост " + height + ", а цвет кожи " + skinColor);
    }

    @Override
    public String toString() {
        return super.toString() + "\nЦвет кожи: " + skinColor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        Human humanObject = (Human) obj;
        return name.equals(humanObject.name) &&
                height == humanObject.height &&
                skinColor.equals(humanObject.skinColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skinColor);
    }
}
