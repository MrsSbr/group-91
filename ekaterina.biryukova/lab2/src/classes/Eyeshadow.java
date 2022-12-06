package classes;

import java.util.Objects;

public class Eyeshadow extends DecorativeCosmetic {
    private int colorsCount;

    public Eyeshadow(int expiration_date, String texture, String appointment, int weight, String brand, int colorsCount) {
        super(expiration_date, texture, appointment, weight, brand);
        this.colorsCount = colorsCount;
    }

    @Override
    public void info() {
        System.out.println("Тени для век — средство декоративной косметики для макияжа глаз...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumber of colors: " + colorsCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Eyeshadow cosmetic)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return colorsCount == cosmetic.colorsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colorsCount);
    }
}
