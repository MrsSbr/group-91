package classes;

import java.util.Objects;

public class Lipstick extends DecorativeCosmetic {
    private String color;

    public Lipstick(int expiration_date, String texture, String appointment, int weight, String brand, String color) {
        super(expiration_date, texture, appointment, weight, brand);
        this.color = color;
    }

    @Override
    public void info() {
        System.out.println("Помада — средство декоративной косметики для подчеркивания выразительности губ...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nСolor: " + color;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lipstick cosmetic)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return Objects.equals(color, cosmetic.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
