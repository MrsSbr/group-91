package classes;

import java.util.Objects;

public class Raincoat extends Outerwear {
    private String cloth;

    public Raincoat(String season, String colour, String print, int size, String brand, String cloth) {
        super(season, colour, print, size, brand);
        this.cloth = cloth;
    }

    public void buyRaincoat() {
        System.out.println("Вы надели плащ " + brand);
    }

    @Override
    public void info () {
        System.out.println("Плащ — предмет верхней одежды из непромокаемого материала....");
    }

    @Override
    public String toString() {
        return super.toString() + "\nCloth: " + cloth;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Raincoat clothes)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return Objects.equals(cloth, clothes.cloth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cloth);
    }
}